package net.onelitefeather.vulpes.api.model.item;

import java.util.Set;

/**
 * The keys of the components which Stelaris adds on top of the vanilla data components.
 * <p>
 * They describe the item stack itself and are stored like any other {@link ItemComponentEntity}. No codec
 * reads them, the generator translates them into the item stack. The specs which describe their values for
 * a user interface are part of the vulpes data package.
 * </p>
 */
public final class StelarisComponents {

    /**
     * The material of the item, e.g. {@code "minecraft:stone"}.
     */
    public static final String MATERIAL = "stelaris:material";

    /**
     * The amount of the item stack, a whole number from 1 to 99. An item without it has an amount of 1.
     */
    public static final String AMOUNT = "stelaris:amount";

    /**
     * The components which every item has. They are created with the item and can't be removed.
     */
    public static final Set<String> REQUIRED = Set.of(MATERIAL);

    /**
     * All components in the {@code stelaris} namespace. Other keys in that namespace are rejected.
     */
    public static final Set<String> ALL = Set.of(MATERIAL, AMOUNT);

    /**
     * The material a new item starts with.
     */
    public static final String DEFAULT_MATERIAL = "minecraft:dirt";

    /**
     * The namespace of the Stelaris components, including the separator.
     */
    public static final String NAMESPACE = "stelaris:";

    private StelarisComponents() {
        // Only constants
    }
}
