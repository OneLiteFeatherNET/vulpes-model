package net.onelitefeather.vulpes.api.model.pack;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import net.onelitefeather.vulpes.api.model.asset.AssetKeySpace;
import net.onelitefeather.vulpes.api.model.asset.AssetKind;
import net.onelitefeather.vulpes.api.model.asset.ProjectAssetEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(environments = "test")
class PackMetaPersistenceTest {

    @Inject
    EntityManager entityManager;

    private ProjectEntity project;

    @BeforeEach
    void setUp() {
        project = new ProjectEntity(null, "Project", "project-" + UUID.randomUUID(), null, null, null, false);
        persistAndFlush(project);
    }

    private void persistAndFlush(Object entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    private PackMetaEntity meta(PackType type, int minMajor, int minMinor, int maxMajor, Integer maxMinor) {
        return new PackMetaEntity(null, project, type, "{\"text\":\"Vulpes\"}",
                minMajor, minMinor, maxMajor, maxMinor, Set.of());
    }

    @Test
    @DisplayName("pack meta keeps its format range and enabled features")
    void keepsFormatRangeAndFeatures() {
        var meta = new PackMetaEntity(null, project, PackType.DATA, "{\"text\":\"Vulpes\"}",
                82, 0, 88, null, Set.of("minecraft:trade_rebalance", "minecraft:redstone_experiments"));
        persistAndFlush(meta);
        entityManager.clear();

        var read = entityManager.find(PackMetaEntity.class, meta.getId());

        assertEquals(PackType.DATA, read.getPackType());
        assertEquals("{\"text\":\"Vulpes\"}", read.getDescription());
        assertEquals(82, read.getMinFormatMajor());
        assertEquals(0, read.getMinFormatMinor());
        assertEquals(88, read.getMaxFormatMajor());
        assertNull(read.getMaxFormatMinor());
        assertEquals(Set.of("minecraft:trade_rebalance", "minecraft:redstone_experiments"),
                read.getEnabledFeatures());
    }

    @Test
    @DisplayName("the pack type is stored by its name")
    void packTypeIsStoredByName() {
        var meta = meta(PackType.RESOURCE, 60, 0, 60, null);
        persistAndFlush(meta);

        var type = entityManager
                .createNativeQuery("select pack_type from pack_meta where id = :id")
                .setParameter("id", meta.getId())
                .getSingleResult();

        assertEquals("RESOURCE", type);
    }

    @Test
    @DisplayName("a maximum below the minimum is rejected")
    void maxBelowMinIsRejected() {
        assertThrows(ConstraintViolationException.class, () -> persistAndFlush(meta(PackType.DATA, 82, 0, 81, null)));
        assertThrows(ConstraintViolationException.class, () -> persistAndFlush(meta(PackType.DATA, 82, 3, 82, 2)));
    }

    @Test
    @DisplayName("a maximum without minor version accepts any minimum minor version of the same major")
    void maxWithoutMinorAcceptsSameMajor() {
        assertDoesNotThrow(() -> persistAndFlush(meta(PackType.DATA, 82, 5, 82, null)));
    }

    @Test
    @DisplayName("a project has at most one pack meta per pack type")
    void onePerProjectAndType() {
        persistAndFlush(meta(PackType.DATA, 82, 0, 82, null));

        assertThrows(PersistenceException.class, () -> persistAndFlush(meta(PackType.DATA, 83, 0, 83, null)));
    }

    private ProjectAssetEntity persistTexture(String key) {
        var asset = new ProjectAssetEntity();
        asset.setProject(project);
        asset.setKey(key);
        asset.setDisplayName(key);
        asset.setKind(AssetKind.TEXTURE);
        asset.setKeySpace(AssetKeySpace.of(AssetKind.TEXTURE));
        asset.setFileName("pack.png");
        asset.setContentType(AssetKind.TEXTURE.contentType());
        asset.setSize(1);
        asset.setSha1("0".repeat(40));
        persistAndFlush(asset);
        return asset;
    }

    @Test
    @DisplayName("pack meta keeps its icon asset")
    void keepsIconAsset() {
        var icon = persistTexture("vulpes:pack/icon");
        var meta = meta(PackType.RESOURCE, 60, 0, 60, null);
        meta.setIconAsset(icon);
        persistAndFlush(meta);
        entityManager.clear();

        var read = entityManager.find(PackMetaEntity.class, meta.getId());

        assertNotNull(read.getIconAsset());
        assertEquals(icon.getId(), read.getIconAsset().getId());
    }

    @Test
    @DisplayName("pack meta without an icon has no icon asset")
    void withoutIconAsset() {
        var meta = meta(PackType.DATA, 82, 0, 82, null);
        persistAndFlush(meta);
        entityManager.clear();

        assertNull(entityManager.find(PackMetaEntity.class, meta.getId()).getIconAsset());
    }

    @Test
    @DisplayName("deleting the icon asset leaves the pack meta without an icon")
    void deletingIconAssetKeepsPackMeta() {
        var icon = persistTexture("vulpes:pack/icon");
        var meta = meta(PackType.RESOURCE, 60, 0, 60, null);
        meta.setIconAsset(icon);
        persistAndFlush(meta);
        entityManager.clear();

        entityManager.remove(entityManager.find(ProjectAssetEntity.class, icon.getId()));
        entityManager.flush();
        entityManager.clear();

        var read = entityManager.find(PackMetaEntity.class, meta.getId());
        assertNotNull(read, "The pack meta must lose its icon, not block the delete");
        assertNull(read.getIconAsset());
    }

    @Test
    @DisplayName("pack meta without a project is rejected")
    void withoutProjectIsRejected() {
        var meta = new PackMetaEntity(null, null, PackType.DATA, "{}", 82, 0, 82, null, Set.of());

        assertThrows(ConstraintViolationException.class, () -> persistAndFlush(meta));
    }
}
