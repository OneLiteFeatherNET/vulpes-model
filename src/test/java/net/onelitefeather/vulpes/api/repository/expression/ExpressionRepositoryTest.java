package net.onelitefeather.vulpes.api.repository.expression;

import io.micronaut.data.model.Pageable;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import net.onelitefeather.vulpes.api.model.expression.ExpressionEntity;
import net.onelitefeather.vulpes.api.model.expression.ExpressionFunctionEntity;
import net.onelitefeather.vulpes.api.model.expression.ExpressionFunctionKind;
import net.onelitefeather.vulpes.api.model.expression.ExpressionVariableType;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = "test")
class ExpressionRepositoryTest {

    @Inject
    ExpressionRepository expressionRepository;

    @Inject
    ExpressionFunctionRepository functionRepository;

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

    private ExpressionEntity expression(String uiName, ProjectEntity owner) {
        return persist(new ExpressionEntity(null, uiName, "1 + 1", null, List.of(), owner));
    }

    private ExpressionFunctionEntity function(String name, ProjectEntity owner) {
        return persist(new ExpressionFunctionEntity(null, name, name, ExpressionFunctionKind.FORMULA,
                ExpressionVariableType.NUMBER, "1", null, List.of(), owner));
    }

    @Test
    @DisplayName("findByProjectId() returns only the expressions of the project")
    void findsOnlyExpressionsOfProject() {
        var damage = expression("Damage", project);
        var heal = expression("Heal", project);
        expression("Foreign", otherProject);

        var page = expressionRepository.findByProjectId(project.getId(), Pageable.from(0, 10));

        assertEquals(2, page.getTotalSize());
        assertEquals(
                List.of(damage.getId(), heal.getId()).stream().sorted().toList(),
                page.getContent().stream().map(ExpressionEntity::getId).sorted().toList()
        );
    }

    @Test
    @DisplayName("findByProjectId() returns only the functions of the project")
    void findsOnlyFunctionsOfProject() {
        var bonus = function("bonus", project);
        function("foreign", otherProject);

        var page = functionRepository.findByProjectId(project.getId(), Pageable.from(0, 10));

        assertEquals(List.of(bonus.getId()), page.getContent().stream().map(ExpressionFunctionEntity::getId).toList());
    }
}
