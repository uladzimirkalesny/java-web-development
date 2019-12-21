package by.epam.training.consumerproducer.simulator.queue;

public interface BlockQueue<T> {
    void put(T item);
    T take();
}
