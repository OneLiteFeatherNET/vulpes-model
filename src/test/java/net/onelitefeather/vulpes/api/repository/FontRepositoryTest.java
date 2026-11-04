package net.onelitefeather.vulpes.api.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import net.onelitefeather.vulpes.api.model.FontEntity;
import net.onelitefeather.vulpes.api.model.asset.AssetKeySpace;
import net.onelitefeather.vulpes.api.model.asset.AssetKind;
import net.onelitefeather.vulpes.api.model.asset.ProjectAssetEntity;
import net.onelitefeather.vulpes.api.model.font.FontStringEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(environments = "test")
class FontRepositoryTest {

    @Inject
    FontRepository fontRepository;

    @Inject
    EntityManager entityManager;

    private ProjectEntity persistProject() {
        var project = new ProjectEntity(null, "Project", "project-" + UUID.randomUUID(), null, null, null, false);
        entityManager.persist(project);
        return project;
    }

    private ProjectAssetEntity persistTexture(ProjectEntity project, String key) {
        var asset = new ProjectAssetEntity();
        asset.setProject(project);
        asset.setKey(key);
        asset.setDisplayName(key);
        asset.setKind(AssetKind.TEXTURE);
        asset.setKeySpace(AssetKeySpace.of(AssetKind.TEXTURE));
        asset.setFileName("texture.png");
        asset.setContentType(AssetKind.TEXTURE.contentType());
        asset.setSize(1);
        asset.setSha1("0".repeat(40));
        entityManager.persist(asset);
        return asset;
    }

    private FontEntity persistFont(ProjectEntity project, String key, int charCount) {
        return persistFont(project, key, charCount, null);
    }

    private FontEntity persistFont(ProjectEntity project, String key, int charCount, ProjectAssetEntity texture) {
        var font = new FontEntity(null, "Font " + key, key, "bitmap", "texture.png", null, 8, 7, new ArrayList<>(), project);
        font.setTextureAsset(texture);
        for (int i = 0; i < charCount; i++) {
            var fontString = new FontStringEntity(null, "line-" + i, i);
            fontString.setFont(font);
            font.getChars().add(fontString);
        }
        entityManager.persist(font);
        entityManager.flush();
        entityManager.clear();
        return font;
    }

    @Test
    void findAllReturnsFontWithoutChars() {
        var font = persistFont(persistProject(), "empty", 0);

        List<FontEntity> result = fontRepository.findAll();

        assertTrue(result.stream().anyMatch(f -> f.getId().equals(font.getId())),
                "A font without chars must be returned by findAll()");
    }

    @Test
    void findAllReturnsFontWithTwoCharsExactlyOnce() {
        var font = persistFont(persistProject(), "two-chars", 2);

        List<FontEntity> result = fontRepository.findAll();

        var matches = result.stream().filter(f -> f.getId().equals(font.getId())).toList();
        assertEquals(1, matches.size(), "A font with two chars must appear exactly once in findAll()");
        assertEquals(2, matches.getFirst().getChars().size(), "The chars of the font must be loaded");
    }

    @Test
    void findByIdKnowsTheIdOfTheTextureAsset() {
        var project = persistProject();
        var texture = persistTexture(project, "vulpes:font/ghost");
        var font = persistFont(project, "icons", 0, texture);

        var found = fontRepository.findById(font.getId()).orElseThrow();

        assertNotNull(found.getTextureAsset(), "The font must refer to its texture asset");
        assertEquals(texture.getId(), found.getTextureAsset().getId());
    }

    @Test
    void findWithTextureAssetByProjectIdLoadsTheTextureAsset() {
        var project = persistProject();
        var texture = persistTexture(project, "vulpes:font/ghost");
        var withTexture = persistFont(project, "icons", 0, texture);
        var withoutTexture = persistFont(project, "plain", 0);
        persistFont(persistProject(), "other", 0, null);

        List<FontEntity> result = fontRepository.findWithTextureAssetByProjectId(project.getId());

        assertEquals(2, result.size(), "Only the project's fonts, with and without texture, must be returned");
        var icons = result.stream().filter(f -> f.getId().equals(withTexture.getId())).findFirst().orElseThrow();
        assertTrue(Hibernate.isInitialized(icons.getTextureAsset()), "The texture asset must be loaded with the font");
        assertEquals("vulpes:font/ghost", icons.getTextureAsset().getKey());
        var plain = result.stream().filter(f -> f.getId().equals(withoutTexture.getId())).findFirst().orElseThrow();
        assertNull(plain.getTextureAsset());
    }

    @Test
    void deletingTheTextureAssetLeavesTheFontWithoutTexture() {
        var project = persistProject();
        var texture = persistTexture(project, "vulpes:font/ghost");
        var font = persistFont(project, "icons", 0, texture);

        entityManager.remove(entityManager.find(ProjectAssetEntity.class, texture.getId()));
        entityManager.flush();
        entityManager.clear();

        var found = fontRepository.findById(font.getId()).orElseThrow();
        assertNull(found.getTextureAsset(), "The font must lose its texture, not block the delete");
    }

    @Test
    void severalFontsMayShareATextureAsset() {
        var project = persistProject();
        var texture = persistTexture(project, "vulpes:font/shared");

        persistFont(project, "first", 0, texture);
        var second = persistFont(project, "second", 0, texture);

        assertEquals(texture.getId(), fontRepository.findById(second.getId()).orElseThrow().getTextureAsset().getId());
    }
}
