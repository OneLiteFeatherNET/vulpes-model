package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.NotificationEntity;
import net.onelitefeather.vulpes.api.model.sound.SoundFileSource;

import java.util.UUID;

/**
 * The {@link SoundFileSourceRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link SoundFileSource} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.5.1
 */
@Repository
public interface SoundFileSourceRepository extends PageableRepository<SoundFileSource, UUID> {
}
