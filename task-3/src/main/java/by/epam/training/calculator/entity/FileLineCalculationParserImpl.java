package by.epam.training.calculator.entity;

import by.epam.training.calculator.enums.OperationType;
import by.epam.training.calculator.interfaces.Parser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLineCalculationParserImpl implements Parser {

    private static final String EXPRESSION_WITHOUT_BRACKET = "[^()]-?[\\d|\\d.\\d]*([/*\\-+](\\d*\\.?\\d+))+";
    private static final String EXPRESSION_WITH_BRACKET = "\\(-?[\\d|\\d.\\d]*([/*\\-+](\\d*\\.?\\d+))+\\)";

    private static final String EXPRESSION_SYMBOL_REGEXP = "(^[\\-](\\d*\\.?\\d+))|(\\d*\\.?\\d+|[/*\\-+])";

    private static final Pattern patterWithOutBracket = Pattern.compile(EXPRESSION_WITHOUT_BRACKET);
    private static final Pattern patterWithBracket = Pattern.compile(EXPRESSION_WITH_BRACKET);
    private static final Pattern symbolRegExp = Pattern.compile(EXPRESSION_SYMBOL_REGEXP);


    @Override
    public List<Expression> parse(String line) {

        List<Expression> expressions = new ArrayList<>();

        Pattern pattern = line.contains("(") ? patterWithBracket : patterWithOutBracket;
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {

            String group = matcher.group();
            String expressionFromLine = group.replaceAll("[()]", "");

            expressions.addAll(processExpression(expressionFromLine));

            line = line.replace(group, String.valueOf(expressions.get(expressions.size() - 1).getResult()))
                    .replace("--", "+");

            pattern = line.contains("(") ? patterWithBracket : patterWithOutBracket;
            matcher = pattern.matcher(line);

        }

        return expressions;
    }

    private List<Expression> processExpression(String expressionFromLine) {

        List<String> breakBySymbolList = parseExpressionByOperationSymbol(expressionFromLine);
        List<Expression> expressions = new ArrayList<>();

        for (OperationType operationType : OperationType.getOperationTypeByPriority()) {
            while (breakBySymbolList.contains(operationType.getOperationSymbol())) {

                int i = breakBySymbolList.indexOf(operationType.getOperationSymbol());

                String left = breakBySymbolList.remove(i - 1);
                String operation = breakBySymbolList.remove(i - 1);
                String right = breakBySymbolList.remove(i - 1);

                Expression expression = new Expression(new BigDecimal(left), new BigDecimal(right), operationType);

                breakBySymbolList.add(i - 1, String.valueOf(expression.getResult()));

                expressions.add(expression);
            }
        }
        return expressions;
    }

    private List<String> parseExpressionByOperationSymbol(String expressionFromLine) {

        Matcher matcher = symbolRegExp.matcher(expressionFromLine);

        List<String> breakBySymbolList = new ArrayList<>();

        while (matcher.find()) {
            String group = matcher.group();
            if (!group.isEmpty()) {
                breakBySymbolList.add(group);
            }
        }

        return breakBySymbolList;
    }


}
