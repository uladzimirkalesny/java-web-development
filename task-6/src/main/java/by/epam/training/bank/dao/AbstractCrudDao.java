package by.epam.training.bank.dao;

import by.epam.training.bank.entity.BaseEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractCrudDao<T extends BaseEntity, E> extends BaseJsonDao<E> implements CrudDao<T> {

    protected abstract List<T> getDataEntities();

    protected abstract E setDataEntities(List<T> list);

    @Override
    public List<T> findAll() {
        return getDataEntities();
    }

    @Override
    public void save(T model) {

        List<T> entities = getDataEntities();
        entities.add((T) model);

        E root = setDataEntities(entities);

        rewriteJsonFile(root);

    }

    @Override
    public void delete(int id) {

        List<T> entities = getDataEntities();

        entities.removeIf(t -> t.getId() == id);

        E root = setDataEntities(entities);

        rewriteJsonFile(root);

    }

    @Override
    public void update(T obj) {

        List<T> entities = getDataEntities();

        entities = entities.stream()
                .map(t -> t.getId() == obj.getId() ? obj : t)
                .collect(toList());

        E root = setDataEntities(entities);

        rewriteJsonFile(root);

    }

    @Override
    public T findById(int id) {
        return getDataEntities().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
