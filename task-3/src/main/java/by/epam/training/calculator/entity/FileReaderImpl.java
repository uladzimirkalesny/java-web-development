package by.epam.training.calculator.entity;

import by.epam.training.calculator.interfaces.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImpl implements Reader {
    @Override
    public Stream<String> readData(String path) {
        Stream<String> lines = null;
        try {
            lines = Files.lines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return lines;
    }

    @Override
    public List<String> formatData(Stream<String> lines) {
        List<String> result = new ArrayList<>();

        if (lines != null) {
            result = lines
                    .map(string -> string.replaceAll("\\s", ""))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
