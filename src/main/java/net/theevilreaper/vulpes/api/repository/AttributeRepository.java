package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.AttributeModel;

import java.util.UUID;

@Repository
public interface AttributeRepository extends PageableRepository<AttributeModel, UUID> {
}
