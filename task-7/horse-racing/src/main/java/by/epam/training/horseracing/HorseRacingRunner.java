package by.epam.training.horseracing;

import by.epam.training.horseracing.entity.Horse;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
public class HorseRacingRunner {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int START_COUNT_DELAY = 1;
    private static int distance;
    private static int horseNumbers;

    public static void main(String[] args) {
        setDistance();

        setHorseNumbers();

        int horseNumbers = getHorseNumbers();

        CountDownLatch startCountDownLatch = new CountDownLatch(START_COUNT_DELAY);
        CountDownLatch endCountDownLatch = new CountDownLatch(horseNumbers);

        ExecutorService executorService = Executors.newFixedThreadPool(horseNumbers);

        for (int i = 0; i < horseNumbers; i++) {
            executorService.submit(new Horse(getDistance(), startCountDownLatch, endCountDownLatch));
        }

        executorService.shutdown();

        log.debug("Concurrent start for all horses");
        startCountDownLatch.countDown();

        try {
            endCountDownLatch.await();
        } catch (InterruptedException e) {
            log.error("Current thread is interrupted while waiting : \n" + e.getMessage());
        }
    }

    private static void setDistance() {
        final String SET_HEADER = "[from setDistance]";
        log.debug(SET_HEADER + " Try to determine the distance value : ");

        System.out.print("Please determine the distance : ");

        int result = HorseRacingRunner.SCANNER.nextInt();

        if (isValidData(SET_HEADER, result)) {

            log.debug(SET_HEADER + " The determined distance value : " + result);
            distance = result;
        }
    }

    private static int getDistance() {
        log.debug("[from getDistance] Try to getting the distance value : ");
        return distance;
    }

    private static void setHorseNumbers() {
        final String SET_HEADER = "[from setHorseNumbers]";
        log.debug(SET_HEADER + " Try to determine the horse numbers value : ");

        System.out.print("Please determine the horse numbers value : ");

        int result = HorseRacingRunner.SCANNER.nextInt();

        if (isValidData(SET_HEADER, result)) {

            log.debug(SET_HEADER + " The determined the horse numbers value : " + result);
            horseNumbers = result;
        }
    }

    private static int getHorseNumbers() {
        log.debug("[from getHorseNumbers] Try to getting the horse numbers value : ");
        return horseNumbers;
    }

    private static boolean isValidData(String SET_HEADER, int result) {
        if (result <= 0) {
            log.error(SET_HEADER + " Incorrect determined value: " + result);

            throw new IllegalArgumentException(
                    "Please re-run application with correct numeric data from 0 to positive number: ");
        }

        return true;
    }
}
