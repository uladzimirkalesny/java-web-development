package by.epam.training.library.interfaces;

public interface Collection<E> extends Iterable<E> {
    Iterator<E> getIterator();

    int size();

    int add(E element);

    E remove(int index);

    void addAll(Collection<? extends E> collection);

    void addAll(E[] array);

    E[] toArray();

    <T> T[] toArray(T[] array);
}
