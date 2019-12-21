package by.epam.training.library.repository;

import java.util.List;

public interface CrudRepository<T> {
    int create(T model);

    T findById(int id);

    List<T> findAll();

    void update(T model);

    void delete(int id);
}