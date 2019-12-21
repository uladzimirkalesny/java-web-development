package by.epam.training.library.interfaces;

import java.util.Comparator;

public interface List<E> extends Collection<E> {
    Iterator<E> getIterator();

    int add(E element);

    void addAll(Collection<? extends E> collection);

    void addAll(E[] array);

    E set(int index, E element);

    E remove(int index);

    void clear();

    int find(E element);

    E get(int index);

    <T> T[] toArray(T[] array);

    E[] toArray();

    int size();

    void trim();

    void sort(Comparator<E> comparator);

    void filterMatches(Collection<E> c);

    void filterMatches(E[] a);

    void filterDifference(Collection<E> c);

    void filterDifference(E[] a);

    int getMaxSize();

    void setMaxSize(int maxSize);
}
