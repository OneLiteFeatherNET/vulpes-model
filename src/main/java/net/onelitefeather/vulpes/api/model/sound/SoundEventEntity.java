package net.onelitefeather.vulpes.api.model.sound;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.VulpesModel;
import org.hibernate.annotations.ColumnDefault;

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
public class SoundEventEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String uiName;
    private String variableName;
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
    @OneToMany(mappedBy = "id")
    private List<SoundFileSource> dataEntities;

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
     * @param variableName the variable name for the sound model
     * @param keyName      the key name for the sound model
     * @param replace      whether to replace an existing sound model
     * @param subTitle     the subtitle for the sound model
     * @param dataEntities the list of sound data entities related to this sound model
     */
    public SoundEventEntity(UUID id, String uiName, String variableName, String keyName, boolean replace, String subTitle, List<SoundFileSource> dataEntities) {
        this.id = id;
        this.uiName = uiName;
        this.keyName = keyName;
        this.variableName = variableName;
        this.replace = replace;
        this.subTitle = subTitle;
        this.dataEntities = dataEntities;
    }

    /**
     * Returns the unique identifier of the sound model
     *
     * @return the unique identifier of the sound model
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the sound model
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
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
     * Set the name for the variable associated with this sound model.
     *
     * @param variableName the name of the variable to set
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Returns the name of the variable associated with this sound model.
     *
     * @return the name of the variable
     */
    public String getVariableName() {
        return variableName;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoundEventEntity that = (SoundEventEntity) o;
        return replace == that.replace && Objects.equals(id, that.id) && Objects.equals(uiName, that.uiName) && Objects.equals(variableName, that.variableName) && Objects.equals(keyName, that.keyName) && Objects.equals(subTitle, that.subTitle) && Objects.equals(dataEntities, that.dataEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uiName, variableName, keyName, replace, subTitle, dataEntities);
    }

    @Override
    public String toString() {
        return "SoundEventEntity{" +
                "id=" + id +
                ", uiName='" + uiName + '\'' +
                ", variableName='" + variableName + '\'' +
                ", keyName='" + keyName + '\'' +
                ", replace=" + replace +
                ", subTitle='" + subTitle + '\'' +
                ", dataEntities=" + dataEntities +
                '}';
    }
}
