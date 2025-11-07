package net.onelitefeather.vulpes.api.model.font;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

import java.util.UUID;

@Entity(name = "font_lore")
public record FontLoreEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @VulpesGenerator
        UUID id,
        String line
) {
}
