package net.onelitefeather.vulpes.api.model.asset;

/**
 * The kind of project asset. It follows from the file extension and the content of the upload.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.3.0
 */
public enum AssetKind {

    /**
     * A PNG texture. Its width and height are known.
     */
    TEXTURE("image/png"),

    /**
     * A Minecraft model, a JSON object.
     */
    MODEL("application/json"),

    /**
     * A Blockbench model, a JSON object whose embedded textures are extracted as {@link #TEXTURE}.
     */
    BBMODEL("application/json"),;

    private final String contentType;

    /**
     * Creates a new instance of an enum entry with the given values-
     *
     * @param contentType of the kind
     */
    AssetKind(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Returns the media type the bytes of this kind are stored and served with.
     *
     * @return the content type
     */
    public String contentType() {
        return contentType;
    }
}
