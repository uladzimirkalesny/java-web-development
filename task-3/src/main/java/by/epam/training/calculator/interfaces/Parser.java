package by.epam.training.calculator.interfaces;

import by.epam.training.calculator.entity.Expression;

import java.util.List;

public interface Parser {
    List<Expression> parse(String line);
}
