package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.FontEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface FontRepository extends PageableRepository<FontEntity, UUID> {

    /**
     * Retrieves the characters associated with a font by its ID.
     *
     * @param id the unique identifier of the font
     * @return a list of characters associated with the font
     */
    @Query("SELECT c FROM fonts f JOIN f.chars c WHERE f.id = :id")
    List<String> findCharsByFontId(UUID id);
}
