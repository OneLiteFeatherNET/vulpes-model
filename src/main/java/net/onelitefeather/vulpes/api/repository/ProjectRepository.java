package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * The {@link ProjectRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link ProjectEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.7.2
 */
@Repository
public interface ProjectRepository extends PageableRepository<ProjectEntity, UUID> {

    /**
     * Retrieves the {@link ProjectEntity} with the given key.
     * The key is unique across all projects, so at most one project can match.
     *
     * @param key the key of the project
     * @return an {@link Optional} containing the project, or an empty one if no project has that key
     */
    Optional<ProjectEntity> findByKey(String key);
}
