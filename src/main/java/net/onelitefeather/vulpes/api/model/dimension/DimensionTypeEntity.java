package net.onelitefeather.vulpes.api.model.dimension;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.VulpesModel;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.UUID;

/**
 * Represents a Dimension Type in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It mirrors {@code net.minestom.server.world.DimensionType} and
 * contains details such as world bounds, lighting behaviour, skybox and its environment attributes.
 * <p>
 * This class is mapped to the database table "dimension_types" and contains fields that are
 * automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 * <p>
 * The {@code monsterSpawnLightLevel} field holds the serialized representation of the source
 * {@code IntProvider} (which has several, partly recursive, variants such as {@code Uniform} or
 * {@code Clamped}). Rather than modelling every variant relationally, it is kept as a single opaque
 * value here; interpreting it is the responsibility of the application layer. Attributes and timeline
 * references are repeating groups and are therefore modelled as separate tables, see
 * {@link DimensionAttributeEntity} and {@link DimensionTimelineEntity}.
 * </p>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.9.0
 */
@Entity(name = "dimension_types")
@Table(name = "dimension_types", indexes = {
        @Index(name = "idx_dimension_types_project_id", columnList = "project_id")
})
public class DimensionTypeEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    @NotNull
    private String uiName;

    @NotNull
    private String variableName;

    @ColumnDefault("false")
    private boolean hasFixedTime;

    @ColumnDefault("true")
    private boolean hasSkylight;

    @ColumnDefault("false")
    private boolean hasCeiling;

    @ColumnDefault("false")
    private boolean hasEnderDragonFight;

    @DecimalMin("0.00001")
    @DecimalMax("30000000.0")
    private double coordinateScale;

    @Min(-2032)
    @Max(2031)
    private int minY;

    @Min(16)
    @Max(4064)
    private int height;

    private int logicalHeight;

    @NotNull
    private String infiniburn;

    private float ambientLight;

    @NotNull
    private String monsterSpawnLightLevel;

    @Min(0)
    @Max(15)
    private int monsterSpawnBlockLightLimit;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Skybox skybox;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CardinalLight cardinalLight;

    @Nullable
    private String defaultClock;

    @OneToMany(mappedBy = "dimensionType", cascade = CascadeType.ALL)
    private List<DimensionAttributeEntity> attributes;

    @OneToMany(mappedBy = "dimensionType", cascade = CascadeType.ALL)
    private List<DimensionTimelineEntity> timelines;

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
    public DimensionTypeEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link DimensionTypeEntity} with the specified values.
     *
     * @param id                          the unique identifier of the dimension type
     * @param uiName                      the user interface name of the dimension type
     * @param variableName                the variable name of the dimension type
     * @param hasFixedTime                whether the dimension type has a fixed time
     * @param hasSkylight                 whether the dimension type has skylight
     * @param hasCeiling                  whether the dimension type has a ceiling
     * @param hasEnderDragonFight         whether the dimension type has the ender dragon fight
     * @param coordinateScale             the coordinate scale of the dimension type
     * @param minY                        the minimum build height of the dimension type
     * @param height                      the height of the dimension type
     * @param logicalHeight               the logical height of the dimension type
     * @param infiniburn                  the infiniburn block tag of the dimension type
     * @param ambientLight                the ambient light of the dimension type
     * @param monsterSpawnLightLevel      the serialized monster spawn light level provider
     * @param monsterSpawnBlockLightLimit the monster spawn block light limit
     * @param skybox                      the skybox of the dimension type
     * @param cardinalLight               the cardinal light behaviour of the dimension type
     * @param defaultClock                the registry key of the default clock, or {@code null}
     * @param attributes                  the environment attributes of the dimension type
     * @param timelines                   the timeline references of the dimension type
     * @param project                     the project this dimension type belongs to
     */
    public DimensionTypeEntity(
            UUID id,
            String uiName,
            String variableName,
            boolean hasFixedTime,
            boolean hasSkylight,
            boolean hasCeiling,
            boolean hasEnderDragonFight,
            double coordinateScale,
            int minY,
            int height,
            int logicalHeight,
            String infiniburn,
            float ambientLight,
            String monsterSpawnLightLevel,
            int monsterSpawnBlockLightLimit,
            Skybox skybox,
            CardinalLight cardinalLight,
            @Nullable String defaultClock,
            List<DimensionAttributeEntity> attributes,
            List<DimensionTimelineEntity> timelines,
            ProjectEntity project
    ) {
        this.id = id;
        this.uiName = uiName;
        this.variableName = variableName;
        this.hasFixedTime = hasFixedTime;
        this.hasSkylight = hasSkylight;
        this.hasCeiling = hasCeiling;
        this.hasEnderDragonFight = hasEnderDragonFight;
        this.coordinateScale = coordinateScale;
        this.minY = minY;
        this.height = height;
        this.logicalHeight = logicalHeight;
        this.infiniburn = infiniburn;
        this.ambientLight = ambientLight;
        this.monsterSpawnLightLevel = monsterSpawnLightLevel;
        this.monsterSpawnBlockLightLimit = monsterSpawnBlockLightLimit;
        this.skybox = skybox;
        this.cardinalLight = cardinalLight;
        this.defaultClock = defaultClock;
        this.attributes = attributes;
        this.timelines = timelines;
        this.project = project;
    }

    // Getters and setters for each field

    /**
     * Returns the unique identifier of the dimension type.
     *
     * @return the unique identifier of the dimension type
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the dimension type.
     *
     * @param id the unique identifier to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the user interface name of the dimension type.
     *
     * @return the user interface name
     */
    public String getUiName() {
        return uiName;
    }

    /**
     * Sets the user interface name of the dimension type.
     *
     * @param uiName the user interface name to set
     */
    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    /**
     * Returns the variable name of the dimension type.
     *
     * @return the variable name
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Sets the variable name of the dimension type.
     *
     * @param variableName the variable name to set
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Returns whether the dimension type has a fixed time.
     *
     * @return {@code true} if the time is fixed, {@code false} otherwise
     */
    public boolean isHasFixedTime() {
        return hasFixedTime;
    }

    /**
     * Sets whether the dimension type has a fixed time.
     *
     * @param hasFixedTime whether the time is fixed
     */
    public void setHasFixedTime(boolean hasFixedTime) {
        this.hasFixedTime = hasFixedTime;
    }

    /**
     * Returns whether the dimension type has skylight.
     *
     * @return {@code true} if it has skylight, {@code false} otherwise
     */
    public boolean isHasSkylight() {
        return hasSkylight;
    }

    /**
     * Sets whether the dimension type has skylight.
     *
     * @param hasSkylight whether it has skylight
     */
    public void setHasSkylight(boolean hasSkylight) {
        this.hasSkylight = hasSkylight;
    }

    /**
     * Returns whether the dimension type has a ceiling.
     *
     * @return {@code true} if it has a ceiling, {@code false} otherwise
     */
    public boolean isHasCeiling() {
        return hasCeiling;
    }

    /**
     * Sets whether the dimension type has a ceiling.
     *
     * @param hasCeiling whether it has a ceiling
     */
    public void setHasCeiling(boolean hasCeiling) {
        this.hasCeiling = hasCeiling;
    }

    /**
     * Returns whether the dimension type has the ender dragon fight.
     *
     * @return {@code true} if it has the ender dragon fight, {@code false} otherwise
     */
    public boolean isHasEnderDragonFight() {
        return hasEnderDragonFight;
    }

    /**
     * Sets whether the dimension type has the ender dragon fight.
     *
     * @param hasEnderDragonFight whether it has the ender dragon fight
     */
    public void setHasEnderDragonFight(boolean hasEnderDragonFight) {
        this.hasEnderDragonFight = hasEnderDragonFight;
    }

    /**
     * Returns the coordinate scale of the dimension type.
     *
     * @return the coordinate scale
     */
    public double getCoordinateScale() {
        return coordinateScale;
    }

    /**
     * Sets the coordinate scale of the dimension type.
     *
     * @param coordinateScale the coordinate scale to set
     */
    public void setCoordinateScale(double coordinateScale) {
        this.coordinateScale = coordinateScale;
    }

    /**
     * Returns the minimum build height of the dimension type.
     *
     * @return the minimum build height
     */
    public int getMinY() {
        return minY;
    }

    /**
     * Sets the minimum build height of the dimension type.
     *
     * @param minY the minimum build height to set
     */
    public void setMinY(int minY) {
        this.minY = minY;
    }

    /**
     * Returns the height of the dimension type.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the dimension type.
     *
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the logical height of the dimension type.
     *
     * @return the logical height
     */
    public int getLogicalHeight() {
        return logicalHeight;
    }

    /**
     * Sets the logical height of the dimension type.
     *
     * @param logicalHeight the logical height to set
     */
    public void setLogicalHeight(int logicalHeight) {
        this.logicalHeight = logicalHeight;
    }

    /**
     * Returns the infiniburn block tag of the dimension type.
     *
     * @return the infiniburn block tag
     */
    public String getInfiniburn() {
        return infiniburn;
    }

    /**
     * Sets the infiniburn block tag of the dimension type.
     *
     * @param infiniburn the infiniburn block tag to set
     */
    public void setInfiniburn(String infiniburn) {
        this.infiniburn = infiniburn;
    }

    /**
     * Returns the ambient light of the dimension type.
     *
     * @return the ambient light
     */
    public float getAmbientLight() {
        return ambientLight;
    }

    /**
     * Sets the ambient light of the dimension type.
     *
     * @param ambientLight the ambient light to set
     */
    public void setAmbientLight(float ambientLight) {
        this.ambientLight = ambientLight;
    }

    /**
     * Returns the serialized monster spawn light level provider.
     *
     * @return the serialized monster spawn light level provider
     */
    public String getMonsterSpawnLightLevel() {
        return monsterSpawnLightLevel;
    }

    /**
     * Sets the serialized monster spawn light level provider.
     *
     * @param monsterSpawnLightLevel the serialized provider to set
     */
    public void setMonsterSpawnLightLevel(String monsterSpawnLightLevel) {
        this.monsterSpawnLightLevel = monsterSpawnLightLevel;
    }

    /**
     * Returns the monster spawn block light limit of the dimension type.
     *
     * @return the monster spawn block light limit
     */
    public int getMonsterSpawnBlockLightLimit() {
        return monsterSpawnBlockLightLimit;
    }

    /**
     * Sets the monster spawn block light limit of the dimension type.
     *
     * @param monsterSpawnBlockLightLimit the monster spawn block light limit to set
     */
    public void setMonsterSpawnBlockLightLimit(int monsterSpawnBlockLightLimit) {
        this.monsterSpawnBlockLightLimit = monsterSpawnBlockLightLimit;
    }

    /**
     * Returns the skybox of the dimension type.
     *
     * @return the skybox
     */
    public Skybox getSkybox() {
        return skybox;
    }

    /**
     * Sets the skybox of the dimension type.
     *
     * @param skybox the skybox to set
     */
    public void setSkybox(Skybox skybox) {
        this.skybox = skybox;
    }

    /**
     * Returns the cardinal light behaviour of the dimension type.
     *
     * @return the cardinal light behaviour
     */
    public CardinalLight getCardinalLight() {
        return cardinalLight;
    }

    /**
     * Sets the cardinal light behaviour of the dimension type.
     *
     * @param cardinalLight the cardinal light behaviour to set
     */
    public void setCardinalLight(CardinalLight cardinalLight) {
        this.cardinalLight = cardinalLight;
    }

    /**
     * Returns the registry key of the default clock.
     *
     * @return the default clock key, or {@code null} if not set
     */
    @Nullable
    public String getDefaultClock() {
        return defaultClock;
    }

    /**
     * Sets the registry key of the default clock.
     *
     * @param defaultClock the default clock key to set
     */
    public void setDefaultClock(@Nullable String defaultClock) {
        this.defaultClock = defaultClock;
    }

    /**
     * Returns the environment attributes of the dimension type.
     *
     * @return the environment attributes
     */
    public List<DimensionAttributeEntity> getAttributes() {
        return attributes;
    }

    /**
     * Sets the environment attributes of the dimension type.
     *
     * @param attributes the environment attributes to set
     */
    public void setAttributes(List<DimensionAttributeEntity> attributes) {
        this.attributes = attributes;
    }

    /**
     * Returns the timeline references of the dimension type.
     *
     * @return the timeline references
     */
    public List<DimensionTimelineEntity> getTimelines() {
        return timelines;
    }

    /**
     * Sets the timeline references of the dimension type.
     *
     * @param timelines the timeline references to set
     */
    public void setTimelines(List<DimensionTimelineEntity> timelines) {
        this.timelines = timelines;
    }

    /**
     * Returns the project this dimension type belongs to.
     *
     * @return the project of the dimension type
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets the project this dimension type belongs to.
     *
     * @param project the project to set
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "DimensionTypeEntity{" +
                "id=" + id +
                ", uiName='" + uiName + '\'' +
                ", variableName='" + variableName + '\'' +
                ", hasFixedTime=" + hasFixedTime +
                ", hasSkylight=" + hasSkylight +
                ", hasCeiling=" + hasCeiling +
                ", hasEnderDragonFight=" + hasEnderDragonFight +
                ", coordinateScale=" + coordinateScale +
                ", minY=" + minY +
                ", height=" + height +
                ", logicalHeight=" + logicalHeight +
                ", infiniburn='" + infiniburn + '\'' +
                ", ambientLight=" + ambientLight +
                ", monsterSpawnLightLevel='" + monsterSpawnLightLevel + '\'' +
                ", monsterSpawnBlockLightLimit=" + monsterSpawnBlockLightLimit +
                ", skybox=" + skybox +
                ", cardinalLight=" + cardinalLight +
                ", defaultClock='" + defaultClock + '\'' +
                ", attributes=" + attributes +
                ", timelines=" + timelines +
                ", project=" + project +
                '}';
    }
}
