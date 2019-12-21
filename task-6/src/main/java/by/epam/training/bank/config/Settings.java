package by.epam.training.bank.config;

import by.epam.training.bank.entity.showForImpl.ShowFor;
import by.epam.training.bank.enums.SortType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ShowFor showFor;
    private SortType sortBy;
    private List<String> useDepartments;
    private BigDecimal startCostEUR;
    private BigDecimal startCostUSD;
}
