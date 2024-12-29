package net.theevilreaper.vulpes.api.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity("notifications")
public record NotificationModel(
        @Id String id,
        String modelName,
        String name,
        String description,
        String material,
        String frameType,
        String title
) implements VulpesModel{
}
