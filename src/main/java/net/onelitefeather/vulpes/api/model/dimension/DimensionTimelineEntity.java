package net.onelitefeather.vulpes.api.model.dimension;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a single timeline reference attached to a {@link DimensionTypeEntity}.
 * <p>
 * A dimension type references a tag (i.e. a set) of timelines, which is a repeating group and
 * therefore modelled as its own table rather than a column on {@link DimensionTypeEntity}. The
 * referenced timeline is stored by its registry key, since timelines are not (yet) an owned entity
 * of this model.
 * </p>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.9.0
 */
@Entity(name = "dimension_timelines")
@Table(name = "dimension_timelines", indexes = {
        @Index(name = "idx_dimension_timelines_type_key", columnList = "dimension_type_id, timeline_key", unique = true)
})
public final class DimensionTimelineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    @NotNull
    private String timelineKey;

    @ManyToOne
    @JoinColumn(name = "dimension_type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DimensionTypeEntity dimensionType;

    /**
     * Default constructor for JPA and Micronaut Data.
     */
    public DimensionTimelineEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link DimensionTimelineEntity} with the specified values.
     *
     * @param id          the unique identifier of the timeline reference
     * @param timelineKey the registry key of the referenced timeline
     */
    public DimensionTimelineEntity(
            UUID id,
            String timelineKey
    ) {
        this.id = id;
        this.timelineKey = timelineKey;
    }

    /**
     * Sets the unique identifier of the timeline reference.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the timeline reference.
     *
     * @return the unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the registry key of the referenced timeline.
     *
     * @param timelineKey the timeline key to set
     */
    public void setTimelineKey(String timelineKey) {
        this.timelineKey = timelineKey;
    }

    /**
     * Gets the registry key of the referenced timeline.
     *
     * @return the timeline key
     */
    public String getTimelineKey() {
        return timelineKey;
    }

    /**
     * Sets the dimension type associated with this timeline reference.
     *
     * @param dimensionType the dimension type to set
     */
    public void setDimensionType(DimensionTypeEntity dimensionType) {
        this.dimensionType = dimensionType;
    }

    /**
     * Gets the dimension type associated with this timeline reference.
     *
     * @return the associated dimension type
     */
    public DimensionTypeEntity getDimensionType() {
        return dimensionType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DimensionTimelineEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.timelineKey, that.timelineKey) &&
                Objects.equals(this.dimensionType, that.dimensionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timelineKey, dimensionType);
    }

    @Override
    public String toString() {
        return "DimensionTimelineEntity[" +
                "id=" + id + ", " +
                "timelineKey=" + timelineKey + ", " +
                "dimensionType=" + dimensionType + ']';
    }
}
