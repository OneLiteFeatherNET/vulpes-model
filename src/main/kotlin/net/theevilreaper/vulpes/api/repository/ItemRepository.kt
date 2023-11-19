package net.theevilreaper.vulpes.api.repository

import net.theevilreaper.vulpes.api.model.ItemModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author theEvilReaper
 * @version 1.0.0
 * @since
 **/

interface ItemRepository : MongoRepository<ItemModel, String>