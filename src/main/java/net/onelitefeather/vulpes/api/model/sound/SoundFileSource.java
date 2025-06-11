package net.onelitefeather.vulpes.api.model.sound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.VulpesModel;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;
import java.util.UUID;

/**
 * The {@link SoundFileSource} class represents a sound file entity that contains information about a sound itself.
 * It includes properties such as volume, pitch, weight, and whether the sound is streamed or preloaded.
 *
 * @author theEvilReaper
 * @version 2.0.0
 * @since 0.1.0
 */
@Entity(name = "sound_data")
public class SoundFileSource implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;
    private String name;
    @ColumnDefault("1.0")
    private float volume;
    @ColumnDefault("1.0")
    private float pitch;
    private int weight;
    @ColumnDefault("true")
    private boolean stream;
    @ColumnDefault("16")
    private int attenuationDistance;
    @ColumnDefault("false")
    private boolean preload;
    @ColumnDefault("file")
    private String type;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public SoundFileSource() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link SoundFileSource} with the specified values.
     *
     * @param id                  the unique identifier of the sound data
     * @param name                the name of the sound data
     * @param volume              the volume level of the sound data
     * @param pitch               the pitch of the sound data
     * @param weight              the weight of the sound data
     * @param stream              whether the sound data is streamed
     * @param attenuationDistance the distance at which sound attenuation occurs
     * @param preload             whether to preload the sound data
     * @param type                the type of sound data (e.g., file)
     */
    public SoundFileSource(
            UUID id,
            String name,
            float volume,
            float pitch,
            int weight,
            boolean stream,
            int attenuationDistance,
            boolean preload,
            String type
    ) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.pitch = pitch;
        this.weight = weight;
        this.stream = stream;
        this.attenuationDistance = attenuationDistance;
        this.preload = preload;
        this.type = type;
    }

    // Getters and setters for each field

    /**
     * Returns the unique identifier of the data.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the sound data.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
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
     * Returns the name of the sound data
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the volume level of the sound data
     *
     * @return the volume level
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Sets the volume level of the sound data
     *
     * @param volume the volume to set
     */
    public void setVolume(float volume) {
        this.volume = volume;
    }

    /**
     * Returns the pitch of the sound data
     *
     * @return the pitch
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Sets the pitch of the sound data
     *
     * @param pitch the pitch to set
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Returns the attenuation distance of the sound.
     *
     * @return the attenuation distance
     */
    public int getAttenuationDistance() {
        return attenuationDistance;
    }

    /**
     * Sets the attenuation distance of the sound.
     *
     * @param attenuationDistance the attenuation distance to set
     */
    public void setAttenuationDistance(int attenuationDistance) {
        this.attenuationDistance = attenuationDistance;
    }

    /**
     * Returns the weight of the sound.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the sound.
     *
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Return the type of the sound which refers to a file or other source.
     *
     * @return the given type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the sound which refers to a file or other source.
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Checks if the sound is preloadable.
     *
     * @return true if preloadable, false otherwise
     */
    public boolean isPreloadable() {
        return preload;
    }

    /**
     * Sets whether the sound should be preloaded.
     *
     * @return true if preloadable, false otherwise
     */
    public boolean isStreamable() {
        return stream;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SoundFileSource that = (SoundFileSource) o;
        return Float.compare(volume, that.volume) == 0 && Float.compare(pitch, that.pitch) == 0 && weight == that.weight && stream == that.stream && attenuationDistance == that.attenuationDistance && preload == that.preload && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, volume, pitch, weight, stream, attenuationDistance, preload, type);
    }

    @Override
    public String toString() {
        return "SoundDataEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", pitch=" + pitch +
                ", weight=" + weight +
                ", stream=" + stream +
                ", attenuationDistance=" + attenuationDistance +
                ", preload=" + preload +
                ", type='" + type + '\'' +
                '}';
    }
}
