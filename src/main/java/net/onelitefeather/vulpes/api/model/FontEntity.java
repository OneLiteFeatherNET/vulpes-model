package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import net.onelitefeather.vulpes.api.model.font.FontStringEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;

import java.util.List;
import java.util.UUID;

/**
 * Represents a Font in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to a font such as name,
 * type, ascent, height, etc.
 * <p>
 * This class is mapped to the database table "vulpes_fonts" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "fonts")
@Table(name = "fonts", indexes = {
        @Index(name = "idx_fonts_project_id", columnList = "project_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uq_fonts_project_key", columnNames = {"project_id", "key"})
})
public class FontEntity extends AbstractEntity {

    private String uiName;
    private String provider;
    private String mapper = "font";
    private String texturePath;
    private String comment;
    private int height;
    private int ascent;
    @OneToMany(mappedBy = "font", cascade = CascadeType.ALL)
    private List<FontStringEntity> chars;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public FontEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link FontEntity} with the specified values.
     *
     * @param id           the unique identifier of the font
     * @param uiName       the user interface name of the font
     * @param key          the local key of the font within the project's namespace
     *                     (e.g. {@code default}, becomes {@code <project-key>:default}
     *                     via {@link #getNamespacedKey()})
     * @param provider     the provider of the font
     * @param texturePath  the path to the texture of the font
     * @param comment      a comment or description for the font
     * @param height       the height of the font
     * @param ascent       the ascent of the font
     * @param chars        the list of characters included in the font
     * @param project      the project this font belongs to
     */
    public FontEntity(
            UUID id,
            String uiName,
            String key,
            String provider,
            String texturePath,
            String comment,
            int height,
            int ascent,
            List<FontStringEntity> chars,
            ProjectEntity project
    ) {
        super(key, project);
        this.setId(id);
        this.uiName = uiName;
        this.provider = provider;
        this.texturePath = texturePath;
        this.comment = comment;
        this.height = height;
        this.ascent = ascent;
        this.chars = chars;
    }

    // Getters and setters for each field

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    public String getUiName() {
        return uiName;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getMapper() {
        return mapper;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public String getTexturePath() {
        return texturePath;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
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
    public List<FontStringEntity> getChars() {
        return chars;
    }

    /**
     * Sets the list of characters included in the font
     *
     * @param chars the list of characters to set
     */
    public void setChars(List<FontStringEntity> chars) {
        this.chars = chars;
    }

    @Override
    public String toString() {
        return "FontEntity{" +
                "id=" + getId() +
                ", uiName='" + uiName + '\'' +
                ", key='" + getKey() + '\'' +
                ", provider='" + provider + '\'' +
                ", mapper='" + mapper + '\'' +
                ", texturePath='" + texturePath + '\'' +
                ", comment='" + comment + '\'' +
                ", height=" + height +
                ", ascent=" + ascent +
                ", chars=" + chars +
                ", project=" + getProject() +
                '}';
    }
}
