package by.epam.training.sql.dao;

public interface CrudDao<T> {
    T create(T model);

    T read(int id);

    void update(T model);

    void delete(int id);
}