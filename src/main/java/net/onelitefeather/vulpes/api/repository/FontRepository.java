package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.FontEntity;

import java.util.List;
import java.util.UUID;

/**
 * The {@link FontRepository} interface inherits from {@link PageableRepository} and provides methods to manage {@link FontEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * * @since 0.1.0
 */
@Repository
public interface FontRepository extends PageableRepository<FontEntity, UUID> {

    /**
     * Retrieves the characters associated with a font by its ID.
     *
     * @param id the unique identifier of the font
     * @return a list of characters associated with the font
     */
    @Query(
            value = "SELECT c FROM fonts f JOIN f.chars c WHERE f.id = :id",
            countQuery = "SELECT COUNT(c) FROM fonts f JOIN f.chars c WHERE f.id = :id"
    )
    List<String> findCharsByFontId(UUID id, Pageable pageable);

    /**
     * Retrieves all fonts along with their associated characters.
     *
     * @return a list of all FontEntity objects with their characters
     */
    @Query("select f from fonts f JOIN FETCH f.chars")
    List<FontEntity> findAll();
}
