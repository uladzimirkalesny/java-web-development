package by.epam.training.bank.entity;

import by.epam.training.bank.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Event extends BaseEntity {
    private CurrencyType currency;
    private BigDecimal cost;
    private LocalDate date;
    @ToString.Exclude
    private final transient int EVENT_PER_DAY = 1;
}
