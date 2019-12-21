package by.epam.training.consumerproducer.tasksimulation.entity.impl;

import by.epam.training.consumerproducer.tasksimulation.entity.Task;

public class TaskTaker implements Task {
    private int value;

    public TaskTaker(int specificValue) {
        this.value = specificValue;
    }

    @Override
    public void print() {
        throw new RuntimeException("Not Operation method for that task");
    }

    @Override
    public int get() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " for getting value " + value;
    }
}
