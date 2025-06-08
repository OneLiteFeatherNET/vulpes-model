package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.FontEntity;

import java.util.UUID;

@Repository
public interface FontRepository extends PageableRepository<FontEntity, UUID> {
}
