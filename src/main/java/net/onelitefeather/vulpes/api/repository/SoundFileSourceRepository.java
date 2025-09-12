package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.sound.SoundFileSource;

import java.util.List;
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

    /**
     * Retrieves the sound file sources associated with a sound event by its ID.
     *
     * @param id the unique identifier of the sound event
     * @param pageable the pagination information
     * @return a list of sound file sources associated with the sound event
     */
    @Query(value = "SELECT f FROM sound_data f WHERE f.soundEvent.id = :id",
    countQuery = "SELECT count(f) FROM sound_data f WHERE f.soundEvent.id = :id")
    Page<SoundFileSource> findSoundFileSourcesBySoundEvent(UUID id, Pageable pageable);

}
