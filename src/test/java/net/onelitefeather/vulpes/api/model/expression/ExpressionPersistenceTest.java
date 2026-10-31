package net.onelitefeather.vulpes.api.model.expression;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(environments = "test")
class ExpressionPersistenceTest {

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

    /** Persists the entity and reads it back from the database, not from the persistence context. */
    private <T extends IdentifiableEntity> T reload(T entity, Class<T> type) {
        persistAndFlush(entity);
        entityManager.clear();
        return entityManager.find(type, entity.getId());
    }

    @Test
    @DisplayName("an expression keeps its variables in their order")
    void expressionKeepsVariableOrder() {
        var expression = new ExpressionEntity(null, "Damage", "base * (1 + bonus)", null, List.of(
                new ExpressionVariable("base", ExpressionVariableType.NUMBER, "10"),
                new ExpressionVariable("crit", ExpressionVariableType.BOOLEAN, null),
                new ExpressionVariable("label", ExpressionVariableType.STRING, "hit")
        ), project);

        var read = reload(expression, ExpressionEntity.class);

        assertEquals(List.of("base", "crit", "label"),
                read.getVariables().stream().map(ExpressionVariable::getName).toList());
        assertEquals(ExpressionVariableType.BOOLEAN, read.getVariables().get(1).getType());
        assertEquals("10", read.getVariables().getFirst().getTestValue());
        assertNull(read.getVariables().get(1).getTestValue());
    }

    @Test
    @DisplayName("a function keeps its kind, return type and parameters")
    void functionKeepsKindReturnTypeAndParameters() {
        var function = new ExpressionFunctionEntity(null, "Hit", "hit", ExpressionFunctionKind.INTERFACE,
                ExpressionVariableType.BOOLEAN, null, "Implemented by the game", List.of(
                new ExpressionVariable("target", ExpressionVariableType.STRING, null),
                new ExpressionVariable("damage", ExpressionVariableType.NUMBER, "4")
        ), project);

        var read = reload(function, ExpressionFunctionEntity.class);

        assertEquals(ExpressionFunctionKind.INTERFACE, read.getKind());
        assertEquals(ExpressionVariableType.BOOLEAN, read.getReturnType());
        assertNull(read.getBody());
        assertEquals(List.of("target", "damage"),
                read.getParameters().stream().map(ExpressionVariable::getName).toList());
    }

    @Test
    @DisplayName("the kind and return type are stored by their names")
    void kindAndReturnTypeAreStoredByName() {
        var function = new ExpressionFunctionEntity(null, "Bonus", "bonus", ExpressionFunctionKind.FORMULA,
                ExpressionVariableType.NUMBER, "base * 2", null, List.of(), project);
        persistAndFlush(function);

        var row = (Object[]) entityManager
                .createNativeQuery("select kind, return_type from expression_function where id = :id")
                .setParameter("id", function.getId())
                .getSingleResult();

        assertEquals("FORMULA", row[0]);
        assertEquals("NUMBER", row[1]);
    }

    @Test
    @DisplayName("an expression without a project is rejected")
    void expressionWithoutProjectIsRejected() {
        var expression = new ExpressionEntity(null, "Orphan", "1 + 1", null, List.of(), null);

        assertThrows(ConstraintViolationException.class, () -> persistAndFlush(expression));
    }
}
