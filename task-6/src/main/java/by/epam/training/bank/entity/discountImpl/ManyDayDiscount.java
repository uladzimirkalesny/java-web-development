package by.epam.training.bank.entity.discountImpl;

import by.epam.training.bank.entity.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class ManyDayDiscount extends Discount {
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
