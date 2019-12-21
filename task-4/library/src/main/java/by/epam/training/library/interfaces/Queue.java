package by.epam.training.library.interfaces;

public interface Queue<E> extends Collection<E> {
    Iterator<E> getIterator();

    Boolean isEmpty();

    E peek();

    E poll();

    E pull();

    E remove();

    void push(E element);

    void pushAll(Collection<? extends E> collection);

    void pushAll(E[] array);

    int search(E element);

    int clear();

    int size();
}
