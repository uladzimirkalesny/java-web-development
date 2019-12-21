package by.epam.training.calculator.entity;

import by.epam.training.calculator.interfaces.Calculator;
import by.epam.training.calculator.interfaces.Parser;
import by.epam.training.calculator.interfaces.Reader;
import by.epam.training.calculator.interfaces.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public class FileCalculatorImpl implements Calculator {
    private String filePath;
    private Validator validator;
    private Reader fileReader;
    private Parser parser;

    public FileCalculatorImpl() {
        this.fileReader = new FileReaderImpl();
        this.validator = new ExpressionValidatorImpl();
        this.parser = new FileLineCalculationParserImpl();
    }

    public FileCalculatorImpl(final String filePath) {
        this.filePath = filePath;
        this.fileReader = new FileReaderImpl();
        this.validator = new ExpressionValidatorImpl();
        this.parser = new FileLineCalculationParserImpl();
    }

    @Override
    public Map<Integer, List<Expression>> performCalculate() {
        Map<Integer, List<Expression>> map = new HashMap<>();
        List<String> correctLinesList = prepareCalculate();

        int lineCounter = 0;
        System.out.println("Result:");

        for (String correctLine : correctLinesList) {
            if (!correctLine.equals("Incorrect expression")) {
                List<Expression> expressionOrderList = parser.parse(correctLine);

                System.out.println(String.format(Locale.ENGLISH, "%d. %-3d steps; %.4f result; %s", ++lineCounter,
                        expressionOrderList.size(),
                        expressionOrderList.get(expressionOrderList.size() - 1).getResult(),
                        correctLine));

                map.put(lineCounter, expressionOrderList);
            } else {
                System.out.println(++lineCounter + ". Incorrect expression");
            }
        }

        return map;
    }

    private List<String> prepareCalculate() {
        Stream<String> stream = readFile(filePath);
        List<String> afterDeletingSpacesList = formatFile(stream);

        return changeIncorrectExpression(afterDeletingSpacesList);
    }

    private List<String> changeIncorrectExpression(List<String> list) {
        List<String> correctLinesList = new ArrayList<>();

        for (String expression : list) {
            if (isValidData(expression)) {
                correctLinesList.add(expression);
            } else
                correctLinesList.add("Incorrect expression");
        }

        return correctLinesList;
    }

    private boolean isValidData(String s) {
        return validator.isValidData(s);
    }

    private Stream<String> readFile(String path) {
        if (path == null) {
            System.out.println("Incorrect path");
        }
        return fileReader.readData(path);
    }

    private List<String> formatFile(Stream<String> stream) {
        return fileReader.formatData(stream);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
