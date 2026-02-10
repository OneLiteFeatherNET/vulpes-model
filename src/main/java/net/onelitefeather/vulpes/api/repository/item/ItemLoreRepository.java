package net.onelitefeather.vulpes.api.repository.item;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.item.ItemLoreEntity;

import java.util.UUID;

/**
 * Repository definition to manage {@link ItemLoreEntity} entities.
 *
 * @author theEvilReaper
 * @version 1.1.0
 * @since 1.7.0
 */
@Repository
public interface ItemLoreRepository extends PageableRepository<ItemLoreEntity, UUID> {

    /**
     * Retrieves the lore associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of lore strings associated with the item
     */
    @Query(value = "SELECT f FROM item_lore f WHERE f.item.id = :id",
            countQuery = "SELECT count(f) FROM item_lore f WHERE f.item.id = :id"
    )
    Page<ItemLoreEntity> findLoreById(UUID id, Pageable pageable);

    /**
     * Counts the number of lore entries associated with an item.
     *
     * @param itemId the unique identifier of the item
     * @return the count of lore entries
     */
    @Query("SELECT COUNT(l) FROM item_lore l WHERE l.item.id = :itemId")
    long countByItemId(UUID itemId);
}
