package by.epam.training.bank.entity;

import by.epam.training.bank.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Discount extends BaseEntity {
    private DiscountType type;
    private BigDecimal discount;
}
