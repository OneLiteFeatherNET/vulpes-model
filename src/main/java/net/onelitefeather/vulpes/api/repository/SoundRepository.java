package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.sound.SoundEventEntity;

import java.util.UUID;

/**
 * The {@link SoundRepository} interface defines a crud repository with the pageable functionality to manage
 * different {@link SoundEventEntity} entities in a database.
 *
 * @author theEvilReaper
 * @version 1.5.0
 * @since 0.1.0
 */
@Repository
public interface SoundRepository extends PageableRepository<SoundEventEntity, UUID> {
}
