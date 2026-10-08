package net.onelitefeather.vulpes.api.model.advancement;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.AbstractEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

/**
 * Represents an Advancement in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains the display of an advancement such as title,
 * description, icon and frame, and its position in the advancement tree.
 * <p>
 * Advancements form a tree via {@link #getParent()}. An advancement without a parent is a root and
 * opens its own tab, which uses the {@link #getBackground()} texture. A toast notification can be
 * derived from the title, frame type and icon of an advancement.
 * </p>
 * <p>
 * This class is mapped to the database table "advancements" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "advancements")
@Table(name = "advancements", indexes = {
        @Index(name = "idx_advancements_project_id", columnList = "project_id"),
        @Index(name = "idx_advancements_parent_id", columnList = "parent_id")
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
    private String background;
    private float x;
    private float y;
    private boolean showToast = true;
    private boolean announceToChat = true;
    private boolean hidden;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AdvancementEntity parent;

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
     * @param parent      the parent advancement, null for a root advancement
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
            AdvancementEntity parent,
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
        this.parent = parent;
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
     * Returns the background texture of the tab. Only used by root advancements.
     *
     * @return the background texture, e.g. {@code minecraft:gui/advancements/backgrounds/stone}
     */
    public String getBackground() {
        return background;
    }

    /**
     * Sets the background texture of the tab. Only used by root advancements.
     *
     * @param background the background texture to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * Returns the x position in the advancement tree
     *
     * @return the x position
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x position in the advancement tree
     *
     * @param x the x position to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Returns the y position in the advancement tree
     *
     * @return the y position
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y position in the advancement tree
     *
     * @param y the y position to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Returns if a toast is shown when the advancement is achieved
     *
     * @return true if a toast is shown
     */
    public boolean isShowToast() {
        return showToast;
    }

    /**
     * Sets if a toast is shown when the advancement is achieved
     *
     * @param showToast true to show a toast
     */
    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    /**
     * Returns if the advancement is announced in the chat when it is achieved
     *
     * @return true if it is announced in the chat
     */
    public boolean isAnnounceToChat() {
        return announceToChat;
    }

    /**
     * Sets if the advancement is announced in the chat when it is achieved
     *
     * @param announceToChat true to announce it in the chat
     */
    public void setAnnounceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
    }

    /**
     * Returns if the advancement is hidden until it is achieved
     *
     * @return true if it is hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Sets if the advancement is hidden until it is achieved
     *
     * @param hidden true to hide it
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Returns the parent advancement in the tree
     *
     * @return the parent, or null if this is a root advancement
     */
    public AdvancementEntity getParent() {
        return parent;
    }

    /**
     * Sets the parent advancement in the tree
     *
     * @param parent the parent to set, null to make this a root advancement
     */
    public void setParent(AdvancementEntity parent) {
        this.parent = parent;
    }

    /**
     * Returns if this advancement is a root, which opens its own tab
     *
     * @return true if the advancement has no parent
     */
    public boolean isRoot() {
        return parent == null;
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
                ", background='" + background + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", showToast=" + showToast +
                ", announceToChat=" + announceToChat +
                ", hidden=" + hidden +
                ", parent=" + (parent == null ? null : parent.getId()) +
                ", project=" + getProject() +
                '}';
    }
}
