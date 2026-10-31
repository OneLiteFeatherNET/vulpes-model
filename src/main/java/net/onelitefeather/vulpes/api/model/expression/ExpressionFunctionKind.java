package net.onelitefeather.vulpes.api.model.expression;

/**
 * How an {@link ExpressionFunctionEntity} is implemented.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.6.0
 */
public enum ExpressionFunctionKind {
    /**
     * The body is an EvalEx formula over the parameters. It may call built-in and other project functions.
     */
    FORMULA,
    /**
     * Only the signature is defined. The game runtime implements the function, and a Java interface is
     * generated for it. An optional body is a test stub that makes the function evaluable in the editor.
     */
    INTERFACE
}
