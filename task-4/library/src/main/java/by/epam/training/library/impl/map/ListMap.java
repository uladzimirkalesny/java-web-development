package by.epam.training.library.impl.map;

import by.epam.training.library.impl.entity.EntityImpl;
import by.epam.training.library.impl.list.LinkedList;
import by.epam.training.library.interfaces.Entity;
import by.epam.training.library.interfaces.List;
import by.epam.training.library.interfaces.Map;


public class ListMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entity<K, V>>[] table;
    private int size;

    public ListMap() {
        this.table = new LinkedList[DEFAULT_CAPACITY];
    }


    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Cannot instantiate with nullable type.");
        }

        if (isContain(key)) {
            throw new IllegalArgumentException("Element is already exist");
        }

        size++;

        int index = indexFor(hash(key), table.length);

        validateCapacity(index);

        Entity<K, V> newEntity = new EntityImpl<>(key, value);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
            table[index].add(newEntity);
        } else {
            LinkedList<Entity<K, V>> elements = table[index];
            for (int i = 0; i < elements.size() - 1; i++) {
                if (elements.get(i).getKey().equals(newEntity.getKey())) {
                    elements.set(i, newEntity);
                } else {
                    elements.add(newEntity);
                }
            }
        }
    }

    private void resize() {
        LinkedList<Entity<K, V>>[] newTable = new LinkedList[this.table.length];
        for (int i = 0; i < table.length - 1; i++) {

            if (table[i] == null) {
                continue;
            } else {
                int hashOfKey = hash(table[i].get(0).getKey());
                int index = indexFor(hashOfKey, table.length);

                newTable[index] = table[i];
            }
        }

        this.table = newTable;
    }

    private void validateCapacity(int size) {
        int oldCapacity = table.length;
        if (size > oldCapacity) {
            Object[] oldData = table;
            int newCapacity = (oldCapacity * 3) / 2 + 1; //Size increases by 1.5 times+1.

            if (newCapacity < size) {
                newCapacity = size;
            }

            table = new LinkedList[newCapacity];
            System.arraycopy(oldData, 0, table, 0, this.size);
        }
    }

    private int hash(Object key) {
        return key.hashCode();
    }

    private int indexFor(int h, int length) {
        return Math.abs(h % (length - 1));
    }

    @Override
    public void set(Entity entity) {
        set((K) entity.getKey(), (V) entity.getValue());
    }

    @Override
    public Entity remove(K key) {
        Entity<K, V> removed = null;

        int index = indexFor(hash(key), table.length);

        if (table[index].size() == 0) {
            return null;
        } else if (table[index].size() == 1) {
            removed = table[index].get(0);
            table[index] = null;
            size--;
        } else if (table[index].size() > 1) {
            for (int i = 0; i < table[index].size() - 1; i++) {
                if (table[index].get(i).getKey().equals(key)) {
                    table[index].remove(i);
                }
            }
        }

        resize();

        return removed;
    }

    @Override
    public Entity remove(Entity entity) {
        return remove((K) entity.getKey());
    }

    @Override
    public List<K> getKeys() {
        List<K> keys = new LinkedList<>();

        for (LinkedList<Entity<K, V>> bucket : table) {
            if (bucket == null) {
                continue;
            } else {
                for (int i = 0; i < bucket.size(); i++) {
                    keys.add(bucket.get(i).getKey());
                }
            }
        }

        return keys;
    }

    @Override
    public List<V> getValues() {
        List<V> values = new LinkedList<>();

        for (LinkedList<Entity<K, V>> bucket : table) {
            if (bucket == null) {
                continue;
            } else {
                for (int i = 0; i < bucket.size(); i++) {
                    values.add(bucket.get(i).getValue());
                }
            }
        }

        return values;
    }

    @Override
    public V get(K key) {
        V value = null;

        int index = indexFor(hash(key), table.length);

        if (table[index] == null) {
            return null;
        } else if (table[index].size() == 1) {
            value = table[index].get(0).getValue();
        } else {
            for (int i = 0; i < table[index].size() - 1; i++) {
                if (table[index].get(i).getKey().equals(key)) {
                    value = table[index].get(i).getValue();
                }
            }
        }

        return value;
    }

    @Override
    public Entity getEntity(K key) {
        Entity<K, V> entity = null;

        V value = get(key);
        entity = new EntityImpl<>(key, value);

        return entity;
    }

    @Override
    public Boolean contains(V value) {
        boolean contains = false;

        for (LinkedList<Entity<K, V>> bucket : table) {
            if (bucket == null) {
                continue;
            } else {
                for (int i = 0; i < bucket.size(); i++) {

                    if (bucket.get(i).getValue().equals(value)) {
                        contains = true;
                    }
                }
            }
        }

        return contains;
    }

    private Boolean isContain(K key) {
        boolean contains = false;

        for (LinkedList<Entity<K, V>> bucket : table) {
            if (bucket == null) {
                continue;
            } else {
                for (int i = 0; i < bucket.size(); i++) {

                    if (bucket.get(i).getKey().equals(key)) {
                        contains = true;
                    }
                }
            }
        }

        return contains;
    }

    @Override
    public int clear() {
        int count = 0;

        for (LinkedList<Entity<K, V>> bucket : table) {
            if (bucket != null) {
                bucket = null;
                count++;
            }
        }

        size = 0;

        return count;
    }

    @Override
    public int size() {
        return size;
    }
}
