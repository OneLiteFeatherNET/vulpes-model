package net.onelitefeather.vulpes.api.model.project;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.generator.VulpesGenerator;
import net.onelitefeather.vulpes.api.model.VulpesModel;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

/**
 * Represents a Project in the system. This class is used as an entity for persistence
 * with JPA and Micronaut Data. It contains details related to a project such as display name,
 * key, urls, description, etc.
 * <p>
 * This class is mapped to the database table "projects" and contains fields that
 * are automatically persisted by the JPA and Micronaut Data layers.
 * </p>
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.7.2
 */
@Entity(name = "projects")
@Table(name = "projects", indexes = {
        @Index(name = "idx_projects_key", columnList = "project_key", unique = true)
})
public class ProjectEntity implements VulpesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @VulpesGenerator
    private UUID id;

    @NotNull
    private String displayName;

    @NotNull
    @Column(name = "project_key")
    private String key;

    @Nullable
    @Column(name = "project_url")
    private String projectUrl;

    @Nullable
    @Column(name = "docu_url")
    private String docuUrl;

    @Nullable
    private String description;

    @ColumnDefault(value = "false")
    private boolean labor;

    /**
     * Default constructor for JPA and Micronaut Data.
     * <p>
     * This constructor is required for the JPA provider to instantiate the entity.
     * </p>
     */
    public ProjectEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link ProjectEntity} with the specified values.
     *
     * @param id          of the project
     * @param displayName of the project
     * @param key         of the project
     * @param projectUrl  of the project
     * @param docuUrl     of the project
     * @param description of the project
     * @param labor       of the project
     */
    public ProjectEntity(
            UUID id,
            String displayName,
            String key,
            @Nullable String projectUrl,
            @Nullable String docuUrl,
            @Nullable String description,
            boolean labor
    ) {
        this.id = id;
        this.displayName = displayName;
        this.key = key;
        this.projectUrl = projectUrl;
        this.docuUrl = docuUrl;
        this.description = description;
        this.labor = labor;
    }

    /**
     * Sets the unique identifier of the project.
     *
     * @param id of the project
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Sets the display name of the project.
     *
     * @param displayName of the project
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets the unique key of the project.
     *
     * @param key of the project
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets the project URL.
     *
     * @param projectUrl of the project
     */
    public void setProjectUrl(@Nullable String projectUrl) {
        this.projectUrl = projectUrl;
    }

    /**
     * Sets the documentation URL.
     *
     * @param docuUrl of the project
     */
    public void setDocuUrl(@Nullable String docuUrl) {
        this.docuUrl = docuUrl;
    }

    /**
     * Sets the description of the project.
     *
     * @param description of the project
     */
    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    /**
     * Sets whether the project is a labor project.
     *
     * @param labor of the project
     */
    public void setLabor(boolean labor) {
        this.labor = labor;
    }

    /**
     * Returns the unique identifier of the project.
     *
     * @return the unique identifier of the project
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the display name of the project.
     *
     * @return the display name of the project
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the unique key of the project.
     *
     * @return the key of the project
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the project URL.
     *
     * @return the project url or {@code null} if not set
     */
    @Nullable
    public String getProjectUrl() {
        return projectUrl;
    }

    /**
     * Returns the documentation URL.
     *
     * @return the documentation url or {@code null} if not set
     */
    @Nullable
    public String getDocuUrl() {
        return docuUrl;
    }

    /**
     * Returns the description of the project.
     *
     * @return the description or {@code null} if not set
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the project is a labor project.
     *
     * @return {@code true} if it is a labor project, {@code false} otherwise
     */
    public boolean isLabor() {
        return labor;
    }
}
