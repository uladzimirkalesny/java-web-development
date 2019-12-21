package by.epam.training.bank.entity;

import by.epam.training.bank.enums.PeriodType;
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
public class Credit extends BaseEntity {
    private int userId;
    private LocalDate date;
    private PeriodType period;
    private BigDecimal money;
    private BigDecimal rate;
}
