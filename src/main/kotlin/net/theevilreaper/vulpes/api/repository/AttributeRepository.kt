package net.theevilreaper.vulpes.api.repository

import net.theevilreaper.vulpes.api.model.AttributeModel
import org.springframework.data.mongodb.repository.MongoRepository

interface AttributeRepository : MongoRepository<AttributeModel, String>
