package by.epam.training.bank.enums;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public enum DebtStatus {
    DONE("DONE", BigDecimal.ZERO), IN_PROGRESS("IN PROGRESS", BigDecimal.ONE);

    private final String name;
    private final BigDecimal value;

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public static DebtStatus getDebtStatus(BigDecimal creditDebt) {
        DebtStatus result = DONE;

        if (creditDebt.compareTo(DONE.getValue()) > 0){
            result = IN_PROGRESS;
        }

        return result;
    }
}
