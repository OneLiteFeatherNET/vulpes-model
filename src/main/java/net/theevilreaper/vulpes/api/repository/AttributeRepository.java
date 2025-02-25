package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.AttributeModel;

@Repository
public interface AttributeRepository extends PageableRepository<AttributeModel, String> {
}
