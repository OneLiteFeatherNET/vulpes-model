package net.theevilreaper.vulpes.api.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

/**
 * The model class describes the data structure to represent an attribute from the game Minecraft.
 *
 * @author theEvilReaper
 * @since 1.0.0
 */
@Serdeable
@MappedEntity("attributes")
public record AttributeModel(
        @Id String id,
        String modelName,
        String name,
        double defaultValue,
        double maximumValue
) implements VulpesModel {
}
