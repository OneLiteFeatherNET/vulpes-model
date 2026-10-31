package net.onelitefeather.vulpes.api.model.expression;

import jakarta.annotation.Nullable;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import net.onelitefeather.vulpes.api.model.IdentifiableEntity;
import net.onelitefeather.vulpes.api.model.project.ProjectEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * An EvalEx formula owned by a project, together with the variables it reads.
 *
 * <p>Expressions are runtime data of the game, not resource pack content, so they have no resource key.
 * Creation and modification timestamps come from {@link IdentifiableEntity}.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.6.0
 */
@Entity
@Table(name = "expression")
public class ExpressionEntity extends IdentifiableEntity {

    @Column(name = "ui_name", nullable = false)
    private String uiName;

    @Column(name = "expression", nullable = false, columnDefinition = "text")
    private String expression;

    @Nullable
    @Column(name = "comment")
    private String comment;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "expression_variable", joinColumns = @JoinColumn(name = "expression_id"))
    @OrderColumn(name = "variable_order")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ExpressionVariable> variables = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProjectEntity project;

    /**
     * Creates an empty entity, required by JPA.
     */
    protected ExpressionEntity() {
    }

    /**
     * Creates a new expression entity.
     *
     * @param id         the identifier, {@code null} for a new entity
     * @param uiName     the name to display in the UI
     * @param expression the EvalEx formula, may be empty
     * @param comment    an internal description, may be {@code null}
     * @param variables  the variables the expression reads, in order
     * @param project    the owning project
     */
    public ExpressionEntity(
            @Nullable UUID id,
            String uiName,
            String expression,
            @Nullable String comment,
            List<ExpressionVariable> variables,
            ProjectEntity project
    ) {
        setId(id);
        this.uiName = uiName;
        this.expression = expression;
        this.comment = comment;
        this.variables = new ArrayList<>(variables);
        this.project = project;
    }

    public String getUiName() {
        return uiName;
    }

    public String getExpression() {
        return expression;
    }

    public @Nullable String getComment() {
        return comment;
    }

    public List<ExpressionVariable> getVariables() {
        return variables;
    }

    public ProjectEntity getProject() {
        return project;
    }
}
