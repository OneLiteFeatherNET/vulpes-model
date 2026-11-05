package net.onelitefeather.vulpes.api.model.pack;

import jakarta.annotation.Nullable;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import net.onelitefeather.vulpes.api.model.asset.ProjectAssetEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The content of the {@code pack.mcmeta} file of a project, one per {@link PackType}.
 *
 * <p>Only the values a user chooses are stored. The legacy fields {@code pack_format} and
 * {@code supported_formats} follow from the format range and are derived when the pack is exported.
 *
 * <p>The format range follows the {@code min_format} and {@code max_format} semantics of Minecraft: a minimum
 * without a minor version means minor version {@code 0}, a maximum without a minor version means any minor
 * version of that major version.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 3.8.0
 */
@Entity
@Table(
        name = "pack_meta",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_pack_meta_project_type",
                columnNames = {"project_id", "pack_type"}
        )
)
public class PackMetaEntity extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProjectEntity project;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pack_type", nullable = false, length = 16)
    private PackType packType;

    /** A text component, serialized as JSON. */
    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "description", nullable = false)
    private String description;

    @Min(1)
    @Column(name = "min_format_major", nullable = false)
    private int minFormatMajor;

    @Min(0)
    @Column(name = "min_format_minor", nullable = false)
    private int minFormatMinor;

    @Min(1)
    @Column(name = "max_format_major", nullable = false)
    private int maxFormatMajor;

    /** {@code null} means any minor version of {@link #maxFormatMajor}. */
    @Nullable
    @Min(0)
    @Column(name = "max_format_minor")
    private Integer maxFormatMinor;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pack_meta_feature", joinColumns = @JoinColumn(name = "pack_meta_id"))
    @Column(name = "feature", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<String> enabledFeatures = new LinkedHashSet<>();

    /** Written as the {@code pack.png} of the pack, {@code null} for none. */
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_asset_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private ProjectAssetEntity iconAsset;

    /**
     * Creates an empty entity, required by JPA.
     */
    protected PackMetaEntity() {
    }

    /**
     * Creates a new pack meta entity.
     *
     * @param id              the identifier, {@code null} for a new entity
     * @param project         the owning project
     * @param packType        the type of pack described
     * @param description     the description as a JSON text component
     * @param minFormatMajor  the minimum supported major format
     * @param minFormatMinor  the minimum supported minor format
     * @param maxFormatMajor  the maximum supported major format
     * @param maxFormatMinor  the maximum supported minor format, {@code null} for any minor version
     * @param enabledFeatures the identifiers of the enabled experimental feature flags
     */
    public PackMetaEntity(
            @Nullable UUID id,
            ProjectEntity project,
            PackType packType,
            String description,
            int minFormatMajor,
            int minFormatMinor,
            int maxFormatMajor,
            @Nullable Integer maxFormatMinor,
            Set<String> enabledFeatures
    ) {
        setId(id);
        this.project = project;
        this.packType = packType;
        this.description = description;
        this.minFormatMajor = minFormatMajor;
        this.minFormatMinor = minFormatMinor;
        this.maxFormatMajor = maxFormatMajor;
        this.maxFormatMinor = maxFormatMinor;
        this.enabledFeatures = new LinkedHashSet<>(enabledFeatures);
    }

    /**
     * Checks that the maximum format is not below the minimum format.
     *
     * @return {@code true} if the range is valid
     */
    @AssertTrue(message = "max format must not be below min format")
    public boolean isFormatRangeValid() {
        if (maxFormatMajor != minFormatMajor) {
            return maxFormatMajor > minFormatMajor;
        }
        return maxFormatMinor == null || maxFormatMinor >= minFormatMinor;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public PackType getPackType() {
        return packType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinFormatMajor() {
        return minFormatMajor;
    }

    public int getMinFormatMinor() {
        return minFormatMinor;
    }

    public int getMaxFormatMajor() {
        return maxFormatMajor;
    }

    public @Nullable Integer getMaxFormatMinor() {
        return maxFormatMinor;
    }

    /**
     * Sets the supported format range.
     *
     * @param minFormatMajor the minimum supported major format
     * @param minFormatMinor the minimum supported minor format
     * @param maxFormatMajor the maximum supported major format
     * @param maxFormatMinor the maximum supported minor format, {@code null} for any minor version
     */
    public void setFormatRange(int minFormatMajor, int minFormatMinor, int maxFormatMajor,
                               @Nullable Integer maxFormatMinor) {
        this.minFormatMajor = minFormatMajor;
        this.minFormatMinor = minFormatMinor;
        this.maxFormatMajor = maxFormatMajor;
        this.maxFormatMinor = maxFormatMinor;
    }

    public Set<String> getEnabledFeatures() {
        return enabledFeatures;
    }

    public void setEnabledFeatures(Set<String> enabledFeatures) {
        this.enabledFeatures = new LinkedHashSet<>(enabledFeatures);
    }

    /**
     * Returns the texture asset written as the {@code pack.png} of the pack.
     * <p>
     * The asset is loaded lazily: its id is known without loading it, its other fields only
     * within a transaction.
     * </p>
     *
     * @return the icon asset, or {@code null} when the pack has no icon
     */
    public @Nullable ProjectAssetEntity getIconAsset() {
        return iconAsset;
    }

    /**
     * Sets the texture asset written as the {@code pack.png} of the pack.
     *
     * @param iconAsset the icon asset, or {@code null} for none
     */
    public void setIconAsset(@Nullable ProjectAssetEntity iconAsset) {
        this.iconAsset = iconAsset;
    }
}
