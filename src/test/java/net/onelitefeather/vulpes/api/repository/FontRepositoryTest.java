package net.onelitefeather.vulpes.api.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import net.onelitefeather.vulpes.api.model.FontEntity;
import net.onelitefeather.vulpes.api.model.font.FontStringEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private FontEntity persistFont(ProjectEntity project, String key, int charCount) {
        var font = new FontEntity(null, "Font " + key, key, "bitmap", "texture.png", null, 8, 7, new ArrayList<>(), project);
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
}
