package net.theevilreaper.vulpes.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/
@Document("items")
data class ItemModel(
    @Id val id: String?,
    val modelName: String,
    val name: String?,
    val description: String?,
    val displayName: String?,
    val material: String?,
    val group: String?,
    val generatorKey: String = "ItemGenerator",
    val customModelId: Int?,
    val amount: Int?,
    val enchantments: Map<String, Short>?,
    val lore: List<String>?,
    val flags: List<String>?
) : VulpesModel
