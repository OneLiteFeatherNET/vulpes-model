package net.theevilreaper.vulpes.api.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
@MappedEntity("fonts")
public record FontModel(
        @Id String id,
        String modelName,
        String name,
        String description,
        String type,
        int ascent,
        int height,
        List<String> chars,
        List<Double> shift
) implements VulpesModel {
}
