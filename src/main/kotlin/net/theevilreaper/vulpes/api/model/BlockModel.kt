package net.theevilreaper.vulpes.api.model

import net.theevilreaper.vulpes.api.util.hasValidNameSpace
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/
@Document("blocks")
data class BlockModel(
    @Id val id: String?,
    val modelName: String,
    val name: String?,
    val material: String?,
    val generator: String = "BlockGenerator",
    val customModelId: Int?,
) : VulpesModel {

    val hasValidMaterial: Boolean
        get() = !material.isNullOrEmpty() && hasValidNameSpace(material)
}
