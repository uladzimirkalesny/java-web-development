package by.epam.training.calculator.entity;

import by.epam.training.calculator.interfaces.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionValidatorImpl implements Validator {

    public ExpressionValidatorImpl() {
    }

    public boolean isValidData(String string) {
        boolean result = true;

        if (!isEqualCountOpenCloseBrackets(string)
                || isCharacterInLineExist(string)
                || isEmptyContentInBrackets(string)
                || isCorrectBracketsOrder(string)
                || hasDivideByZero(string)) {

            result = false;
        }

        return result;
    }

    private boolean hasDivideByZero(String line) {
        return findMatch("\\/0", line);

    }

    private boolean isCharacterInLineExist(String line) {
        return findMatch("[^\\d()+\\-.*/]", line);
    }

    private boolean isCorrectBracketsOrder(String line) {
        return findMatch("\\)\\d+(\\.)?\\d*?[*|/|+\\-]\\d*(\\.)?\\d*\\(", line);
    }

    private boolean isEmptyContentInBrackets(String line) {
        return findMatch("\\(\\d{0}\\)", line);
    }

    private boolean findMatch(String regex, String line) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        return m.find();
    }

    private boolean isEqualCountOpenCloseBrackets(String line) {
        return getOpenBracketsCountByLine(line) == getCloseBracketsCountByLine(line);
    }

    private long getCloseBracketsCountByLine(String line) {
        return line.chars()
                .mapToObj(intChar -> (char) intChar)
                .filter(element -> element.equals(')'))
                .count();
    }

    private long getOpenBracketsCountByLine(String line) {
        return line.chars()
                .mapToObj(intChar -> (char) intChar)
                .filter(element -> element.equals('('))
                .count();
    }
}
