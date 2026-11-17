package net.onelitefeather.vulpes.api.repository.blockstate;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.blockstate.BlockStateEntity;

import java.util.List;
import java.util.UUID;

/**
 * The {@link BlockStateRepository} interface defines a crud repository with the pageable functionality to manage
 * {@link BlockStateEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 3.12.0
 */
@Repository
public interface BlockStateRepository extends PageableRepository<BlockStateEntity, UUID> {

    /**
     * Retrieves the blockstates of a specific project.
     *
     * @param projectId the unique identifier of the project
     * @param pageable  the pagination information
     * @return a page of the blockstates of the project
     */
    Page<BlockStateEntity> findByProjectId(UUID projectId, Pageable pageable);

    /**
     * Retrieves every context of one block in a specific project, whose rules the export writes into one file.
     *
     * @param projectId the unique identifier of the project
     * @param key       the full key of the block, e.g. {@code minecraft:pink_petals}
     * @return the blockstates of the block in the project
     */
    List<BlockStateEntity> findByProjectIdAndKey(UUID projectId, String key);
}
