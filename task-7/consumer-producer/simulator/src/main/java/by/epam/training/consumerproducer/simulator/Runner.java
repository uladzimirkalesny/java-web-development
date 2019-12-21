package by.epam.training.consumerproducer.simulator;

import by.epam.training.consumerproducer.simulator.entity.impl.IntConsumer;
import by.epam.training.consumerproducer.simulator.entity.impl.IntProducer;
import by.epam.training.consumerproducer.simulator.queue.impl.ProducerConsumerBlockQueue;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int mProducers;
    private static int nConsumers;

    public static void main(String[] args) {
        setProducersFromCommandLine();
        setConsumersFromCommandLine();

        ProducerConsumerBlockQueue<Integer> queue = new ProducerConsumerBlockQueue<>(2);

        ExecutorService consumerPool = Executors.newFixedThreadPool(getnConsumers());
        ExecutorService producerPool = Executors.newFixedThreadPool(getProducers());

        for (int i = 0; i < getProducers(); i++) {
            producerPool.submit(new IntProducer(queue));
        }

        for (int i = 0; i < getnConsumers(); i++) {
            consumerPool.submit(new IntConsumer(queue));
        }

        producerPool.shutdown();
        consumerPool.shutdown();
    }

    private static void setProducersFromCommandLine() {
        System.out.print("Determine the producers count : ");
        int tempData = SCANNER.nextInt();

        if (isValidData(tempData)) {
            mProducers = tempData;
        }
    }

    private static int getProducers() {
        return mProducers;
    }

    private static void setConsumersFromCommandLine() {
        System.out.print("Determine the consumers count : ");
        int tempData = SCANNER.nextInt();

        if (isValidData(tempData)) {
            nConsumers = tempData;
        }
    }

    private static int getnConsumers() {
        return nConsumers;
    }

    private static boolean isValidData(int tempData) {
        if (tempData <= 0) {
            throw new IllegalArgumentException("Incorrect determined value: " + tempData);
        }

        return true;
    }
}
