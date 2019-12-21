package by.epam.training.library.impl.stack;

import by.epam.training.library.impl.list.LinkedList;
import by.epam.training.library.interfaces.Collection;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.Stack;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class ListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList;

    public ListStack() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public Iterator<E> getIterator() {
        return new ListStackIterator(linkedList.size() - 1);
    }

    @Override
    public Boolean isEmpty() {
        return linkedList.size() == 0;
    }

    @Override
    public E peek() {
        return linkedList.get(size() - 1);
    }

    @Override
    public E pop() {
        return linkedList.remove(size() - 1);
    }

    @Override
    public void push(E element) {
        if (linkedList.find(element) < 0) {
            add(element);
        } else {
            throw new IllegalStateException("Element " + element.toString() + " is exist");
        }
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
    public void sort(Comparator<E> comparator) {
        linkedList.sort(comparator);
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
        for (E e : array) {
            if (e == null) {
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
        for (T e : array) {
            if (e == null) {
                throw new IllegalArgumentException("Cannot instantiate with nullable type.");
            }
        }

        return linkedList.toArray(array);
    }

    private class ListStackIterator implements Iterator<E> {
        private int index;

        ListStackIterator(int index) {
            this.index = index;
        }

        public boolean hasNext() {
            return (index < size() && index >= 0);
        }

        public E getNext() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int currIndex = index;
            index--;

            return linkedList.get(currIndex);
        }

        public void remove() {
            if (index < 0 || index >= size()) {
                throw new IllegalStateException();
            }

            linkedList.remove(index);
        }

        public void set(E element) {
            if (index < size() && index >= 0) {
                throw new IllegalStateException();
            }

            linkedList.set(index, element);
        }

        @Override
        public void addBefore(E element) {
            ListStack.this.getIterator().addBefore(element);
        }

        @Override
        public void addAfter(E element) {
            ListStack.this.getIterator().addAfter(element);
        }
    }
}
