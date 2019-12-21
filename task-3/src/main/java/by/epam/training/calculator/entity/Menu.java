package by.epam.training.calculator.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public void chooseExpressionAndStep(Map<Integer, List<Expression>> map) {
        int expressionNumber = 0;

        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Choose and enter expression's number and step:");

            String next = scanner.nextLine();
            int[] arr = Arrays.stream(next.split(" ")).mapToInt(Integer::parseInt).toArray();

            expressionNumber = arr[0];

            Expression expression = map.get(expressionNumber).get(arr[1]);
            System.out.println(String.format("Result:\n" + expressionNumber
                            + ". %s %s %s", expression.getLeftOperand(),
                    expression.getOperationType().getOperationSymbol(),
                    expression.getRightOperand()));

        } catch (Exception e) {
            System.out.println("Result:\n" + expressionNumber + ". Incorrect value");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
