package net.idrok.travel_logs_service.service.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    public List<T> getAll();

    public Optional<T> getById(ID id);

    public T create(T t);
    public T update(T t);
    public void delete(T t);
    public void deleteById(ID t);
}
