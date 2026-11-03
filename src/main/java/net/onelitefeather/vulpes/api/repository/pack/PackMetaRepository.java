package net.onelitefeather.vulpes.api.repository.pack;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import net.onelitefeather.vulpes.api.model.pack.PackMetaEntity;
import net.onelitefeather.vulpes.api.model.pack.PackType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The {@link PackMetaRepository} interface inherits from {@link CrudRepository} and provides methods to
 * manage {@link PackMetaEntity} objects.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 3.8.0
 */
@Repository
public interface PackMetaRepository extends CrudRepository<PackMetaEntity, UUID> {

    /**
     * Retrieves the pack metadata of a specific project, at most one per {@link PackType}.
     *
     * @param projectId the unique identifier of the project
     * @return the pack metadata of the project
     */
    List<PackMetaEntity> findByProjectId(UUID projectId);

    /**
     * Retrieves the pack metadata of a specific project for one pack type.
     *
     * @param projectId the unique identifier of the project
     * @param packType  the type of pack
     * @return the pack metadata, or empty if none exists
     */
    Optional<PackMetaEntity> findByProjectIdAndPackType(UUID projectId, PackType packType);
}
