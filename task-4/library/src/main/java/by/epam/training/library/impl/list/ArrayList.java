package by.epam.training.library.impl.list;

import by.epam.training.library.interfaces.Collection;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.List;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;
    private int maxSize = Integer.MAX_VALUE - 8;
    private Comparator<E> comparator;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(Comparator<E> comparator) {
        this.comparator = comparator;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public Iterator<E> getIterator() {
        return new ArrayListIterator(0);
    }

    public Iterator<E> getIterator(int index) {
        return new ArrayListIterator(index);
    }

    public int addWithoutSort(E element) {

        validateCapacity(this.size);
        elementData[size++] = element;

        return find(element);
    }

    public int add(E element) {

        validateCapacity(this.size);
        elementData[size++] = element;

        if (this.comparator != null) {
            sort(comparator);
        } else {
            defaultSort();
        }

        return find(element);
    }

    private void validateCapacity(int size) {
        if (size > maxSize) {
            throw new ArrayIndexOutOfBoundsException("Maximum array size exceeded!");
        }

        int oldCapacity = elementData.length;

        if (size >= oldCapacity) {
            Object[] oldData = elementData;
            int newCapacity = (oldCapacity * 3) / 2 + 1; //Size increases by 1.5 times+1.

            if (newCapacity < size) {
                newCapacity = size;
            }

            elementData = new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, this.size);
        }
    }

    public void addAll(Collection<? extends E> collection) {
        addAll(collection.toArray());
    }

    public void addAll(E[] array) {
        if ((array.length + size) > maxSize) {
            throw new ArrayIndexOutOfBoundsException("Maximum array size exceeded!");
        }

        for (int i = 0; i < array.length; i++) {
            this.add(array[i]);
        }

        if (this.comparator != null) {
            sort(comparator);
        } else {
            defaultSort();
        }
    }

    public void addAllWithoutSort(E[] array) {
        if ((array.length + size) > maxSize) {
            throw new ArrayIndexOutOfBoundsException("Maximum array size exceeded!");
        }

        for (int i = 0; i < array.length; i++) {
            this.addWithoutSort(array[i]);
        }
    }

    public void addAllWithoutSort(Collection<? extends E> collection) {
        addAllWithoutSort(collection.toArray());
    }

    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = (E) this.elementData[index];
        this.elementData[index] = element;

        if (this.comparator != null) {
            sort(comparator);
        } else {
            defaultSort();
        }

        return oldValue;
    }

    public E remove(int index) {
        rangeCheck(index);

        Object oldValue = elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;

        return (E) oldValue;
    }

    public E setWithoutSort(int index, E element) {
        rangeCheck(index);

        E oldValue = (E) this.elementData[index];
        this.elementData[index] = element;

        return oldValue;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    public int find(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (element.equals(elementData[i]))
                    return i;
        }

        return -1;
    }

    public E get(int index) {
        rangeCheck(index);

        return (E) elementData[index];
    }

    public <T> T[] toArray(T[] array) {
        Object[] arr = new Object[array.length + elementData.length];

        System.arraycopy(this.toArray(), 0, arr, 0, size);
        System.arraycopy(array, 0, arr, size, array.length);

        ArrayList<T> list = new ArrayList<>();
        list.addAll((T[]) arr);
        list.trim();

        return list.toArray();
    }

    public E[] toArray() {
        Object[] arr = new Object[size];
        System.arraycopy(elementData, 0, arr, 0, size);

        return (E[]) arr;
    }

    public int size() {
        return size;
    }

    public void trim() {
        int count = 0;
        for (Object i : elementData) {
            if (i != null) {
                count++;
            }
        }

        Object[] newArray = new Object[count];

        int index = 0;
        for (Object i : elementData) {
            if (i != null) {
                newArray[index++] = i;
            }
        }

        size = newArray.length;
        elementData = newArray;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;

        if (maxSize < size) {
            Object[] arr = new Object[maxSize];
            System.arraycopy(elementData, 0, arr, 0, arr.length);
            elementData = arr;
        }

        size = this.maxSize;
    }

    public void sort(Comparator<E> comparator) {
        int n = size;
        Object temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (comparator.compare((E) elementData[j - 1], (E) elementData[j]) > 0) {
                    temp = elementData[j - 1];
                    elementData[j - 1] = elementData[j];
                    elementData[j] = temp;
                }
            }
        }
    }

    private void defaultSort() {
        int n = size;
        Object temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (compare(elementData[j - 1], elementData[j]) > 0) {
                    temp = elementData[j - 1];
                    elementData[j - 1] = elementData[j];
                    elementData[j] = temp;
                }
            }
        }
    }

    public int compare(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return 1;
        }
        if (o2 == null) {
            return -1;
        }
        return o1.hashCode() - o2.hashCode();
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void filterMatches(Collection<E> collection) {
        filterMatches(collection.toArray());
    }

    public void filterMatches(E[] array) {
        ArrayList<E> arrayList = new ArrayList<>();
        Object[] data = this.toArray();

        for (Object element : array) {
            for (Object element2 : data) {
                if (element.equals(element2)) {
                    arrayList.add((E) element);
                    break;
                }
            }
        }

        this.clear();
        this.addAll(arrayList);
    }

    public void filterDifference(Collection<E> collection) {
        filterDifference(collection.toArray());
    }

    public void filterDifference(E[] array) {
        ArrayList<E> arrayList = new ArrayList<>();

        for (int i = 0; i < size; ++i) {
            boolean found = false;

            for (int j = 0; j < array.length; ++j) {
                if (array[j].equals(elementData[i])) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                arrayList.add((E) elementData[i]);
            }
        }

        arrayList.trim();

        this.elementData = arrayList.toArray();
        this.size = this.elementData.length;
    }

    private class ArrayListIterator implements Iterator<E> {
        int cursor = 0;

        public ArrayListIterator(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return cursor < ArrayList.this.size;
        }

        @Override
        public E getNext() {
            int i = cursor;

            if (i >= ArrayList.this.size) {
                throw new NoSuchElementException();
            }

            cursor = i++;
            return (E) elementData[cursor++];
        }

        @Override
        public void remove() {
            ArrayList.this.remove(cursor);
        }

        @Override
        public void addBefore(E element) {
            if (cursor == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            ArrayList.this.set(cursor - 1, element);
        }

        @Override
        public void addAfter(E element) {
            if (cursor == ArrayList.this.size) {
                throw new ArrayIndexOutOfBoundsException();
            }

            ArrayList.this.set(cursor + 1, element);
        }
    }
}
