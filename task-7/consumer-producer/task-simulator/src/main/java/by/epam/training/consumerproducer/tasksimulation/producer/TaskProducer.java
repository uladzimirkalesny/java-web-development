package by.epam.training.consumerproducer.tasksimulation.producer;

import by.epam.training.consumerproducer.simulator.entity.Producer;
import by.epam.training.consumerproducer.tasksimulation.entity.Task;
import by.epam.training.consumerproducer.tasksimulation.entity.impl.TaskPrinter;
import by.epam.training.consumerproducer.tasksimulation.entity.impl.TaskTaker;

import java.util.concurrent.LinkedBlockingQueue;

public class TaskProducer implements Producer {
    private LinkedBlockingQueue<Task> queue;
    private Task task;

    public TaskProducer(LinkedBlockingQueue<Task> queue, int i) {
        this.queue = queue;
        this.task = i % 2 == 0 ? new TaskTaker(i) : new TaskPrinter(i);
    }

    @Override
    public void run() {
        try {
            queue.put(task);
            System.out.println("Producer producing task " + task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
