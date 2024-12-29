package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.AttributeModel;

@MongoRepository
public interface AttributeRepository extends PageableRepository<AttributeModel, String> {
}
