package by.epam.training.library.impl.queue;

import by.epam.training.library.impl.list.ArrayList;
import by.epam.training.library.interfaces.Collection;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.Queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private ArrayList arrayList;

    public ArrayQueue() {
        this.arrayList = new ArrayList();
    }

    @Override
    public Iterator<E> getIterator() {
        return new ArrayQueueIterator(0);
    }

    @Override
    public Boolean isEmpty() {
        return arrayList.size() == 0;
    }

    @Override
    public E peek() {
        if (arrayList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return (E) arrayList.get(0);
    }

    @Override
    public E poll() {
        if (arrayList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return (E) arrayList.remove(0);
    }

    @Override
    public E pull() {
        if (arrayList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return (E) arrayList.get(size() - 1);
    }

    @Override
    public E remove() {
        if (arrayList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return (E) arrayList.remove(size() - 1);
    }

    @Override
    public void push(E element) {
        add(element);
    }

    @Override
    public void pushAll(E[] array) {
        addAll(array);
    }

    @Override
    public void pushAll(Collection<? extends E> collection) {
        addAll(collection);
    }

    @Override
    public int search(E element) {
        return arrayList.find(element);
    }

    @Override
    public int clear() {
        int count = 0;

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            remove(i);
            count++;
        }

        return count;
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public int add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot instantiate with nullable type.");
        }

        return arrayList.addWithoutSort(element);
    }

    @Override
    public E remove(int index) {
        return (E) arrayList.remove(index);
    }

    @Override
    public void addAll(Collection<? extends E> collection) {
        addAll(collection.toArray());
    }

    @Override
    public void addAll(E[] array) {
        for (E element : array) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot instantiate with nullable type.");
            }
        }

        arrayList.addAllWithoutSort(array);
    }

    @Override
    public E[] toArray() {
        return (E[]) arrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        for (T element : array) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot instantiate with nullable type.");
            }
        }

        return (T[]) arrayList.toArray(array);
    }

    private class ArrayQueueIterator implements Iterator<E> {
        private int cursor;

        public ArrayQueueIterator(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return (cursor < ArrayQueue.this.size() && cursor >= 0);
        }

        @Override
        public E getNext() {
            int i = cursor;

            if (i >= ArrayQueue.this.size()) {
                throw new NoSuchElementException();
            }

            cursor = i--;
            return (E) arrayList.get(cursor++);
        }

        @Override
        public void remove() {
            ArrayQueue.this.remove(cursor);
        }

        @Override
        public void addBefore(E element) {
            if (cursor == ArrayQueue.this.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }

            ArrayQueue.this.arrayList.set(cursor + 1, element);
        }

        @Override
        public void addAfter(E element) {
            if (cursor == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            ArrayQueue.this.arrayList.set(cursor - 1, element);
        }
    }
}

