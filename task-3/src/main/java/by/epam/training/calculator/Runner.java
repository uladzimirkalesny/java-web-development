package by.epam.training.calculator;

import by.epam.training.calculator.entity.Expression;
import by.epam.training.calculator.entity.FileCalculatorImpl;
import by.epam.training.calculator.entity.Menu;
import by.epam.training.calculator.interfaces.Calculator;

import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        try {
            Calculator calculator = new FileCalculatorImpl(args[0]);
            Menu menu = new Menu();

            Map<Integer, List<Expression>> calculationMap = calculator.performCalculate();

            menu.chooseExpressionAndStep(calculationMap);
        } catch (RuntimeException e) {
            System.out.print("Incorrect path");
        }
    }
}
