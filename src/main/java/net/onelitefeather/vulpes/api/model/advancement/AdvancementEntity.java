package net.onelitefeather.vulpes.api.model.advancement;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.AbstractEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

/**
 * Represents an Advancement in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains the display of an advancement such as title,
 * description, icon and frame.
 * <p>
 * A toast notification can be derived from the title, frame type and icon of an advancement.
 * </p>
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
    @Enumerated(EnumType.STRING)
    private AdvancementFrameType frameType;
    @JdbcTypeCode(SqlTypes.JSON)
    private String title;
    @JdbcTypeCode(SqlTypes.JSON)
    private String description;

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
     * @param id          the unique identifier of the advancement
     * @param uiName      the user interface name of the advancement
     * @param key         the local key of the advancement within the project's namespace
     *                    (e.g. {@code first_kill}, becomes {@code <project-key>:first_kill}
     *                    via {@link #getNamespacedKey()})
     * @param comment     a comment for the advancement
     * @param material    the material of the icon
     * @param frameType   the frame type of the advancement
     * @param title       the title as vanilla JSON text component
     * @param description the description as vanilla JSON text component
     * @param project     the project this advancement belongs to
     */
    public AdvancementEntity(
            UUID id,
            String uiName,
            String key,
            String comment,
            String material,
            AdvancementFrameType frameType,
            String title,
            String description,
            ProjectEntity project
    ) {
        super(key, project);
        this.setId(id);
        this.uiName = uiName;
        this.comment = comment;
        this.material = material;
        this.frameType = frameType;
        this.title = title;
        this.description = description;
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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the advancement
     *
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the material of the icon
     *
     * @return the material of the icon
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the material of the icon
     *
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Returns the frame type of the advancement
     *
     * @return the frame type of the advancement
     */
    public AdvancementFrameType getFrameType() {
        return frameType;
    }

    /**
     * Sets the frame type of the advancement
     *
     * @param frameType the frame type to set
     */
    public void setFrameType(AdvancementFrameType frameType) {
        this.frameType = frameType;
    }

    /**
     * Returns the title of the advancement
     *
     * @return the title as vanilla JSON text component
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the advancement
     *
     * @param title the title as vanilla JSON text component
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the advancement
     *
     * @return the description as vanilla JSON text component
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the advancement
     *
     * @param description the description as vanilla JSON text component
     */
    public void setDescription(String description) {
        this.description = description;
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
                ", comment='" + comment + '\'' +
                ", material='" + material + '\'' +
                ", frameType=" + frameType +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", project=" + getProject() +
                '}';
    }
}
