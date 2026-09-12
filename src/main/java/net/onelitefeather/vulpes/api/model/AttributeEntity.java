package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
})
public class AttributeEntity extends AbstractEntity {

    private String uiName;
    private String key;
    private double defaultValue;
    private double maximumValue;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity project;

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
     * @param key          the namespaced key of the attribute (e.g. {@code minecraft:generic.max_health})
     * @param defaultValue the default value of the attribute
     * @param maximumValue the maximum value of the attribute
     * @param project      the project this attribute belongs to
     */
    public AttributeEntity(UUID id, String uiName, String key, double defaultValue, double maximumValue, ProjectEntity project) {
        this.setId(id);
        this.uiName = uiName;
        this.key = key;
        this.defaultValue = defaultValue;
        this.maximumValue = maximumValue;
        this.project = project;
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
     * Returns the namespaced key of the attribute (e.g. {@code minecraft:generic.max_health}).
     *
     * @return the namespaced key of the attribute
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the namespaced key of the attribute (e.g. {@code minecraft:generic.max_health}).
     *
     * @param key the namespaced key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Derives the variable name of the attribute from its namespaced key, e.g. {@code minecraft:generic.max_health}
     * becomes {@code GENERIC.MAX_HEALTH}.
     *
     * @return the derived variable name of the attribute
     */
    public String getVariableName() {
        int separatorIndex = key.indexOf(':');
        return (separatorIndex >= 0 ? key.substring(separatorIndex + 1) : key).toUpperCase();
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
     * Returns the project this attribute belongs to.
     *
     * @return the project of the attribute
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets the project this attribute belongs to.
     *
     * @param project the project to set
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
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
                ", key='" + key + '\'' +
                ", defaultValue=" + defaultValue +
                ", maximumValue=" + maximumValue +
                ", project=" + project +
                '}';
    }
}
