package net.onelitefeather.vulpes.api.repository.expression;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.expression.ExpressionEntity;

import java.util.UUID;

/**
 * The {@link ExpressionRepository} interface inherits from {@link PageableRepository} and provides methods to
 * manage {@link ExpressionEntity} objects.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 3.7.0
 */
@Repository
public interface ExpressionRepository extends PageableRepository<ExpressionEntity, UUID> {

    /**
     * Retrieves the expressions that belong to a specific project.
     *
     * @param projectId the unique identifier of the project
     * @param pageable  the pagination information
     * @return a page of the expressions of the project
     */
    Page<ExpressionEntity> findByProjectId(UUID projectId, Pageable pageable);
}
