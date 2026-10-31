package net.onelitefeather.vulpes.api.model.asset;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(environments = "test")
class ProjectAssetPersistenceTest {

    @Inject
    EntityManager entityManager;

    private ProjectEntity project;

    @BeforeEach
    void setUp() {
        project = new ProjectEntity(null, "Project", "project-" + UUID.randomUUID(), null, null, null, false);
        entityManager.persist(project);
        entityManager.flush();
    }

    private ProjectAssetEntity asset(String key, AssetKind kind) {
        var asset = new ProjectAssetEntity();
        asset.setProject(project);
        asset.setKey(key);
        asset.setDisplayName(key);
        asset.setKind(kind);
        asset.setKeySpace(AssetKeySpace.of(kind));
        asset.setFileName(key + ".png");
        asset.setContentType(kind.contentType());
        asset.setSize(1);
        asset.setSha1("0".repeat(40));
        return asset;
    }

    private ProjectAssetRevisionEntity revision(ProjectAssetEntity asset, int number) {
        var revision = new ProjectAssetRevisionEntity();
        revision.setAsset(asset);
        revision.setNumber(number);
        revision.setSha1("0".repeat(40));
        revision.setSize(1);
        revision.setStorageKey(project.getId() + "/" + asset.getId() + "/" + number);
        return revision;
    }

    private void persistAndFlush(Object entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Test
    @DisplayName("a texture and a model may share a key")
    void textureAndModelShareKey() {
        persistAndFlush(asset("vulpes:block/stone", AssetKind.TEXTURE));

        assertDoesNotThrow(() -> persistAndFlush(asset("vulpes:block/stone", AssetKind.MODEL)));
    }

    @Test
    @DisplayName("a model and a Blockbench model must not share a key")
    void modelAndBlockbenchModelRejectSharedKey() {
        persistAndFlush(asset("vulpes:block/stone", AssetKind.MODEL));

        assertThrows(PersistenceException.class,
                () -> persistAndFlush(asset("vulpes:block/stone", AssetKind.BBMODEL)));
    }

    @Test
    @DisplayName("revisions of one asset are numbered one after another")
    void revisionsAreNumberedPerAsset() {
        var asset = asset("vulpes:block/stone", AssetKind.TEXTURE);
        persistAndFlush(asset);
        persistAndFlush(revision(asset, 1));

        assertDoesNotThrow(() -> persistAndFlush(revision(asset, 2)));
    }

    @Test
    @DisplayName("an asset must not have two revisions with the same number")
    void duplicateRevisionNumberIsRejected() {
        var asset = asset("vulpes:block/stone", AssetKind.TEXTURE);
        persistAndFlush(asset);
        persistAndFlush(revision(asset, 1));

        assertThrows(PersistenceException.class, () -> persistAndFlush(revision(asset, 1)));
    }
}
