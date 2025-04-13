package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.sound.SoundModel;

import java.util.UUID;

@Repository
public interface SoundRepository extends PageableRepository<SoundModel, UUID> {
}
