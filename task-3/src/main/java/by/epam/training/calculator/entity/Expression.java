package by.epam.training.calculator.entity;

import by.epam.training.calculator.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Expression {
    private BigDecimal leftOperand;
    private BigDecimal rightOperand;
    private OperationType operationType;

    public BigDecimal getResult() {
        return operationType.process(leftOperand, rightOperand);
    }
}
