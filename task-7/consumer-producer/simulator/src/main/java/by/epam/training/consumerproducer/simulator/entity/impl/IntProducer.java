package by.epam.training.consumerproducer.simulator.entity.impl;

import by.epam.training.consumerproducer.simulator.entity.Producer;
import by.epam.training.consumerproducer.simulator.queue.impl.ProducerConsumerBlockQueue;

import java.util.Random;

public class IntProducer implements Producer {
    private ProducerConsumerBlockQueue<Integer> queue;
    private int element;

    public IntProducer(ProducerConsumerBlockQueue<Integer> queue) {
        this.queue = queue;
        this.queue.incrementProducer();
        this.element = new Random().nextInt(100);
    }

    @Override
    public void run() {
        try {
            queue.put(element);
        } finally {
            queue.decrementProducer();
        }
    }
}
