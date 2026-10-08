package net.onelitefeather.vulpes.api.model.advancement;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.AbstractEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.UUID;

/**
 * Represents an Advancement in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to an advancement such as name,
 * description, material, etc.
 * <p>
 * This class is mapped to the database table "advancements" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "advancements")
@Table(name = "advancements", indexes = {
        @Index(name = "idx_advancements_project_id", columnList = "project_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_advancements_project_key", columnNames = {"project_id", "key"})
})
public class AdvancementEntity extends AbstractEntity {

    private String uiName;
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
    public AdvancementEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link AdvancementEntity} with the specified values.
     *
     * @param id           the unique identifier of the advancement
     * @param uiName       the user interface name of the advancement
     * @param key          the local key of the advancement within the project's namespace
     *                     (e.g. {@code first_kill}, becomes {@code <project-key>:first_kill}
     *                     via {@link #getNamespacedKey()})
     * @param comment      a comment for the description
     * @param material     the material type associated with the advancement
     * @param frameType    the frame type associated with the advancement
     * @param title        the title of the advancement
     * @param project      the project this advancement belongs to
     */
    public AdvancementEntity(UUID id, String uiName, String key, String comment, String material, String frameType, String title, ProjectEntity project) {
        super(key, project);
        this.setId(id);
        this.uiName = uiName;
        this.comment = comment;
        this.material = material;
        this.frameType = frameType;
        this.title = title;
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
     * Returns the comment of the advancement
     *
     * @return the description
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the advancement
     *
     * @param description the comment to set
     */
    public void setComment(String description) {
        this.comment = description;
    }

    /**
     * Returns the material type associated with the advancement
     *
     * @return the material type of the advancement
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the material type associated with the advancement
     *
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Returns the frame type associated with the advancement
     *
     * @return the frame type of the advancement
     */
    public String getFrameType() {
        return frameType;
    }

    /**
     * Sets the frame type associated with the advancement
     *
     * @param frameType the frame type to set
     */
    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    /**
     * Returns the title of the advancement
     *
     * @return the title of the advancement
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the advancement
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Provides a string representation of the AdvancementEntity
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "AdvancementEntity{" +
                "id='" + getId() + '\'' +
                ", uiName='" + uiName + '\'' +
                ", key='" + getKey() + '\'' +
                ", description='" + comment + '\'' +
                ", material='" + material + '\'' +
                ", frameType='" + frameType + '\'' +
                ", title='" + title + '\'' +
                ", project=" + getProject() +
                '}';
    }
}
