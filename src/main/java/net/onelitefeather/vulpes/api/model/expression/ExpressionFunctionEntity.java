package net.onelitefeather.vulpes.api.model.expression;

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
 * A function a project defines for its expressions, together with its parameters.
 *
 * <p>Formulas call it by {@link #getName()}. For an {@link ExpressionFunctionKind#INTERFACE} function the
 * name also becomes a Java method name, which is why it has no namespaced resource key. Creation and
 * modification timestamps come from {@link IdentifiableEntity}.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.6.0
 */
@Entity
@Table(name = "expression_function")
public class ExpressionFunctionEntity extends IdentifiableEntity {

    @Column(name = "ui_name", nullable = false)
    private String uiName;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind", nullable = false)
    private ExpressionFunctionKind kind;

    @Enumerated(EnumType.STRING)
    @Column(name = "return_type", nullable = false)
    private ExpressionVariableType returnType;

    @Nullable
    @Column(name = "body", columnDefinition = "text")
    private String body;

    @Nullable
    @Column(name = "comment")
    private String comment;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "expression_function_parameter",
            joinColumns = @JoinColumn(name = "expression_function_id")
    )
    @OrderColumn(name = "parameter_order")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ExpressionVariable> parameters = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ProjectEntity project;

    /**
     * Creates an empty entity, required by JPA.
     */
    protected ExpressionFunctionEntity() {
    }

    /**
     * Creates a new expression function entity.
     *
     * @param id         the identifier, {@code null} for a new entity
     * @param uiName     the name to display in the UI
     * @param name       the identifier used in formulas
     * @param kind       whether the body implements the function or the runtime does
     * @param returnType the type of the returned value
     * @param body       the EvalEx formula or the test stub, may be {@code null}
     * @param comment    an internal description, may be {@code null}
     * @param parameters the parameters, in call order
     * @param project    the owning project
     */
    public ExpressionFunctionEntity(
            @Nullable UUID id,
            String uiName,
            String name,
            ExpressionFunctionKind kind,
            ExpressionVariableType returnType,
            @Nullable String body,
            @Nullable String comment,
            List<ExpressionVariable> parameters,
            ProjectEntity project
    ) {
        setId(id);
        this.uiName = uiName;
        this.name = name;
        this.kind = kind;
        this.returnType = returnType;
        this.body = body;
        this.comment = comment;
        this.parameters = new ArrayList<>(parameters);
        this.project = project;
    }

    public String getUiName() {
        return uiName;
    }

    public String getName() {
        return name;
    }

    public ExpressionFunctionKind getKind() {
        return kind;
    }

    public ExpressionVariableType getReturnType() {
        return returnType;
    }

    public @Nullable String getBody() {
        return body;
    }

    public @Nullable String getComment() {
        return comment;
    }

    public List<ExpressionVariable> getParameters() {
        return parameters;
    }

    public ProjectEntity getProject() {
        return project;
    }
}
