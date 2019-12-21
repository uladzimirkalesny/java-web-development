package by.epam.training.calculator.interfaces;

import by.epam.training.calculator.entity.Expression;

import java.util.List;
import java.util.Map;

public interface Calculator {
    Map<Integer, List<Expression>> performCalculate();
}
