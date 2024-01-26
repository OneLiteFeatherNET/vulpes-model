package net.theevilreaper.vulpes.api.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/
@Document("notifications")
data class NotificationModel(
    @Id val id: String?,
    val modelName: String?,
    val name: String?,
    val description: String?,
    val material: String?,
    val frameType: String?,
    val title: String?
) : VulpesModel
