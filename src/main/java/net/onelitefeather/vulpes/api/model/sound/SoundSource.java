package net.onelitefeather.vulpes.api.model.sound;

/**
 * The category a {@link SoundEventEntity} plays in. Each one is a volume slider in the sound options of the client.
 * <p>
 * Mirrors {@code net.kyori.adventure.sound.Sound.Source}.
 * </p>
 *
 * @author Joltras
 * @version 1.0.0
 * @since 3.11.0
 */
public enum SoundSource {
    MASTER,
    MUSIC,
    RECORD,
    WEATHER,
    BLOCK,
    HOSTILE,
    NEUTRAL,
    PLAYER,
    AMBIENT,
    VOICE,
    UI
}
