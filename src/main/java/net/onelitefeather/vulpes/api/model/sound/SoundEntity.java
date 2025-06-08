package net.onelitefeather.vulpes.api.model.sound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.VulpesModel;

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
    private String modelName;
    private String source;

    /**
     * Represents the list of sound data related to this sound model.
     * This is a one-to-many relationship where each sound model can have multiple sound data entities.
     */
    @OneToMany(mappedBy = "id")
    private List<SoundDataEntity> soundDatumEntities;

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
     * @param id        the unique identifier of the sound model
     * @param modelName the name of the sound model
     * @param source    the source file or location of the sound model
     * @param soundDatumEntities the list of sound data related to this sound model
     */
    public SoundEntity(UUID id, String modelName, String source, List<SoundDataEntity> soundDatumEntities) {
        this.id = id;
        this.modelName = modelName;
        this.source = source;
        this.soundDatumEntities = soundDatumEntities;
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
     * Returns the model name of the sound model
     *
     * @return the model name of the sound model
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the model name of the sound model
     *
     * @param modelName the model name to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Returns the source of the sound model
     *
     * @return the source of the sound model
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the sound model
     *
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Returns the list of sound data related to this sound model
     *
     * @return the list of sound data
     */
    public List<SoundDataEntity> getSoundData() {
        return soundDatumEntities;
    }

    /**
     * Sets the list of sound data related to this sound model
     *
     * @param soundDatumEntities the list of sound data to set
     */
    public void setSoundData(List<SoundDataEntity> soundDatumEntities) {
        this.soundDatumEntities = soundDatumEntities;
    }

    /**
     * Provides a string representation of the SoundModel
     *
     * @return a string representation
     */
    @Override
    public String toString() {
        return "SoundModel{" +
                "id='" + id + '\'' +
                ", modelName='" + modelName + '\'' +
                ", source='" + source + '\'' +
                ", soundData=" + soundDatumEntities +
                '}';
    }
}
