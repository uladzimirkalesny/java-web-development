package by.epam.training.horseracing.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Log4j2
@NoArgsConstructor
@Data
public class Horse implements Runnable {
    private int speed;
    private int distance;
    private CountDownLatch startCountDownLatch;
    private CountDownLatch endCountDownLatch;

    public Horse(int distance, CountDownLatch startCountDownLatch, CountDownLatch endCountDownLatch) {
        final int speedRandomGeneratorValue = 30;

        this.speed = new Random().nextInt(speedRandomGeneratorValue) + speedRandomGeneratorValue;
        this.distance = distance;
        this.startCountDownLatch = startCountDownLatch;
        this.endCountDownLatch = endCountDownLatch;

        log.info("Horse with speed : " + speed);
    }

    @Override
    public void run() {

        try {
            if (startCountDownLatch != null) {
                log.debug("Wait all horses before starting ");
                startCountDownLatch.await();
            }

            while (distance > 0) {

                distance = distance - speed;
                log.debug("Change distance to the finish line ");

                changeDistanceToZeroIfLessThenZero();

                log.info(Thread.currentThread()
                               .getName() + " has distance to the finish line : " + distance);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            log.error("Current thread is interrupted while waiting : \n" + e.getMessage());
        }

        log.debug("Try to change endCountDownLatch by 1 if horse cross to the finish line ");
        endCountDownLatch.countDown();

    }

    private void changeDistanceToZeroIfLessThenZero() {
        if (distance < 0) {
            distance = 0;
            log.debug("Change distance to the finish line to 0 ");
        }
    }
}
