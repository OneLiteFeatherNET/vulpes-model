package net.theevilreaper.vulpes.api.model.sound;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Index;
import net.theevilreaper.vulpes.api.model.VulpesModel;

import java.util.List;

/**
 * Represents sound data in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to sound such as name, source,
 * and a list of related sound data entities.
 * <p>
 * This class is mapped to the database table "vulpes_sounds" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Serdeable
@Entity(name = "sounds")
@Table(name = "vulpes_sounds", indexes = @Index(columnList = "id"))
@MappedEntity
public class SoundModel implements VulpesModel {

    @Id
    private String id;
    private String modelName;
    private String source;

    /**
     * Represents the list of sound data related to this sound model.
     * This is a one-to-many relationship where each sound model can have multiple sound data entities.
     */
    @OneToMany(mappedBy = "soundModel")
    private List<SoundData> soundData;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public SoundModel() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link SoundModel} with the specified values.
     *
     * @param id        the unique identifier of the sound model
     * @param modelName the name of the sound model
     * @param source    the source file or location of the sound model
     * @param soundData the list of sound data related to this sound model
     */
    public SoundModel(String id, String modelName, String source, List<SoundData> soundData) {
        this.id = id;
        this.modelName = modelName;
        this.source = source;
        this.soundData = soundData;
    }

    // Getters and setters for each field

    /**
     * Returns the unique identifier of the sound model
     *
     * @return the unique identifier of the sound model
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the sound model
     *
     * @param id the unique identifier to set
     */
    public void setId(String id) {
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
    public List<SoundData> getSoundData() {
        return soundData;
    }

    /**
     * Sets the list of sound data related to this sound model
     *
     * @param soundData the list of sound data to set
     */
    public void setSoundData(List<SoundData> soundData) {
        this.soundData = soundData;
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
                ", soundData=" + soundData +
                '}';
    }
}
