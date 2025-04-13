package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.FontModel;

import java.util.UUID;

@Repository
public interface FontRepository extends PageableRepository<FontModel, UUID> {
}
