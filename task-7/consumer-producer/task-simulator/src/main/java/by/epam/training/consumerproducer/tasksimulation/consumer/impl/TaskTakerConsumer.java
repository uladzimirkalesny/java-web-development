package by.epam.training.consumerproducer.tasksimulation.consumer.impl;

import by.epam.training.consumerproducer.tasksimulation.consumer.TaskConsumer;
import by.epam.training.consumerproducer.tasksimulation.entity.Task;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class TaskTakerConsumer implements TaskConsumer {
    private LinkedBlockingQueue<Task> queue;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public TaskTakerConsumer(LinkedBlockingQueue<Task> queue) {
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

                    if (task.getClass().getSimpleName().equals("TaskTaker")) {
                        iterator.remove();
                        System.out.println("TaskTakerConsumer get value " + task.get());
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
