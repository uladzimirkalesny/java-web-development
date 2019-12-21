package by.epam.training.library.impl.queue;

import by.epam.training.library.impl.list.ArrayList;
import by.epam.training.library.interfaces.Collection;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.Stack;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private ArrayList<E> arrayList;

    public ArrayStack() {
        this.arrayList = new ArrayList<>();
    }

    @Override
    public Iterator<E> getIterator() {
        return new ArrayStackIterator(arrayList.size() - 1);
    }

    @Override
    public Boolean isEmpty() {
        return arrayList.size() == 0;
    }

    @Override
    public E peek() {
        return arrayList.get(size() - 1);
    }

    @Override
    public E pop() {
        return arrayList.remove(size() - 1);
    }

    @Override
    public void push(E element) {
        if (arrayList.find(element) < 0) {
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
        return arrayList.find(element);
    }

    @Override
    public int clear() {
        int count = 0;

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            arrayList.remove(i);
            count++;
        }

        return count;
    }

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public void sort(Comparator<E> comparator) {
        arrayList.sort(comparator);
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
        return arrayList.remove(index);
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
        return arrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        for (T element : array) {
            if (element == null) {
                throw new IllegalArgumentException("Cannot instantiate with nullable type.");
            }
        }

        return arrayList.toArray(array);
    }

    private class ArrayStackIterator implements Iterator<E> {
        private int cursor;

        public ArrayStackIterator(int cursor) {
            this.cursor = cursor;
        }

        @Override
        public boolean hasNext() {
            return (cursor < ArrayStack.this.size() && cursor >= 0);
        }

        @Override
        public E getNext() {
            int i = cursor;
            if (i >= ArrayStack.this.size()) {
                throw new NoSuchElementException();
            }

            cursor = i--;

            return arrayList.get(cursor--);
        }

        @Override
        public void remove() {
            ArrayStack.this.remove(cursor);
        }

        @Override
        public void addBefore(E element) {
            if (cursor == ArrayStack.this.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }

            ArrayStack.this.arrayList.set(cursor + 1, element);
        }

        @Override
        public void addAfter(E element) {
            if (cursor == 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            ArrayStack.this.arrayList.set(cursor - 1, element);
        }
    }
}
