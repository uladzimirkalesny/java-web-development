package by.epam.training.bank.dto;

import by.epam.training.bank.enums.DebtStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class CreditDto {
    private final int creditId;
    private final long transactionCount;
    private final BigDecimal creditDebt;
    private final int dayOfCreditPeriod;
    private final DebtStatus debtStatus;
    private final String dateOfCreditDone;

    private final UserDto userDto;
}
