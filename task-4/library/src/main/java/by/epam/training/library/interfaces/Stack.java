package by.epam.training.library.interfaces;

import java.util.Comparator;

public interface Stack<E> extends Collection<E> {
    Iterator<E> getIterator();

    Boolean isEmpty();

    E peek();

    E pop();

    void push(E element);

    void pushAll(Collection<? extends E> collection);

    void pushAll(E[] array);

    int search(E element);

    int clear();

    int size();

    void sort(Comparator<E> comparator);
}
