package net.theevilreaper.vulpes.api.model;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;
import java.util.Map;

@Serdeable
@MappedEntity("items")
public record ItemModel(
        @Id String id,
        String modelName,
        String name,
        String description,
        String displayName,
        String material,
        String group,
        int customModelData,
        int amount,
        Map<String, Short> enchantments,
        List<String> lore,
        List<String> flags
) implements VulpesModel {

}
