package net.theevilreaper.vulpes.api.model.sound;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import net.theevilreaper.vulpes.api.model.VulpesModel;

import java.util.List;

@Serdeable
@MappedEntity("sounds")
public record SoundModel(
        @Id String id,
        String modelName,
        String source,
        List<SoundData> soundData
) implements VulpesModel {
}
