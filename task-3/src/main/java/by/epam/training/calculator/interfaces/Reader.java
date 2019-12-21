package by.epam.training.calculator.interfaces;

import java.util.List;
import java.util.stream.Stream;

public interface Reader {
    Stream<String> readData(String path);

    List<String> formatData(Stream<String> stream);
}
