package by.epam.training.task1;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Runner class for task-1.
 *
 * @author Uladzimir Kalesny
 */
public class Runner {
    /**
     * The digital variable for adding one week to current date.
     */
    private static final long PLUS_ONE_WEEK = 1;
    /**
     * The header view for output textual information about date.
     */
    private static final String HEADER = "Hello world: %d %s %d %d %d %d\n";

    public static void main(String[] args) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime todayPlusWeek = today.plusWeeks(PLUS_ONE_WEEK);

        printTaskDateFormat(today);
        printTaskDateFormat(todayPlusWeek);
    }

    /**
     * Method provides textual information about a desired date according to the assignment.
     *
     * @param date LocaleDateTime instance
     */
    private static void printTaskDateFormat(final LocalDateTime date) {
        System.out.printf(Runner.HEADER,
                date.getDayOfMonth(),
                Month.of(date.getMonthValue()),
                date.getSecond(),
                date.getHour(),
                date.getYear(),
                date.getMinute());
    }
}
