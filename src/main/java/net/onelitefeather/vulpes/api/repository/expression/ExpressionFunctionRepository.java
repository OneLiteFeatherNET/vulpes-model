package net.onelitefeather.vulpes.api.repository.expression;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.expression.ExpressionFunctionEntity;

import java.util.UUID;

/**
 * The {@link ExpressionFunctionRepository} interface inherits from {@link PageableRepository} and provides
 * methods to manage {@link ExpressionFunctionEntity} objects.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.7.0
 */
@Repository
public interface ExpressionFunctionRepository extends PageableRepository<ExpressionFunctionEntity, UUID> {

    /**
     * Retrieves the functions that belong to a specific project.
     *
     * @param projectId the unique identifier of the project
     * @param pageable  the pagination information
     * @return a page of the functions of the project
     */
    Page<ExpressionFunctionEntity> findByProjectId(UUID projectId, Pageable pageable);
}
