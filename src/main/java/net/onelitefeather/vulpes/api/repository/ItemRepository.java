package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.ItemEntity;

import java.util.List;
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
