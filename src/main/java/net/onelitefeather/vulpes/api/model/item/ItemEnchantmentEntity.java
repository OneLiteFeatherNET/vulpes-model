package net.onelitefeather.vulpes.api.model.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

import java.util.UUID;

@Entity(name = "item_enchantments")
public record ItemEnchantmentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        @VulpesGenerator
        UUID id,
        String name,
        short level
) {
}
