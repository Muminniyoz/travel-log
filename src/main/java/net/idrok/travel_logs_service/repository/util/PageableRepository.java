package net.idrok.travel_logs_service.repository.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageableRepository<T, IdType> extends CrudRepository<T, IdType> {
    public Page<T> findAll(Pageable pageable);

}
