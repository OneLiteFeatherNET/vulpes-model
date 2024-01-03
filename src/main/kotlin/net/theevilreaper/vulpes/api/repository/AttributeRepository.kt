package net.theevilreaper.vulpes.api.repository

import net.theevilreaper.vulpes.api.model.AttributeModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * The repository is used to store different object instances from the [AttributeModel].
 * It extends the [MongoRepository] to provide the basic CRUD operations.
 * @see MongoRepository
 * @author theEvilReaper
 */
interface AttributeRepository : MongoRepository<AttributeModel, String>
