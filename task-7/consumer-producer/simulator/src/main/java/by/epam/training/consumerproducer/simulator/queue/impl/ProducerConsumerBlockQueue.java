package by.epam.training.consumerproducer.simulator.queue.impl;

import by.epam.training.consumerproducer.simulator.queue.BlockQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerBlockQueue<T> implements BlockQueue<T> {
    private Queue<T> queue;
    private int capacity = 10;
    private final ReentrantLock reentrantLock = new ReentrantLock(true);
    private final Condition notEmpty = reentrantLock.newCondition();
    private final Condition notFull = reentrantLock.newCondition();
    private AtomicInteger count = new AtomicInteger(0);

    public ProducerConsumerBlockQueue() {
        this.queue = new LinkedList<>();
    }

    public ProducerConsumerBlockQueue(int capacity) {
        this();
        this.capacity = capacity;
    }

    @Override
    public void put(T item) {
        reentrantLock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            System.out.println("PRODUCER " + Thread.currentThread().getName() + " " + item);
            queue.add(item);

            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override
    public T take() {
        reentrantLock.lock();
        try {
            while (queue.size() == 0) {
                if (getCurrentCount() == 0) {
                    return null;
                }
                notEmpty.await();
            }
            T item = queue.remove();
            System.out.println("CONSUMER " + Thread.currentThread().getName() + " " + item);
            notFull.signalAll();

            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

    public void incrementProducer() {
        count.incrementAndGet();
    }

    public void decrementProducer() {
        count.decrementAndGet();
    }

    public int getCurrentCount() {
        return count.get();
    }
}
