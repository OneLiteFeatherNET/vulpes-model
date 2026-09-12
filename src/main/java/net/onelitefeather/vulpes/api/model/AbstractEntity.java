package net.onelitefeather.vulpes.api.model;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;

import java.time.Instant;
import java.util.UUID;

/**
 * Base class for all entities of the Vulpes model. Bundles the fields that are common to every
 * entity: the generated identifier and the creation/modification timestamps, which are maintained
 * automatically by Micronaut Data.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 2.2.0
 */
@MappedSuperclass
public abstract class AbstractEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    @DateCreated
    private Instant creationDate;

    @DateUpdated
    private Instant modificationDate;

    /**
     * Returns the unique identifier of the entity.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the entity.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the point in time at which the entity was created.
     *
     * @return the creation date
     */
    public Instant getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the point in time at which the entity was last modified.
     *
     * @return the modification date
     */
    public Instant getModificationDate() {
        return modificationDate;
    }
}
