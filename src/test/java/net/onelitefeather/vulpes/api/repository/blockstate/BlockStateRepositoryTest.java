package net.onelitefeather.vulpes.api.repository.blockstate;

import io.micronaut.data.model.Pageable;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import net.onelitefeather.vulpes.api.model.blockstate.BlockStateEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = "test")
class BlockStateRepositoryTest {

    @Inject
    BlockStateRepository repository;

    @Inject
    EntityManager entityManager;

    private ProjectEntity project;
    private ProjectEntity otherProject;

    @BeforeEach
    void setUp() {
        project = persist(new ProjectEntity(null, "Project", "project-" + UUID.randomUUID(), null, null, null, false));
        otherProject = persist(new ProjectEntity(null, "Other", "other-" + UUID.randomUUID(), null, null, null, false));
    }

    private <T> T persist(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    private BlockStateEntity blockState(String key, ProjectEntity owner) {
        return persist(new BlockStateEntity(null, owner, key, key, null, "[]"));
    }

    private static List<UUID> idsOf(List<BlockStateEntity> blockStates) {
        return blockStates.stream().map(BlockStateEntity::getId).sorted().toList();
    }

    @Test
    @DisplayName("findByProjectId() returns only the blockstates of the project")
    void findsOnlyBlockStatesOfProject() {
        var petals = blockState("minecraft:pink_petals", project);
        var stairs = blockState("minecraft:oak_stairs", project);
        blockState("minecraft:pink_petals", otherProject);

        var found = repository.findByProjectId(project.getId(), Pageable.UNPAGED).getContent();

        assertEquals(idsOf(List.of(petals, stairs)), idsOf(found));
    }

    @Test
    @DisplayName("findByProjectIdAndKey() returns every context of the block in the project")
    void findsContextsOfBlock() {
        var first = blockState("minecraft:pink_petals", project);
        var second = blockState("minecraft:pink_petals", project);
        blockState("minecraft:oak_stairs", project);
        blockState("minecraft:pink_petals", otherProject);

        var found = repository.findByProjectIdAndKey(project.getId(), "minecraft:pink_petals");

        assertEquals(idsOf(List.of(first, second)), idsOf(found));
    }
}
