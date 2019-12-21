package by.epam.training.changer.entity;

import by.epam.training.changer.exceptions.DuplicateContainsException;
import by.epam.training.changer.exceptions.NoOneWayException;
import by.epam.training.changer.interfaces.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Changer {
    private Reader reader;

    private String filePath;

    public Changer() {
        this.reader = new FileReader();
    }

    public Changer(String filePath) {
        this.reader = new FileReader();
        this.filePath = filePath;
    }

    private List<String> readData() {
        return reader.readData(filePath);
    }

    public void performChange() {
        int amount;
        List<Integer> values;
        List<List<Integer>> combineList;

        List<String> strings = readData();
        List<String[]> data = prepareData(strings);

        for (String[] line : data) {
            try {
                amount = Integer.parseInt(line[0]);
                values = Arrays.stream(line)
                        .skip(1)
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                combineList = combineAmount(amount, values);

                printChange(amount, values, combineList);

            } catch (NumberFormatException e) {
                System.out.println("Error: There are not coins");
            } catch (NoOneWayException e) {
                System.out.println("Error: No one way");
            } catch (DuplicateContainsException e) {
                System.out.println("Error: Duplicates");
            }
            System.out.println();
        }
    }

    private void printChange(int amount, List<Integer> values, List<List<Integer>> combineList) {
        System.out.print(amount + " ");
        System.out.println(convertArrayToStringMethod(values));

        if (values.size() == 0) {
            throw new NumberFormatException();
        }
        if (!hasExchange(amount, values)) {
            throw new NoOneWayException();
        }

        for (int i = 0; i < combineList.size(); i++) {
            if (combineList.get(i).size() > 0) {
                for (int j = 0; j < combineList.get(i).size(); j++) {
                    if (hasDuplicates(values)) {
                        throw new DuplicateContainsException();
                    }
                    System.out.print(combineList.get(i).get(j) + " ");
                }
            }
            System.out.println();
        }
    }

    private List<String[]> prepareData(List<String> strings) {
        List<String[]> data = new ArrayList<>();

        for (String string : strings) {
            String[] s = string.split(" ");
            data.add(s);
        }

        return data;
    }

    private String convertArrayToStringMethod(List<Integer> list) {

        return list.stream().map(Object::toString).collect(Collectors.joining(" "));
    }


    private boolean hasDuplicates(List<Integer> values) {
        Set<Integer> uniques = new HashSet<>(values);

        return values.size() > uniques.size();
    }

    private boolean hasExchange(int amount, List<Integer> values) {
        boolean result = true;
        if (values.size() == 1 && amount == values.get(0)) {
            result = false;
        }

        if (values.size() >= 1) {
            for (Integer value : values) {
                if (amount % value != 0) {
                    result = false;
                }
            }
        }

        return result;
    }

    private void findNumbers(List<Integer> values, int amount, List<List<Integer>> accumulator,
                             List<Integer> temp, int counter) {
        if (amount < 0) {
            return;
        }

        if (amount == 0) {
            accumulator.add(new ArrayList<>(temp));
            return;
        }

        while (counter < values.size() && amount - values.get(counter) >= 0) {
            temp.add(values.get(counter));

            findNumbers(values, amount - values.get(counter), accumulator, temp, counter);
            counter++;

            temp.remove(temp.size() - 1);
        }
    }

    private List<List<Integer>> combineAmount(int amount, List<Integer> values) {
        Collections.sort(values);

        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> accumulator = new ArrayList<>();

        findNumbers(values, amount, accumulator, temp, 0);

        return accumulator;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
