package net.onelitefeather.vulpes.api.model.asset;

/**
 * How a linked asset is used by its target. A {@link #TEXTURE} link requires a texture asset, a
 * {@link #MODEL} link requires a model or a Blockbench model.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.4.0
 */
public enum AssetLinkRole {
    TEXTURE,
    MODEL
}
