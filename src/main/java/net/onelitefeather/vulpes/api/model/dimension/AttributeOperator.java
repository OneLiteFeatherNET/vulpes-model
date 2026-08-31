package net.onelitefeather.vulpes.api.model.dimension;

/**
 * Represents the operator used to combine an attribute's argument with its base value.
 * <p>
 * Mirrors {@code net.minestom.server.world.attribute.EnvironmentAttribute.Modifier.Operator}.
 * Not every operator is valid for every attribute value type (e.g. {@code AND}/{@code OR} only
 * apply to boolean attributes, {@code BLEND_TO_GRAY} only to color attributes) - validating that
 * combination is left to the application layer.
 * </p>
 */
public enum AttributeOperator {
    OVERRIDE,
    ALPHA_BLEND,
    ADD,
    SUBTRACT,
    MULTIPLY,
    BLEND_TO_GRAY,
    MINIMUM,
    MAXIMUM,
    AND,
    NAND,
    OR,
    NOR,
    XOR,
    XNOR
}
