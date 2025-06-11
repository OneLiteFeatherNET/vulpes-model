package net.onelitefeather.vulpes.api.model.sound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.VulpesModel;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.UUID;

/**
 * Represents sound data in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to sound such as name, source,
 * and a list of related sound data entities.
 * <p>
 * This class is mapped to the database table "vulpes_sounds" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "sounds")
public class SoundEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String uiName;
    private String variableName;
    @ColumnDefault("false")
    private boolean replace;
    @ColumnDefault("null")
    private String subTitle;

    /**
     * Represents the list of sound data related to this sound model.
     * This is a one-to-many relationship where each sound model can have multiple sound data entities.
     */
    @OneToMany(mappedBy = "id")
    private List<SoundDataEntity> dataEntities;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public SoundEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link SoundEntity} with the specified values.
     *
     * @param id           the unique identifier of the sound model
     * @param uiName       the user interface name for the sound model
     * @param variableName the variable name for the sound model
     * @param replace      whether to replace an existing sound model
     * @param subTitle     the subtitle for the sound model
     * @param dataEntities the list of sound data entities related to this sound model
     */
    public SoundEntity(UUID id, String uiName, String variableName, boolean replace, String subTitle, List<SoundDataEntity> dataEntities) {
        this.id = id;
        this.uiName = uiName;
        this.variableName = variableName;
        this.replace = replace;
        this.subTitle = subTitle;
        this.dataEntities = dataEntities;
    }

    // Getters and setters for each field

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
    public List<SoundDataEntity> getSoundData() {
        return dataEntities;
    }

    /**
     * Sets the list of sound data related to this sound model
     *
     * @param soundDatumEntities the list of sound data to set
     */
    public void setSoundData(List<SoundDataEntity> soundDatumEntities) {
        this.dataEntities = soundDatumEntities;
    }

    @Override
    public String toString() {
        return "SoundEntity{" +
                "id=" + id +
                ", uiName='" + uiName + '\'' +
                ", variableName='" + variableName + '\'' +
                ", replace=" + replace +
                ", subTitle='" + subTitle + '\'' +
                ", dataEntities=" + dataEntities +
                '}';
    }
}
