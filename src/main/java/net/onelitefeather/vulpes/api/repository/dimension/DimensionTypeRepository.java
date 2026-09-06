package net.onelitefeather.vulpes.api.repository.dimension;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.dimension.DimensionTypeEntity;

import java.util.UUID;

/**
 * The {@link DimensionTypeRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link DimensionTypeEntity} objects.
 * <p>
 * Note: unlike some sibling repositories, this intentionally has no combined "fetch with attributes
 * and timelines" query - both are {@code @OneToMany} lists (bags), and Hibernate cannot join-fetch two
 * bags in a single query ({@code MultipleBagFetchException}). Use
 * {@code DimensionAttributeRepository}/{@code DimensionTimelineRepository} to load them separately.
 * </p>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.9.0
 */
@Repository
public interface DimensionTypeRepository extends PageableRepository<DimensionTypeEntity, UUID> {

    /**
     * Retrieves all dimension types that belong to a specific project.
     *
     * @param projectId the unique identifier of the project
     * @param pageable  the pagination information
     * @return a page of DimensionTypeEntity objects belonging to the project
     */
    Page<DimensionTypeEntity> findByProjectId(UUID projectId, Pageable pageable);
}
