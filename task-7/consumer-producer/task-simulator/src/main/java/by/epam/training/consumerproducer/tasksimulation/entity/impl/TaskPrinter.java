package by.epam.training.consumerproducer.tasksimulation.entity.impl;

import by.epam.training.consumerproducer.tasksimulation.entity.Task;

public class TaskPrinter implements Task {
    private int value;


    public TaskPrinter(int specificValue) {
        this.value = specificValue;
    }

    @Override
    public int get() {
        throw new RuntimeException("Not Operation method for that task");
    }

    @Override
    public void print() {
        System.out.println(value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " for printing value " + value;
    }
}
