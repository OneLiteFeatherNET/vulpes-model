package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.ItemEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The {@link ItemRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link ItemEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
@Repository
public interface ItemRepository extends PageableRepository<ItemEntity, UUID> {

    /**
     * Retrieves the enchantments associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a map of enchantment names and their levels associated with the item
     */
    @Query(value = "SELECT e FROM items i JOIN i.enchantments e WHERE i.id = :id",
            countQuery = "SELECT count(e) FROM items i JOIN i.enchantments e WHERE i.id = :id"
    )
    Map<String, Short> findEnchantmentsById(UUID id, Pageable pageable);

    /**
     * Retrieves the lore associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of lore strings associated with the item
     */
    @Query(value = "SELECT l FROM items i JOIN i.lore l WHERE i.id = :id",
            countQuery = "SELECT count(l) FROM items i JOIN i.lore l WHERE i.id = :id"
    )
    List<String> findLoreById(UUID id, Pageable pageable);

    /**
     * Retrieves the flags associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of flags associated with the item
     */
    @Query(value = "SELECT f FROM items i JOIN i.flags f WHERE i.id = :id",
            countQuery = "SELECT count(f) FROM items i JOIN i.flags f WHERE i.id = :id"
    )
    List<String> findFlagsById(UUID id, Pageable pageable);


    /**
     * Retrieves all items along with their associated enchantments, lore, and flags.
     *
     * @return a list of all ItemEntity objects with their enchantments, lore, and flags
     */
    @Query(
            value = "SELECT i FROM items i LEFT JOIN FETCH i.enchantments LEFT JOIN FETCH i.lore LEFT JOIN FETCH i.flags",
            countQuery = "SELECT count(i) FROM items i"
    )
    List<ItemEntity> findAllWithFetches(Pageable pageable);
}
