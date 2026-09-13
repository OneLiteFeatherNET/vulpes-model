package net.onelitefeather.vulpes.api.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Base class for every entity that belongs to a {@link ProjectEntity}. Adds the project
 * reference and the entity's local key, which combine into a namespaced key
 * (e.g. {@code cygnus:whatever}) via {@link #getNamespacedKey()}.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 2.2.0
 */
@MappedSuperclass
public abstract class AbstractEntity extends IdentifiableEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProjectEntity project;

    @NotNull
    private String key;

    /**
     * Default constructor for JPA and Micronaut Data.
     */
    protected AbstractEntity() {
        // No-argument constructor for JPA
    }

    /**
     * Constructs a new {@link AbstractEntity} with the specified local key and project.
     *
     * @param key     the local key of the entity within the project's namespace
     * @param project the project this entity belongs to
     */
    protected AbstractEntity(String key, ProjectEntity project) {
        this.key = key;
        this.project = project;
    }

    /**
     * Returns the project this entity belongs to.
     *
     * @return the project of the entity
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Sets the project this entity belongs to.
     *
     * @param project the project to set
     */
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    /**
     * Returns the local key of the entity within its project's namespace (e.g. {@code whatever}).
     *
     * @return the local key of the entity
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the local key of the entity within its project's namespace (e.g. {@code whatever}).
     *
     * @param key the local key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the full namespaced key, combining the project's key with this entity's local key
     * (e.g. project key {@code cygnus} and local key {@code whatever} become {@code cygnus:whatever}).
     *
     * @return the namespaced key of the entity
     */
    public String getNamespacedKey() {
        return project.getKey() + ":" + key;
    }

    /**
     * Derives the variable name of the entity from its local key, e.g. local key {@code whatever}
     * becomes {@code WHATEVER}.
     *
     * @return the derived variable name of the entity
     */
    public String getVariableName() {
        return key.toUpperCase();
    }
}
