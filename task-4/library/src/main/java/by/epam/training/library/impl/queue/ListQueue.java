package by.epam.training.library.impl.queue;

import by.epam.training.library.impl.list.LinkedList;
import by.epam.training.library.interfaces.Collection;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.Queue;

public class ListQueue<E> implements Queue<E> {
    private LinkedList<E> linkedList;

    public ListQueue() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public Iterator<E> getIterator() {
        return linkedList.getIterator();
    }

    @Override
    public Boolean isEmpty() {
        return linkedList.size() == 0;
    }

    @Override
    public E peek() {
        if (linkedList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return linkedList.get(0);
    }

    @Override
    public E poll() {
        if (linkedList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return linkedList.remove(0);
    }

    @Override
    public E pull() {
        if (linkedList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return linkedList.get(size() - 1);
    }

    @Override
    public E remove() {
        if (linkedList.size() == 0) {
            throw new IndexOutOfBoundsException("Queue is empty!");
        }

        return linkedList.remove(size() - 1);
    }

    @Override
    public void push(E element) {
        add(element);
    }

    @Override
    public void pushAll(Collection<? extends E> collection) {
        addAll(collection);
    }

    @Override
    public void pushAll(E[] array) {
        addAll(array);
    }

    @Override
    public int search(E element) {
        return linkedList.find(element);
    }

    @Override
    public int clear() {
        int count = 0;

        Iterator iterator = linkedList.getIterator();

        while (iterator.hasNext()) {
            iterator.getNext();
            iterator.remove();
            count++;
        }

        return count;
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public int add(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Cannot instantiate with nullable type.");
        }

        return linkedList.addWithoutSort(element);
    }

    @Override
    public E remove(int index) {
        return linkedList.remove(index);
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

        linkedList.addAllWithoutSort(array);
    }

    @Override
    public E[] toArray() {
        return linkedList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        for (T element : array) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot instantiate with nullable type.");
            }
        }

        return linkedList.toArray(array);
    }
}
