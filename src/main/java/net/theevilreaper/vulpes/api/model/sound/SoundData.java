package net.theevilreaper.vulpes.api.model.sound;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import net.theevilreaper.vulpes.api.model.VulpesModel;

@Serdeable
@MappedEntity("sound_data")
public record SoundData(
        @Id String id,
        String name,
        String source,
        double volume,
        double pitch
) implements VulpesModel {
}
