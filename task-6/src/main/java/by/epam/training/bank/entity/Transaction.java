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
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Transaction extends BaseEntity {
    private LocalDate date;
    private int userId;
    private int creditId;
    private CurrencyType currency;
    private BigDecimal money;

}
