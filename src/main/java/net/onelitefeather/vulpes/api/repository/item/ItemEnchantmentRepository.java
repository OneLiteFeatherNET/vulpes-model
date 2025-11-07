package net.onelitefeather.vulpes.api.repository.item;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.item.ItemEnchantmentEntity;

import java.util.UUID;

/**
 * Repository definition to manage {@link ItemEnchantmentEntity} entities.
 *
 * @version 1.0.0
 * @since 1.7.0
 * @author theEvilReaper
 */
@Repository
public interface ItemEnchantmentRepository extends PageableRepository<ItemEnchantmentEntity, UUID> {

    /**
     * Retrieves the enchantments associated with an item by its ID.
     *
     * @param id the unique identifier of the item
     * @return a list of enchantments associated with the item
     */
    @Query(value = "SELECT f FROM item_enchantments f WHERE f.item.id = :id",
            countQuery = "SELECT count(f) FROM item_enchantments f WHERE f.item.id = :id"
    )
    Page<ItemEnchantmentEntity> findEnchantmentsById(UUID id, Pageable pageable);
}
