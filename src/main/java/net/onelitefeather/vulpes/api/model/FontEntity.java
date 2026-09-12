package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import net.onelitefeather.vulpes.api.model.font.FontStringEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
})
public class FontEntity extends AbstractEntity {

    private String uiName;
    private String key;
    private String provider;
    private String mapper = "font";
    private String texturePath;
    private String comment;
    private int height;
    private int ascent;
    @OneToMany(mappedBy = "font", cascade = CascadeType.ALL)
    private List<FontStringEntity> chars;

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
    public FontEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link FontEntity} with the specified values.
     *
     * @param id           the unique identifier of the font
     * @param uiName       the user interface name of the font
     * @param key          the namespaced key of the font (e.g. {@code minecraft:default})
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
        this.setId(id);
        this.uiName = uiName;
        this.key = key;
        this.provider = provider;
        this.texturePath = texturePath;
        this.comment = comment;
        this.height = height;
        this.ascent = ascent;
        this.chars = chars;
        this.project = project;
    }

    // Getters and setters for each field

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    public String getUiName() {
        return uiName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    /**
     * Derives the variable name of the font from its namespaced key, e.g. {@code minecraft:default}
     * becomes {@code DEFAULT}.
     *
     * @return the derived variable name of the font
     */
    public String getVariableName() {
        int separatorIndex = key.indexOf(':');
        return (separatorIndex >= 0 ? key.substring(separatorIndex + 1) : key).toUpperCase();
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

    /**
     * Returns the project this font belongs to.
     *
     * @return the project of the font
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets the project this font belongs to.
     *
     * @param project the project to set
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "FontEntity{" +
                "id=" + getId() +
                ", uiName='" + uiName + '\'' +
                ", key='" + key + '\'' +
                ", provider='" + provider + '\'' +
                ", mapper='" + mapper + '\'' +
                ", texturePath='" + texturePath + '\'' +
                ", comment='" + comment + '\'' +
                ", height=" + height +
                ", ascent=" + ascent +
                ", chars=" + chars +
                ", project=" + project +
                '}';
    }
}
