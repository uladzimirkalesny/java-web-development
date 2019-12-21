package by.epam.training.consumerproducer.tasksimulation.consumer.impl;

import by.epam.training.consumerproducer.tasksimulation.consumer.TaskConsumer;
import by.epam.training.consumerproducer.tasksimulation.entity.Task;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class TaskPrinterConsumer implements TaskConsumer {
    private LinkedBlockingQueue<Task> queue;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public TaskPrinterConsumer(LinkedBlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                reentrantLock.lock();

                Iterator<Task> iterator = queue.iterator();

                while (iterator.hasNext()) {

                    Task task = iterator.next();

                    if (task.getClass().getSimpleName().equals("TaskPrinter")) {
                        iterator.remove();
                        System.out.print(getClass().getSimpleName() + " printing value ");
                        task.print();
                        break;
                    }
                }
            } finally {
                reentrantLock.unlock();
            }

            Thread.yield();
        }
    }
}
