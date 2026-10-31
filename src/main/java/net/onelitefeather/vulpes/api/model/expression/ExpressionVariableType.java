package net.onelitefeather.vulpes.api.model.expression;

/**
 * The type a value in an expression is converted to before the expression is evaluated: the value of an
 * {@link ExpressionVariable} or what an {@link ExpressionFunctionEntity} returns.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.6.0
 */
public enum ExpressionVariableType {
    NUMBER,
    BOOLEAN,
    STRING
}
