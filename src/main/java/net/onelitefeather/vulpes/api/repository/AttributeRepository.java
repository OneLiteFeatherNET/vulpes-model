package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.AttributeEntity;

import java.util.UUID;

/**
 * The {@link AttributeRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link AttributeEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
@Repository
public interface AttributeRepository extends PageableRepository<AttributeEntity, UUID> {
}
