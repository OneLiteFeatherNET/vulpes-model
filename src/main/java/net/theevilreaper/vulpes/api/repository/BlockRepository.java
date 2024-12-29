package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.BlockModel;

@MongoRepository
public interface BlockRepository extends PageableRepository<BlockModel, String> {
}
