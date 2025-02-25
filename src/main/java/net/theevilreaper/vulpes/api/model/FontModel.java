package net.theevilreaper.vulpes.api.model;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.List;

/**
 * Represents a Font in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to a font such as name,
 * type, ascent, height, etc.
 * <p>
 * This class is mapped to the database table "vulpes_fonts" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Serdeable
@Entity(name = "fonts")
@Table(name = "vulpes_fonts", indexes = @Index(columnList = "id"))
@MappedEntity
public class FontModel implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String modelName;
    private String name;
    private String description;
    private String type;
    private int ascent;
    private int height;
    @ElementCollection
    private List<String> chars;
    @ElementCollection
    private List<Double> shift;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public FontModel() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link FontModel} with the specified values.
     *
     * @param id          the unique identifier of the font
     * @param modelName   the model name associated with the font
     * @param name        the name of the font
     * @param description a description of the font
     * @param type        the type of the font
     * @param ascent      the ascent of the font
     * @param height      the height of the font
     * @param chars       the list of characters included in the font
     * @param shift       the list of shift values for the font
     */
    public FontModel(String id, String modelName, String name, String description, String type, int ascent, int height, List<String> chars, List<Double> shift) {
        this.id = id;
        this.modelName = modelName;
        this.name = name;
        this.description = description;
        this.type = type;
        this.ascent = ascent;
        this.height = height;
        this.chars = chars;
        this.shift = shift;
    }

    // Getters and setters for each field

    /**
     * Returns the unique identifier of the font
     *
     * @return the unique identifier of the font
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the font
     *
     * @param id the unique identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the model name associated with the font
     *
     * @return the model name of the font
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the model name associated with the font
     *
     * @param modelName the model name to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Returns the name of the font
     *
     * @return the name of the font
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the font
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the font
     *
     * @return the description of the font
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the font
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the type of the font
     *
     * @return the type of the font
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the font
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the ascent of the font
     *
     * @return the ascent of the font
     */
    public int getAscent() {
        return ascent;
    }

    /**
     * Sets the ascent of the font
     *
     * @param ascent the ascent to set
     */
    public void setAscent(int ascent) {
        this.ascent = ascent;
    }

    /**
     * Returns the height of the font
     *
     * @return the height of the font
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the font
     *
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the list of characters included in the font
     *
     * @return the list of characters in the font
     */
    public List<String> getChars() {
        return chars;
    }

    /**
     * Sets the list of characters included in the font
     *
     * @param chars the list of characters to set
     */
    public void setChars(List<String> chars) {
        this.chars = chars;
    }

    /**
     * Returns the list of shift values for the font
     *
     * @return the list of shift values for the font
     */
    public List<Double> getShift() {
        return shift;
    }

    /**
     * Sets the list of shift values for the font
     *
     * @param shift the list of shift values to set
     */
    public void setShift(List<Double> shift) {
        this.shift = shift;
    }

    /**
     * Provides a string representation of the FontModel
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "FontModel{" +
                "id='" + id + '\'' +
                ", modelName='" + modelName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", ascent=" + ascent +
                ", height=" + height +
                ", chars=" + chars +
                ", shift=" + shift +
                '}';
    }
}
