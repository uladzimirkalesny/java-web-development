package by.epam.training.bank.enums;

import java.time.LocalDate;

public enum PeriodType {
    DAY {
        @Override
        public LocalDate increaseDays(LocalDate currentDate) {
            return currentDate.plusDays(1);
        }
    }, WEEK {
        @Override
        public LocalDate increaseDays(LocalDate currentDate) {
            return currentDate.plusWeeks(1);
        }
    }, MONTH {
        @Override
        public LocalDate increaseDays(LocalDate currentDate) {
            return currentDate.plusMonths(1);
        }
    }, YEAR {
        @Override
        public LocalDate increaseDays(LocalDate currentDate) {
            return currentDate.plusYears(1);
        }
    };

    public abstract LocalDate increaseDays(LocalDate currentDate);

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}

