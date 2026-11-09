package net.onelitefeather.vulpes.api.model.asset;

/**
 * The namespace in which an asset key must be unique within a project. Textures form one key space,
 * Minecraft models and Blockbench models the second and sounds the third, so a texture, a model and a
 * sound may share a key.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.5.0
 */
public enum AssetKeySpace {

    /**
     * The key space of PNG textures.
     */
    TEXTURE,

    /**
     * The key space of Minecraft models and Blockbench models.
     */
    MODEL,

    /**
     * The key space of sounds.
     */
    SOUND;

    /**
     * Returns the key space an asset of the given kind lives in.
     *
     * @param kind the kind of the asset
     * @return the key space of the kind
     */
    public static AssetKeySpace of(AssetKind kind) {
        return switch (kind) {
            case TEXTURE -> TEXTURE;
            case MODEL, BBMODEL -> MODEL;
            case SOUND -> SOUND;
        };
    }
}
