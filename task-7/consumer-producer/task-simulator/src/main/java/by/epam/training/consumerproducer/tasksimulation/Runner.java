package by.epam.training.consumerproducer.tasksimulation;

import by.epam.training.consumerproducer.tasksimulation.consumer.impl.TaskPrinterConsumer;
import by.epam.training.consumerproducer.tasksimulation.consumer.impl.TaskTakerConsumer;
import by.epam.training.consumerproducer.tasksimulation.entity.Task;
import by.epam.training.consumerproducer.tasksimulation.producer.TaskProducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;


public class Runner {
    private static final int COUNT_FOR_TASK = 10;

    public static void main(String[] args) {
        LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<>(2);

        ExecutorService producerPool = Executors.newFixedThreadPool(COUNT_FOR_TASK);
        ExecutorService consumerPool = Executors.newFixedThreadPool(COUNT_FOR_TASK);

        List<Future> tasks = new ArrayList<>();
        for (int i = 0; i < COUNT_FOR_TASK; i++) {
            tasks.add(producerPool.submit(new TaskProducer(queue, i)));
        }

        List<Runnable> consumers = new ArrayList<>();

        for (int i = 0; i < COUNT_FOR_TASK; i++) {
            if (i % 2 == 0) {
                consumers.add(new TaskTakerConsumer(queue));
            } else {
                consumers.add(new TaskPrinterConsumer(queue));
            }
        }

        for (Runnable consumer : consumers) {
            consumerPool.submit(consumer);
        }

        for (Future task : tasks) {
            while (!task.isDone());
        }
        producerPool.shutdown();

        while (!queue.isEmpty());
        consumerPool.shutdownNow();
    }
}
