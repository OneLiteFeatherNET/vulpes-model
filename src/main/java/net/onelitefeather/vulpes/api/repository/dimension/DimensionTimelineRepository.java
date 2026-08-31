package net.onelitefeather.vulpes.api.repository.dimension;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.dimension.DimensionTimelineEntity;

import java.util.UUID;

/**
 * Repository definition to manage {@link DimensionTimelineEntity} entities.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.9.0
 */
@Repository
public interface DimensionTimelineRepository extends PageableRepository<DimensionTimelineEntity, UUID> {

    /**
     * Retrieves the timeline references associated with a dimension type by its ID.
     *
     * @param id       the unique identifier of the dimension type
     * @param pageable the pagination information
     * @return a page of timeline references associated with the dimension type
     */
    @Query(value = "SELECT d FROM dimension_timelines d WHERE d.dimensionType.id = :id",
            countQuery = "SELECT count(d) FROM dimension_timelines d WHERE d.dimensionType.id = :id"
    )
    Page<DimensionTimelineEntity> findTimelinesByDimensionTypeId(UUID id, Pageable pageable);
}
