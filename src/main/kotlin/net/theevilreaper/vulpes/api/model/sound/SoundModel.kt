package net.theevilreaper.vulpes.api.model.sound

import net.theevilreaper.vulpes.api.model.VulpesModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * The [SoundModel] contains all relevant fields to represent a sound object from the game as object
 * in a specific database.
 * @since 1.0.0
 * @author theEvilReaper
 * @see <a href="https://minecraft.wiki/w/Sounds.json">Sounds.json</a>
 */
@Document("sounds")
data class SoundModel(
    @Id val id: String?,
    val modelName: String?,
    val source: String?,
    val soundData: List<SoundData> = listOf()
) : VulpesModel
