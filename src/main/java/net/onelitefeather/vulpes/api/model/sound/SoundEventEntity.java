package net.onelitefeather.vulpes.api.model.sound;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import net.onelitefeather.vulpes.api.model.AbstractEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The {@link SoundEventEntity} class represents a sound event to represent a custom sound.
 * It contains all relevant information about the sound to be used on a Minecraft server.
 * <br>
 * For more information about the data structure, see the <a href="https://minecraft.wiki/w/Sounds.json">wiki</a> page
 *
 * @author theEvilReaper
 * @version 2.0.0
 * @since 0.1.0
 */
@Entity(name = "sounds")
@Table(name = "sounds", indexes = {
        @Index(name = "idx_sounds_project_id", columnList = "project_id")
})
public class SoundEventEntity extends AbstractEntity {

    private String uiName;
    private String key;
    private String keyName;
    @Column(name = "replace_flag")
    @ColumnDefault("false")
    private boolean replace;
    @ColumnDefault("null")
    private String subTitle;

    /**
     * Represents the list of sound data related to this sound model.
     * This is a one-to-many relationship where each sound model can have multiple sound data entities.
     */
    @OneToMany(mappedBy = "soundEvent")
    private List<SoundFileSource> dataEntities;

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
    public SoundEventEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link SoundEventEntity} with the specified values.
     *
     * @param id           the unique identifier of the sound model
     * @param uiName       the user interface name for the sound model
     * @param key          the namespaced key for the sound model (e.g. {@code minecraft:ambient.cave})
     * @param keyName      the key name for the sound model
     * @param replace      whether to replace an existing sound model
     * @param subTitle     the subtitle for the sound model
     * @param dataEntities the list of sound data entities related to this sound model
     * @param project      the project this sound event belongs to
     */
    public SoundEventEntity(UUID id, String uiName, String key, String keyName, boolean replace, String subTitle, List<SoundFileSource> dataEntities, ProjectEntity project) {
        this.setId(id);
        this.uiName = uiName;
        this.keyName = keyName;
        this.key = key;
        this.replace = replace;
        this.subTitle = subTitle;
        this.dataEntities = dataEntities;
        this.project = project;
    }

    /**
     * Returns the user interface name for the sound model
     *
     * @return the user interface name
     */
    public String getUiName() {
        return uiName;
    }

    /**
     * Sets the user interface name for the sound model
     *
     * @param uiName the user interface name to set
     */
    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    /**
     * Set the key name for the sound model.
     *
     * @param keyName the key name to set
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * Returns the key name for the sound model.
     *
     * @return the key name
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * Set the namespaced key associated with this sound model (e.g. {@code minecraft:ambient.cave}).
     *
     * @param key the namespaced key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the namespaced key associated with this sound model (e.g. {@code minecraft:ambient.cave}).
     *
     * @return the namespaced key
     */
    public String getKey() {
        return key;
    }

    /**
     * Derives the variable name of the sound model from its namespaced key, e.g. {@code minecraft:ambient.cave}
     * becomes {@code AMBIENT.CAVE}.
     *
     * @return the derived variable name of the sound model
     */
    public String getVariableName() {
        int separatorIndex = key.indexOf(':');
        return (separatorIndex >= 0 ? key.substring(separatorIndex + 1) : key).toUpperCase();
    }

    /**
     * Sets the subtitle for the sound model.
     *
     * @param subTitle the subtitle to set
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * Returns the subtitle for the sound model.
     *
     * @return the subtitle
     */
    public String getSubTitle() {
        return subTitle;
    }


    /**
     * Returns the list of sound data related to this sound model
     *
     * @return the list of sound data
     */
    public List<SoundFileSource> getSoundData() {
        return dataEntities;
    }

    /**
     * Sets the list of sound data related to this sound model
     *
     * @param soundDatumEntities the list of sound data to set
     */
    public void setSoundData(List<SoundFileSource> soundDatumEntities) {
        this.dataEntities = soundDatumEntities;
    }

    /**
     * Returns the project this sound event belongs to.
     *
     * @return the project of the sound event
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets the project this sound event belongs to.
     *
     * @param project the project to set
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoundEventEntity that = (SoundEventEntity) o;
        return replace == that.replace && Objects.equals(getId(), that.getId()) && Objects.equals(uiName, that.uiName) && Objects.equals(key, that.key) && Objects.equals(keyName, that.keyName) && Objects.equals(subTitle, that.subTitle) && Objects.equals(dataEntities, that.dataEntities) && Objects.equals(project, that.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), uiName, key, keyName, replace, subTitle, dataEntities, project);
    }

    @Override
    public String toString() {
        return "SoundEventEntity{" +
                "id=" + getId() +
                ", uiName='" + uiName + '\'' +
                ", key='" + key + '\'' +
                ", keyName='" + keyName + '\'' +
                ", replace=" + replace +
                ", subTitle='" + subTitle + '\'' +
                ", dataEntities=" + dataEntities +
                ", project=" + project +
                '}';
    }
}
