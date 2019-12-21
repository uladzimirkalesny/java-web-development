package by.epam.training.changer.entity;

import by.epam.training.changer.interfaces.Reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader implements Reader {
    @Override
    public List<String> readData(String path) {
        List<String> lines = null;
        try {
            lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("The required file in this path was not found.");
        }
        return lines;
    }
}
