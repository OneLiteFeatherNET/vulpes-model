package net.onelitefeather.vulpes.api.model.pack;

/**
 * The type of pack a {@link PackMetaEntity} describes. Data packs and resource packs have separate format numbers.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 3.8.0
 */
public enum PackType {

    /**
     * A data pack, placed in the {@code datapacks} directory of a world.
     */
    DATA,

    /**
     * A resource pack, loaded by the client.
     */
    RESOURCE
}
