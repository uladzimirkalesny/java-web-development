package by.epam.training.changer.entity;

import by.epam.training.changer.interfaces.Reader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.File;
import java.util.Objects;

public class ChangerTest {
    private static final String CORRECT_FILE_NAME = "input";
    private File correctFile;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        correctFile = new File(Objects.requireNonNull(classLoader.getResource(CORRECT_FILE_NAME)).getFile());
    }

    @After
    public void tearDown() {
        correctFile = null;
    }

    @Test
    public void validMoneyChangeDataOutputOutputConsoleGreenTestWhenAllGood() {
        Changer changer = new Changer(correctFile.getAbsolutePath());
        changer.performChange();

        String dataExpected = "6 1 2 3\r\n" +
                "1 1 1 1 1 1 " + "\r\n" +
                "1 1 1 1 2 " + "\r\n" +
                "1 1 1 3 " + "\r\n" +
                "1 1 2 2 " + "\r\n" +
                "1 2 3 " + "\r\n" +
                "2 2 2 " + "\r\n" +
                "3 3 " + "\r\n" +
                "\r\n";

        Assert.assertEquals(dataExpected, systemOutRule.getLog());
    }

}