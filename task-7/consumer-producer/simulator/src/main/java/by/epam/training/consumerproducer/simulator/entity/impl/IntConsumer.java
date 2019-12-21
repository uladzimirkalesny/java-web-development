package by.epam.training.consumerproducer.simulator.entity.impl;

import by.epam.training.consumerproducer.simulator.entity.Consumer;
import by.epam.training.consumerproducer.simulator.queue.impl.ProducerConsumerBlockQueue;

public class IntConsumer implements Consumer {
    private ProducerConsumerBlockQueue<Integer> queue;

    public IntConsumer(ProducerConsumerBlockQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            if (queue.take() == null) {
                break;
            }
        }
    }
}
