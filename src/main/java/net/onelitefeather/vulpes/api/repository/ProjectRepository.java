package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.NotificationEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.UUID;

/**
 * The {@link ProjectRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link NotificationEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.7.2
 */
@Repository
public interface ProjectRepository extends PageableRepository<ProjectEntity, UUID> {
}
