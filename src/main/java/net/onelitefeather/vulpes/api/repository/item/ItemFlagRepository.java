package net.onelitefeather.vulpes.api.repository.item;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.item.ItemFlagEntity;

import java.util.UUID;

/**
 * Repository definition to manage {@link ItemFlagEntity} entities.
 *
 * @version 1.0.0
 * @since 1.7.0
 * @author theEvilReaper
 */
@Repository
public interface ItemFlagRepository extends PageableRepository<ItemFlagEntity, UUID> {

    /**
     * Retrieves the flags associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of flags associated with the item
     */
    @Query(value = "SELECT f FROM item_flags f WHERE f.item.id = :id",
            countQuery = "SELECT count(f) FROM item_flags f WHERE f.item.id = :id"
    )
    Page<ItemFlagEntity> findFlagsById(UUID id, Pageable pageable);
}
