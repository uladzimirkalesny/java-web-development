package by.epam.training.calculator.enums;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum OperationType {

    MULTIPLICATION("*", 1) {
        @Override
        public BigDecimal process(BigDecimal left, BigDecimal right) {
            return left.multiply(right);
        }
    },
    DIVISION("/", 1) {
        @Override
        public BigDecimal process(BigDecimal left, BigDecimal right) {
            return left.divide(right, 4, BigDecimal.ROUND_HALF_EVEN);
        }
    },
    ADDITION("+", 2) {
        @Override
        public BigDecimal process(BigDecimal left, BigDecimal right) {
            return left.add(right);
        }
    },
    SUBTRACTION("-", 2) {
        @Override
        public BigDecimal process(BigDecimal left, BigDecimal right) {
            return left.subtract(right);
        }
    };

    private String operationSymbol;
    private int operationPriority;

    public abstract BigDecimal process(BigDecimal left, BigDecimal right);

    public static OperationType getOperationTypeBySymbol(String operationSymbol) {
        OperationType[] values = OperationType.values();
        for (OperationType value : values) {
            if (value.operationSymbol.equals(operationSymbol)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Operation");
    }

    public static List<OperationType> getOperationTypeByPriority() {
        return Arrays.stream(OperationType.values())
                .sorted(Comparator.comparingInt(o -> o.operationPriority))
                .collect(Collectors.toList());
    }

    public String getOperationSymbol() {
        return operationSymbol;
    }

    public int getOperationPriority() {
        return operationPriority;
    }

}
