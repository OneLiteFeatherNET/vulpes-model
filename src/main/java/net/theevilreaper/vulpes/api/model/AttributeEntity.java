package net.theevilreaper.vulpes.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.theevilreaper.vulpes.api.generator.VulpesGenerator;

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
public class AttributeEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String modelName;
    private String name;
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
     * @param id            the unique identifier of the attribute
     * @param modelName     the model name associated with the attribute
     * @param name          the name of the attribute
     * @param defaultValue  the default value of the attribute
     * @param maximumValue  the maximum value of the attribute
     */
    public AttributeEntity(UUID id, String modelName, String name, double defaultValue, double maximumValue) {
        this.id = id;
        this.modelName = modelName;
        this.name = name;
        this.defaultValue = defaultValue;
        this.maximumValue = maximumValue;
    }

    // Getters and setters for each field

    /**
     * Returns the unique identifier of the attribute
     *
     * @return the unique identifier of the attribute
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the attribute
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the model name associated with the attribute
     *
     * @return the model name of the attribute
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the model name associated with the attribute
     *
     * @param modelName the model name to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Returns the name of the attribute
     *
     * @return the name of the attribute
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the attribute
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
                "id='" + id + '\'' +
                ", modelName='" + modelName + '\'' +
                ", name='" + name + '\'' +
                ", defaultValue=" + defaultValue +
                ", maximumValue=" + maximumValue +
                '}';
    }
}
