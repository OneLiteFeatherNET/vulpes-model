package net.onelitefeather.vulpes.api.repository;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(environments = "test")
class ProjectRepositoryTest {

    @Inject
    ProjectRepository projectRepository;

    @Inject
    EntityManager entityManager;

    private ProjectEntity persistProject(String key) {
        var project = new ProjectEntity(null, "Project", key, null, null, null, false);
        entityManager.persist(project);
        entityManager.flush();
        entityManager.clear();
        return project;
    }

    @Test
    @DisplayName("findByKey() returns the project with the given key")
    void findByKeyReturnsMatchingProject() {
        var key = "project-" + UUID.randomUUID();
        var project = persistProject(key);
        persistProject("other-" + UUID.randomUUID());

        Optional<ProjectEntity> result = projectRepository.findByKey(key);

        assertTrue(result.isPresent(), "The project with the given key must be found");
        assertEquals(project.getId(), result.get().getId());
    }

    @Test
    @DisplayName("findByKey() returns an empty result for an unknown key")
    void findByKeyReturnsEmptyForUnknownKey() {
        persistProject("project-" + UUID.randomUUID());

        Optional<ProjectEntity> result = projectRepository.findByKey("unknown-" + UUID.randomUUID());

        assertTrue(result.isEmpty(), "No project must be found for an unknown key");
    }
}
