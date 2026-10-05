package net.onelitefeather.vulpes.api.repository.item;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.item.ItemComponentEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemComponentRepository extends PageableRepository<ItemComponentEntity, UUID> {

    @Query(value = "SELECT c FROM item_components c WHERE c.item.id = :id",
            countQuery = "SELECT count(c) FROM item_components c WHERE c.item.id = :id"
    )
    Page<ItemComponentEntity> findComponentsById(UUID id, Pageable pageable);

    Optional<ItemComponentEntity> findByItemIdAndComponentKey(UUID itemId, String componentKey);
}
