package net.theevilreaper.vulpes.api.model.sound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.theevilreaper.vulpes.api.generator.UuidV7Generator;
import net.theevilreaper.vulpes.api.generator.VulpesGenerator;
import net.theevilreaper.vulpes.api.model.VulpesModel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.util.UUID;

/**
 * Represents sound data in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to sound such as name, source,
 * volume, and pitch.
 * <p>
 * This class is mapped to the database table "vulpes_sound_data" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 */
@Entity(name = "sound_data")
public class SoundData implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String name;
    private String source;
    private double volume;
    private double pitch;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public SoundData() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link SoundData} with the specified values.
     *
     * @param id      the unique identifier of the sound data
     * @param name    the name of the sound data
     * @param source  the source file or location of the sound
     * @param volume  the volume level of the sound
     * @param pitch   the pitch of the sound
     */
    public SoundData(UUID id, String name, String source, double volume, double pitch) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.volume = volume;
        this.pitch = pitch;
    }

    // Getters and setters for each field

    /**
     * Returns the unique identifier of the sound data
     *
     * @return the unique identifier of the sound data
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the sound data
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the name of the sound data
     *
     * @return the name of the sound data
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the sound data
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the source of the sound data
     *
     * @return the source of the sound data
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source of the sound data
     *
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Returns the volume level of the sound data
     *
     * @return the volume level of the sound data
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Sets the volume level of the sound data
     *
     * @param volume the volume to set
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * Returns the pitch of the sound data
     *
     * @return the pitch of the sound data
     */
    public double getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch of the sound data
     *
     * @param pitch the pitch to set
     */
    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    /**
     * Provides a string representation of the SoundData
     *
     * @return
     */
    @Override
    public String toString() {
        return "SoundData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                ", volume=" + volume +
                ", pitch=" + pitch +
                '}';
    }
}
