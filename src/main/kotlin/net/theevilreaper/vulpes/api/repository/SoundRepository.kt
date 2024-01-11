package net.theevilreaper.vulpes.api.repository

import net.theevilreaper.vulpes.api.model.sound.SoundModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * The repository is used to store different object instances from the [SoundModel].
 * It extends the [MongoRepository] to provide the basic CRUD operations.
 * @see MongoRepository
 * @author theEvilReaper
 */
interface SoundRepository : MongoRepository<SoundModel, String>