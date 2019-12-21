package by.epam.training.library.interfaces;


public interface Iterator<E> {
    boolean hasNext();

    E getNext();

    void remove();

    void addBefore(E element);

    void addAfter(E element);
}
