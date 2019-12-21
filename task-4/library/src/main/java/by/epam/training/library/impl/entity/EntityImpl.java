package by.epam.training.library.impl.entity;

import by.epam.training.library.interfaces.Entity;

import java.util.Objects;

public class EntityImpl<K, V> implements Entity<K, V> {
    private K key;
    private V value;

    public EntityImpl(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot instantiate with nullable type.");
        }
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + " " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityImpl)) return false;
        EntityImpl<?, ?> entity = (EntityImpl<?, ?>) o;
        return getKey().equals(entity.getKey()) &&
                getValue().equals(entity.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}
