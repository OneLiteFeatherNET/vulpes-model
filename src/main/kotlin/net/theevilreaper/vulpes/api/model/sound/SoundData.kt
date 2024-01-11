package net.theevilreaper.vulpes.api.model.sound

import org.springframework.data.annotation.Id

/**
 * The [SoundData] includes the fields to represent a sound file object from the game Minecraft.
 * @since 1.0.0
 * @author theEvilReaper
 * @see <a href="https://minecraft.wiki/w/Sounds.json">Sounds.json</a>
 */
data class SoundData(
    @Id val id: String?,
    val name: String?,
    val source: String?,
    val volume: Double?,
    val pitch: Double?
)
