package net.onelitefeather.vulpes.api.model.dimension;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a single environment attribute entry attached to a {@link DimensionTypeEntity}.
 * <p>
 * A dimension type can carry an arbitrary set of environment attributes (key/value pairs), which is a
 * repeating group and therefore modelled as its own table rather than a column on {@link DimensionTypeEntity}.
 * An entry is not a plain key/value pair - it combines an {@link AttributeOperator} with an argument
 * (e.g. "override with X" vs. "alpha-blend with X"). The argument is stored as its serialized (string)
 * representation; interpreting it back into a typed environment attribute value is the responsibility
 * of the application layer.
 * </p>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.9.0
 */
@Entity(name = "dimension_attributes")
@Table(name = "dimension_attributes", indexes = {
        @Index(name = "idx_dimension_attributes_type_key", columnList = "dimension_type_id, attribute_key", unique = true)
})
public final class DimensionAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnvironmentAttributeKey attributeKey;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttributeOperator operator;

    @NotNull
    private String attributeValue;

    @ManyToOne
    @JoinColumn(name = "dimension_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DimensionTypeEntity dimensionType;

    /**
     * Default constructor for JPA and Micronaut Data.
     */
    public DimensionAttributeEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link DimensionAttributeEntity} with the specified values.
     *
     * @param id             the unique identifier of the attribute entry
     * @param attributeKey   the key identifying the environment attribute
     * @param operator       the operator used to combine the argument with the base value
     * @param attributeValue the serialized argument of the environment attribute
     */
    public DimensionAttributeEntity(
            UUID id,
            EnvironmentAttributeKey attributeKey,
            AttributeOperator operator,
            String attributeValue
    ) {
        this.id = id;
        this.attributeKey = attributeKey;
        this.operator = operator;
        this.attributeValue = attributeValue;
    }

    /**
     * Sets the unique identifier of the attribute entry.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the attribute entry.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the key identifying the environment attribute.
     *
     * @param attributeKey the attribute key to set
     */
    public void setAttributeKey(EnvironmentAttributeKey attributeKey) {
        this.attributeKey = attributeKey;
    }

    /**
     * Gets the key identifying the environment attribute.
     *
     * @return the attribute key
     */
    public EnvironmentAttributeKey getAttributeKey() {
        return attributeKey;
    }

    /**
     * Sets the serialized argument of the environment attribute.
     *
     * @param attributeValue the attribute argument to set
     */
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    /**
     * Gets the serialized argument of the environment attribute.
     *
     * @return the attribute argument
     */
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * Sets the operator used to combine the argument with the base value.
     *
     * @param operator the operator to set
     */
    public void setOperator(AttributeOperator operator) {
        this.operator = operator;
    }

    /**
     * Gets the operator used to combine the argument with the base value.
     *
     * @return the operator
     */
    public AttributeOperator getOperator() {
        return operator;
    }

    /**
     * Sets the dimension type associated with this attribute entry.
     *
     * @param dimensionType the dimension type to set
     */
    public void setDimensionType(DimensionTypeEntity dimensionType) {
        this.dimensionType = dimensionType;
    }

    /**
     * Gets the dimension type associated with this attribute entry.
     *
     * @return the associated dimension type
     */
    public DimensionTypeEntity getDimensionType() {
        return dimensionType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DimensionAttributeEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.attributeKey, that.attributeKey) &&
                this.operator == that.operator &&
                Objects.equals(this.attributeValue, that.attributeValue) &&
                Objects.equals(this.dimensionType, that.dimensionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attributeKey, operator, attributeValue, dimensionType);
    }

    @Override
    public String toString() {
        return "DimensionAttributeEntity[" +
                "id=" + id + ", " +
                "attributeKey=" + attributeKey + ", " +
                "operator=" + operator + ", " +
                "attributeValue=" + attributeValue + ", " +
                "dimensionType=" + dimensionType + ']';
    }
}
