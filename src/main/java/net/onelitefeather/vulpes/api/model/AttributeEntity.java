package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.UUID;

/**
 * Represents an Attribute in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to an attribute such as name,
 * default value, maximum value, etc.
 * <p>
 * This class is mapped to the database table "vulpes_attributes" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "attributes")
@Table(name = "attributes", indexes = {
        @Index(name = "idx_attributes_project_id", columnList = "project_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_attributes_project_key", columnNames = {"project_id", "key"})
})
public class AttributeEntity extends AbstractEntity {

    private String uiName;
    private double defaultValue;
    private double maximumValue;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public AttributeEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link AttributeEntity} with the specified values.
     *
     * @param id           the unique identifier of the attribute
     * @param uiName       the model name associated with the attribute
     * @param key          the local key of the attribute within the project's namespace
     *                     (e.g. {@code generic.max_health}, becomes {@code <project-key>:generic.max_health}
     *                     via {@link #getNamespacedKey()})
     * @param defaultValue the default value of the attribute
     * @param maximumValue the maximum value of the attribute
     * @param project      the project this attribute belongs to
     */
    public AttributeEntity(UUID id, String uiName, String key, double defaultValue, double maximumValue, ProjectEntity project) {
        super(key, project);
        this.setId(id);
        this.uiName = uiName;
        this.defaultValue = defaultValue;
        this.maximumValue = maximumValue;
    }

    // Getters and setters for each field

    /**
     * Returns the model name associated with the attribute
     *
     * @return the model name of the attribute
     */
    public String getUiName() {
        return uiName;
    }

    /**
     * Sets the model name associated with the attribute
     *
     * @param modelName the model name to set
     */
    public void setUiName(String modelName) {
        this.uiName = modelName;
    }

    /**
     * Returns the default value of the attribute
     *
     * @return the default value of the attribute
     */
    public double getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value of the attribute
     *
     * @param defaultValue the default value to set
     */
    public void setDefaultValue(double defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the maximum value of the attribute
     *
     * @return the maximum value of the attribute
     */
    public double getMaximumValue() {
        return maximumValue;
    }

    /**
     * Sets the maximum value of the attribute
     *
     * @param maximumValue the maximum value to set
     */
    public void setMaximumValue(double maximumValue) {
        this.maximumValue = maximumValue;
    }

    /**
     * Provides a string representation of the AttributeModel
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "AttributeModel{" +
                "id='" + getId() + '\'' +
                ", modelName='" + uiName + '\'' +
                ", key='" + getKey() + '\'' +
                ", defaultValue=" + defaultValue +
                ", maximumValue=" + maximumValue +
                ", project=" + getProject() +
                '}';
    }
}
