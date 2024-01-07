package net.theevilreaper.vulpes.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * The model class describes the data structure to represent an attribute from the game Minecraft.
 * @since 1.0.0
 * @author theEvilReaper
 */
@Document("attributes")
data class AttributeModel(
    @Id
    val id: String?,
    val modelName: String?,
    val name: String?,
    val defaultValue: Double?,
    val maximumValue: Double?
) : VulpesModel