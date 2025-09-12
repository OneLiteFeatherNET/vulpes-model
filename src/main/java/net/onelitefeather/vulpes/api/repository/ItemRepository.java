package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
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
    @Query("SELECT e FROM items i JOIN i.enchantments e WHERE i.id = :id")
    Map<String, Short> findEnchantmentsById(UUID id);

    /**
     * Retrieves the lore associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of lore strings associated with the item
     */
    @Query("SELECT l FROM items i JOIN i.enchantments l WHERE i.id = :id")
    List<String> findLoreById(UUID id);

    /**
     * Retrieves the flags associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of flags associated with the item
     */
    @Query("SELECT f FROM items i JOIN i.enchantments f WHERE i.id = :id")
    List<String> findFlagsById(UUID id);


    /**
     * Retrieves all items along with their associated enchantments, lore, and flags.
     *
     * @return a list of all ItemEntity objects with their enchantments, lore, and flags
     */
    @Query("SELECT i FROM items i LEFT JOIN FETCH i.enchantments LEFT JOIN FETCH i.lore LEFT JOIN FETCH i.flags")
    List<ItemEntity> findAllWithFetches();
}
