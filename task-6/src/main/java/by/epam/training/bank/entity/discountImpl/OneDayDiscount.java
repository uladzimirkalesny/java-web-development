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
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OneDayDiscount extends Discount {
    private LocalDate date;
}
