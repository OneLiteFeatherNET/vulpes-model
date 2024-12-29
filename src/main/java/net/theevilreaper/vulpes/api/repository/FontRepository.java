package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.PageableRepository;

@MongoRepository
public interface FontRepository extends PageableRepository<FontRepository, String> {
}
