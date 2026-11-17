package net.onelitefeather.vulpes.api.model.blockstate;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(environments = "test")
class BlockStatePersistenceTest {

    private static final String RULES = """
            [{"name":"Seat","when":{"facing":["north"]},"model":"minecraft:block/seat","x":0,"y":90,"uvlock":true}]""";

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

    private BlockStateEntity blockState(String key) {
        return new BlockStateEntity(null, project, "Petals", key, null, RULES);
    }

    @Test
    @DisplayName("a blockstate keeps its block key, comment and rules")
    void keepsBlockKeyCommentAndRules() {
        var blockState = new BlockStateEntity(null, project, "Petals", "minecraft:pink_petals", "A note", RULES);
        persistAndFlush(blockState);
        entityManager.clear();

        var read = entityManager.find(BlockStateEntity.class, blockState.getId());

        assertEquals("Petals", read.getUiName());
        assertEquals("minecraft:pink_petals", read.getKey());
        assertEquals("A note", read.getComment());
        assertEquals(RULES, read.getRules());
    }

    @Test
    @DisplayName("a blockstate without a comment has none")
    void withoutComment() {
        var blockState = blockState("minecraft:pink_petals");
        persistAndFlush(blockState);
        entityManager.clear();

        assertNull(entityManager.find(BlockStateEntity.class, blockState.getId()).getComment());
    }

    @Test
    @DisplayName("a project may hold several contexts of the same block")
    void severalContextsPerBlock() {
        persistAndFlush(blockState("minecraft:pink_petals"));

        assertDoesNotThrow(() -> persistAndFlush(blockState("minecraft:pink_petals")));
    }

    @Test
    @DisplayName("a block key without a namespace is rejected")
    void blockKeyWithoutNamespaceIsRejected() {
        assertThrows(ConstraintViolationException.class, () -> persistAndFlush(blockState("pink_petals")));
    }

    @Test
    @DisplayName("a blockstate without a project is rejected")
    void withoutProjectIsRejected() {
        var blockState = new BlockStateEntity(null, null, "Petals", "minecraft:pink_petals", null, RULES);

        assertThrows(ConstraintViolationException.class, () -> persistAndFlush(blockState));
    }

    @Test
    @DisplayName("deleting the project deletes its blockstates")
    void deletingProjectDeletesBlockStates() {
        var blockState = blockState("minecraft:pink_petals");
        persistAndFlush(blockState);
        entityManager.clear();

        entityManager.createNativeQuery("delete from projects where id = :id")
                .setParameter("id", project.getId())
                .executeUpdate();
        entityManager.clear();

        assertNull(entityManager.find(BlockStateEntity.class, blockState.getId()));
    }
}
