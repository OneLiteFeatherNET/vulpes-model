package net.theevilreaper.vulpes.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("attributes")
data class AttributeModel(
    @Id
    val id: String?,
    val modelName: String?,
    val name: String?,
    val defaultValue: Double?,
    val maximumValue: Double?
) : VulpesModel