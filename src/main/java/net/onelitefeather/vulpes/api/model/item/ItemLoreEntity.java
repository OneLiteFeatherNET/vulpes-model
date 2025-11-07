package net.onelitefeather.vulpes.api.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

import java.util.UUID;

/**
 * The {@link ItemLoreEntity} represents a lore entry associated with an item.
 *
 * @param id   the unique identifier of the lore entry
 * @param text the lore text
 */
@Entity(name = "item_lore")
public record ItemLoreEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @VulpesGenerator
        UUID id,
        String text
) {
}
