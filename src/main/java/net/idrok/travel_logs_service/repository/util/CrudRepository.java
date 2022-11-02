package net.idrok.travel_logs_service.repository.util;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Basis repository interface for real repositories
 * @param <T>
 * @param <IdType>
 */
public interface CrudRepository<T, IdType> {
    /**
     * find all elements from database and return them as List
     * @return result as List
     */
    public List<T> findAll();
    public Optional<T> findById(IdType id);

    public T create(T t);
    public T update(T t);
    public void delete(T t);
    public void deleteById(IdType t);
}
