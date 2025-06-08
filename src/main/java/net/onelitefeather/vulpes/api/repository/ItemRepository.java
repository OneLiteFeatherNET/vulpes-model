package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.ItemEntity;

import java.util.UUID;

@Repository
public interface ItemRepository extends PageableRepository<ItemEntity, UUID> {
}
