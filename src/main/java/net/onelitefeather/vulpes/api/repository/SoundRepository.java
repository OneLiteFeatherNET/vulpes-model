package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.sound.SoundEntity;

import java.util.UUID;

@Repository
public interface SoundRepository extends PageableRepository<SoundEntity, UUID> {
}
