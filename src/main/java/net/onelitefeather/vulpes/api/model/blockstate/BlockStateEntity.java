package net.onelitefeather.vulpes.api.model.blockstate;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

/**
 * Rules for the blockstate file of one block, e.g. {@code minecraft:pink_petals}.
 *
 * <p>A resource pack can only replace the blockstates of existing blocks, so the {@link #getKey() key} is the
 * full key of the block and not a key in the namespace of the project. A project may hold several
 * blockstates for the same block, each one a context of its own. The export writes the rules of all contexts of a
 * block into one {@code multipart} file.
 *
 * <p>The rules are stored as a JSON array, each rule an object with the fields {@code name}, {@code when},
 * {@code model}, {@code x}, {@code y} and {@code uvlock}. The {@code name} only tells the rules apart in the
 * editor and is left out of the exported file.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 3.12.0
 */
@Entity
@Table(name = "block_states", indexes = {
        @Index(name = "idx_block_states_project_block", columnList = "project_id, key")
})
public class BlockStateEntity extends IdentifiableEntity {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProjectEntity project;

    @NotBlank
    @Column(name = "ui_name", nullable = false)
    private String uiName;

    /** The full key of the block, e.g. {@code minecraft:pink_petals}. */
    @NotBlank
    @Pattern(regexp = "[a-z0-9_.-]+:[a-z0-9_./-]+", message = "must be a namespaced block key")
    @Column(name = "key", nullable = false)
    private String key;

    @Nullable
    @Column(name = "comment")
    private String comment;

    /** The rules as a JSON array, in the order they are written to the file. */
    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "rules", nullable = false)
    private String rules = "[]";

    /**
     * Creates an empty entity, required by JPA.
     */
    protected BlockStateEntity() {
    }

    /**
     * Creates a new blockstate entity.
     *
     * @param id       the identifier, {@code null} for a new entity
     * @param project  the owning project
     * @param uiName   the name shown in the user interface
     * @param key      the full key of the block, e.g. {@code minecraft:pink_petals}
     * @param comment  a comment of the user, {@code null} for none
     * @param rules    the rules as a JSON array
     */
    public BlockStateEntity(
            @Nullable UUID id,
            ProjectEntity project,
            String uiName,
            String key,
            @Nullable String comment,
            String rules
    ) {
        setId(id);
        this.project = project;
        this.uiName = uiName;
        this.key = key;
        this.comment = comment;
        this.rules = rules;
    }

    /**
     * Returns the project the blockstate belongs to.
     *
     * @return the owning project
     */
    public ProjectEntity getProject() {
        return project;
    }

    /**
     * Returns the name shown in the user interface.
     *
     * @return the user interface name
     */
    public String getUiName() {
        return uiName;
    }

    /**
     * Sets the name shown in the user interface.
     *
     * @param uiName the user interface name to set
     */
    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    /**
     * Returns the full key of the block, which decides where the file is written.
     *
     * @return the block key, e.g. {@code minecraft:pink_petals}
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the full key of the block.
     *
     * @param key the block key to set, e.g. {@code minecraft:pink_petals}
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the comment of the user.
     *
     * @return the comment, or {@code null} for none
     */
    public @Nullable String getComment() {
        return comment;
    }

    /**
     * Sets the comment of the user.
     *
     * @param comment the comment to set, {@code null} for none
     */
    public void setComment(@Nullable String comment) {
        this.comment = comment;
    }

    /**
     * Returns the rules as a JSON array.
     *
     * @return the rules
     */
    public String getRules() {
        return rules;
    }

    /**
     * Sets the rules as a JSON array.
     *
     * @param rules the rules to set
     */
    public void setRules(String rules) {
        this.rules = rules;
    }
}
