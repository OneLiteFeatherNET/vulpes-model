package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

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
public class NotificationEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String uiName;
    private String variableName;
    private String comment;
    private String material;
    private String frameType;
    private String title;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public NotificationEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link NotificationEntity} with the specified values.
     *
     * @param id           the unique identifier of the notification
     * @param uiName       the user interface name of the notification
     * @param variableName the variable name of the notification
     * @param comment      a comment for the description
     * @param material     the material type associated with the notification
     * @param frameType    the frame type associated with the notification
     * @param title        the title of the notification
     */
    public NotificationEntity(UUID id, String uiName, String variableName, String comment, String material, String frameType, String title) {
        this.id = id;
        this.uiName = uiName;
        this.variableName = variableName;
        this.comment = comment;
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
     * Sets the name representation for the ui
     *
     * @param uiName the name to set for the ui
     */
    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    /**
     * Returns the name representation for the ui
     *
     * @return the given ui name
     */
    public String getUiName() {
        return uiName;
    }

    /**
     * Sets the variable name for the notification
     *
     * @param variableName the variable name to set
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Returns the variable name for the notification
     *
     * @return the variable name of the notification
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Returns the comment of the notification
     *
     * @return the description
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the notification
     *
     * @param description the comment to set
     */
    public void setComment(String description) {
        this.comment = description;
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
                ", uiName='" + uiName + '\'' +
                ", variableName='" + variableName + '\'' +
                ", description='" + comment + '\'' +
                ", material='" + material + '\'' +
                ", frameType='" + frameType + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
