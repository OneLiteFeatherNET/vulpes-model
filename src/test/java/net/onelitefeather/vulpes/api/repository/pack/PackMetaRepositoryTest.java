package net.onelitefeather.vulpes.api.repository.pack;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import net.onelitefeather.vulpes.api.model.pack.PackMetaEntity;
import net.onelitefeather.vulpes.api.model.pack.PackType;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(environments = "test")
class PackMetaRepositoryTest {

    @Inject
    PackMetaRepository repository;

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

    private PackMetaEntity meta(PackType type, ProjectEntity owner) {
        return persist(new PackMetaEntity(null, owner, type, "{}", 82, 0, 82, null, Set.of()));
    }

    @Test
    @DisplayName("findByProjectId() returns only the pack meta of the project")
    void findsOnlyMetaOfProject() {
        var data = meta(PackType.DATA, project);
        var resource = meta(PackType.RESOURCE, project);
        meta(PackType.DATA, otherProject);

        var found = repository.findByProjectId(project.getId());

        assertEquals(
                List.of(data.getId(), resource.getId()).stream().sorted().toList(),
                found.stream().map(PackMetaEntity::getId).sorted().toList()
        );
    }

    @Test
    @DisplayName("findByProjectIdAndPackType() returns the pack meta of the given type")
    void findsByType() {
        meta(PackType.DATA, project);
        var resource = meta(PackType.RESOURCE, project);

        assertEquals(resource.getId(),
                repository.findByProjectIdAndPackType(project.getId(), PackType.RESOURCE).orElseThrow().getId());
        assertTrue(repository.findByProjectIdAndPackType(otherProject.getId(), PackType.RESOURCE).isEmpty());
    }
}
