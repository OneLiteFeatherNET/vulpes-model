package net.theevilreaper.vulpes.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.theevilreaper.vulpes.api.generator.VulpesGenerator;

import java.util.UUID;

/**
 * Represents a Notification in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to a notification such as name,
 * description, material, etc.
 * <p>
 * This class is mapped to the database table "vulpes_notifications" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "notifications")
public class NotificationModel implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String modelName;
    private String name;
    private String description;
    private String material;
    private String frameType;
    private String title;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public NotificationModel() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link NotificationModel} with the specified values.
     *
     * @param id          the unique identifier of the notification
     * @param modelName   the model name associated with the notification
     * @param name        the name of the notification
     * @param description a description of the notification
     * @param material    the material type associated with the notification
     * @param frameType   the frame type associated with the notification
     * @param title       the title of the notification
     */
    public NotificationModel(UUID id, String modelName, String name, String description, String material, String frameType, String title) {
        this.id = id;
        this.modelName = modelName;
        this.name = name;
        this.description = description;
        this.material = material;
        this.frameType = frameType;
        this.title = title;
    }

    /**
     * Returns the unique identifier of the notification
     *
     * @return the unique identifier of the notification
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the notification
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the model name associated with the notification
     *
     * @return the model name of the notification
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the model name associated with the notification
     *
     * @param modelName the model name to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Returns the name of the notification
     *
     * @return the name of the notification
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the notification
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the notification
     *
     * @return the description of the notification
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the notification
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the material type associated with the notification
     *
     * @return the material type of the notification
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the material type associated with the notification
     *
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Returns the frame type associated with the notification
     *
     * @return the frame type of the notification
     */
    public String getFrameType() {
        return frameType;
    }

    /**
     * Sets the frame type associated with the notification
     *
     * @param frameType the frame type to set
     */
    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    /**
     * Returns the title of the notification
     *
     * @return the title of the notification
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the notification
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Provides a string representation of the NotificationModel
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "NotificationModel{" +
                "id='" + id + '\'' +
                ", modelName='" + modelName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", material='" + material + '\'' +
                ", frameType='" + frameType + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
