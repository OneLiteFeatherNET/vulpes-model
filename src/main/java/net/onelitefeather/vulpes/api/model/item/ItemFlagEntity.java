package net.onelitefeather.vulpes.api.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

import java.util.UUID;

/**
 * The {@link ItemFlagEntity} represents a flag or attribute that can be associated with an item in the system.
 *
 * @param id   the unique identifier of the item flag
 * @param text the descriptive text of the item flag
 */
@Entity(name = "item_flags")
public record ItemFlagEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @VulpesGenerator
        UUID id,
        String text
) {

}
