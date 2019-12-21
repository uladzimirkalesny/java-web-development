package by.epam.training.library.interfaces;

public interface Map<K, V> {
    Boolean isEmpty();

    void set(K key, V value);

    void set(Entity entity);

    Entity remove(K key);

    Entity remove(Entity entity);

    List getKeys();

    List getValues();

    V get(K key);

    Entity getEntity(K key);

    Boolean contains(V value);

    int clear();

    int size();
}
