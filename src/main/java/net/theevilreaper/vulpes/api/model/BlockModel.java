package net.theevilreaper.vulpes.api.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 **/
@Serdeable
@MappedEntity("blocks")
public record BlockModel(
        @Id String id,
        String modelName,
        String name,
        String material,
        int customModelData
) implements VulpesModel {
}
