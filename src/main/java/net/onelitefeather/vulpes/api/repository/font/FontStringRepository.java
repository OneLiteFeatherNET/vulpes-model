package net.onelitefeather.vulpes.api.repository.font;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.font.FontStringEntity;

import java.util.UUID;

/**
 * Repository definition to manage {@link FontStringRepository} entities.
 *
 * @version 1.0.0
 * @since 1.7.0
 * @author theEvilReaper
 */
@Repository
public interface FontStringRepository extends PageableRepository<FontStringEntity, UUID> {

    /**
     * Retrieves the characters associated with a font by its ID.
     *
     * @param id the unique identifier of the font
     * @return a list of characters associated with the font
     */
    @Query(value = "SELECT f FROM font_lore f WHERE f.font.id = :id",
            countQuery = "SELECT count(f) FROM font_lore f WHERE f.font.id = :id"
    )
    Page<FontStringEntity> findCharsByFontId(UUID id, Pageable pageable);
}
