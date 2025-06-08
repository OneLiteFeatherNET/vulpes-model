package net.onelitefeather.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.onelitefeather.vulpes.api.model.NotificationEntity;

import java.util.UUID;

@Repository
public interface NotificationRepository extends PageableRepository<NotificationEntity, UUID> {
}
