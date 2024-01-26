package net.theevilreaper.vulpes.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/
@Document("fonts")
data class FontModel(
    @Id val id: String?,
    val modelName: String?,
    val name: String?,
    val description: String?,
    val type: String?,
    val ascent: Int?,
    val height: Int?,
    val chars: List<Char>?,
    val shift: List<Double>?
) : VulpesModel