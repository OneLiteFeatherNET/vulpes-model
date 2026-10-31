package net.onelitefeather.vulpes.api.model.expression;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * A variable an {@link ExpressionEntity} reads, or a parameter of an {@link ExpressionFunctionEntity}.
 *
 * <p>It is stored as an element collection of its owner, so an order column keeps its position in the
 * list.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.6.0
 */
@Embeddable
public class ExpressionVariable {

    @Column(name = "variable_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "variable_type", nullable = false)
    private ExpressionVariableType type;

    @Nullable
    @Column(name = "test_value")
    private String testValue;

    /**
     * Creates an empty variable, required by JPA.
     */
    protected ExpressionVariable() {
    }

    /**
     * Creates a stored variable.
     *
     * @param name      the variable name
     * @param type      the type the value is converted to
     * @param testValue the value used when the expression is tested, may be {@code null}
     */
    public ExpressionVariable(String name, ExpressionVariableType type, @Nullable String testValue) {
        this.name = name;
        this.type = type;
        this.testValue = testValue;
    }

    public String getName() {
        return name;
    }

    public ExpressionVariableType getType() {
        return type;
    }

    public @Nullable String getTestValue() {
        return testValue;
    }
}
