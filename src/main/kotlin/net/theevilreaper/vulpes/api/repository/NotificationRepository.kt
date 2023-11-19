package net.theevilreaper.vulpes.api.repository

import net.theevilreaper.vulpes.api.model.NotificationModel
import org.springframework.data.mongodb.repository.MongoRepository


/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/

interface NotificationRepository : MongoRepository<NotificationModel, String>