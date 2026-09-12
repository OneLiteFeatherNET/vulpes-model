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
 * Represents a Notification in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to a notification such as name,
 * description, material, etc.
 * <p>
 * This class is mapped to the database table "vulpes_notifications" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "notifications")
@Table(name = "notifications", indexes = {
        @Index(name = "idx_notifications_project_id", columnList = "project_id")
})
public class NotificationEntity extends AbstractEntity {

    private String uiName;
    private String key;
    private String comment;
    private String material;
    private String frameType;
    private String title;
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
    public NotificationEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link NotificationEntity} with the specified values.
     *
     * @param id           the unique identifier of the notification
     * @param uiName       the user interface name of the notification
     * @param key          the namespaced key of the notification (e.g. {@code minecraft:achievement})
     * @param comment      a comment for the description
     * @param material     the material type associated with the notification
     * @param frameType    the frame type associated with the notification
     * @param title        the title of the notification
     * @param project      the project this notification belongs to
     */
    public NotificationEntity(UUID id, String uiName, String key, String comment, String material, String frameType, String title, ProjectEntity project) {
        this.setId(id);
        this.uiName = uiName;
        this.key = key;
        this.comment = comment;
        this.material = material;
        this.frameType = frameType;
        this.title = title;
        this.project = project;
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
     * Sets the namespaced key for the notification (e.g. {@code minecraft:achievement}).
     *
     * @param key the namespaced key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the namespaced key for the notification (e.g. {@code minecraft:achievement}).
     *
     * @return the namespaced key of the notification
     */
    public String getKey() {
        return key;
    }

    /**
     * Derives the variable name of the notification from its namespaced key, e.g. {@code minecraft:achievement}
     * becomes {@code ACHIEVEMENT}.
     *
     * @return the derived variable name of the notification
     */
    public String getVariableName() {
        int separatorIndex = key.indexOf(':');
        return (separatorIndex >= 0 ? key.substring(separatorIndex + 1) : key).toUpperCase();
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
     * Returns the project this notification belongs to.
     *
     * @return the project of the notification
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets the project this notification belongs to.
     *
     * @param project the project to set
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    /**
     * Provides a string representation of the NotificationModel
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "NotificationModel{" +
                "id='" + getId() + '\'' +
                ", uiName='" + uiName + '\'' +
                ", key='" + key + '\'' +
                ", description='" + comment + '\'' +
                ", material='" + material + '\'' +
                ", frameType='" + frameType + '\'' +
                ", title='" + title + '\'' +
                ", project=" + project +
                '}';
    }
}
