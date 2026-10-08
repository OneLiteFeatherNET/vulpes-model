package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.advancement.AdvancementEntity;

import java.util.List;
import java.util.UUID;

/**
 * The {@link AdvancementRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link AdvancementEntity} objects.
 *
 * @author theEvilReaper
 * @version 2.0.0
 * @since 0.1.0
 */
@Repository
public interface AdvancementRepository extends PageableRepository<AdvancementEntity, UUID> {

    /**
     * Retrieves all advancements that belong to a specific project.
     *
     * @param projectId the unique identifier of the project
     * @param pageable  the pagination information
     * @return a page of AdvancementEntity objects belonging to the project
     */
    Page<AdvancementEntity> findByProjectId(UUID projectId, Pageable pageable);

    /**
     * Retrieves all root advancements of a specific project, each of them opens its own tab.
     *
     * @param projectId the unique identifier of the project
     * @return the advancements of the project without a parent
     */
    List<AdvancementEntity> findByProjectIdAndParentIsNull(UUID projectId);

    /**
     * Retrieves the direct children of an advancement.
     *
     * @param parentId the unique identifier of the parent advancement
     * @return the advancements whose parent is the given advancement
     */
    List<AdvancementEntity> findByParentId(UUID parentId);

    /**
     * Checks whether a {@link AdvancementEntity} with the given key already exists within a specific project.
     *
     * @param projectId the unique identifier of the project
     * @param key       the key to check for
     * @return {@code true} if an advancement with that key exists in the project, {@code false} otherwise
     */
    boolean existsByProjectIdAndKey(UUID projectId, String key);
}
