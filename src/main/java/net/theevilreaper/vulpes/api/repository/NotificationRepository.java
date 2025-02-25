package net.theevilreaper.vulpes.api.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.PageableRepository;
import net.theevilreaper.vulpes.api.model.NotificationModel;

@Repository
public interface NotificationRepository extends PageableRepository<NotificationModel, String> {
}
