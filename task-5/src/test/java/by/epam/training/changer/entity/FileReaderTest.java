package by.epam.training.changer.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FileReaderTest {
    private static final String CORRECT_FILE_NAME = "input";
    private static final String INCORRECT_FILE_NAME = "input1";
    private List<String> list;
    private File correctFile;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        correctFile = new File(Objects.requireNonNull(classLoader.getResource(CORRECT_FILE_NAME)).getFile());
    }

    @After
    public void tearDown() {
        list.clear();
    }


    @Test(expected = IOException.class)
    public void checkInputFileWhenNotFoundDropException() throws IOException {
        Files.lines(Paths.get(INCORRECT_FILE_NAME));
    }

    @Test
    public void positiveCheckInputFileFromResourcesDirectory() {
        Path path = Paths.get(correctFile.getAbsolutePath());

        assertNotNull(path);
    }

    @Test
    public void checkFillListDataFromTextFile() throws IOException {
        list = Files.lines(Paths.get(correctFile.getAbsolutePath()))
                .collect(Collectors.toList());

        assertTrue(list.size() > 0);
    }
}