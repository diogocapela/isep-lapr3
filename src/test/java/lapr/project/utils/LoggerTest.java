package lapr.project.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class LoggerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void logTest() {
        System.setOut(new PrintStream(outContent));

        String str = "test";
        Logger.log(str);
        Assertions.assertEquals(str, outContent.toString().replaceAll("\r", "").replaceAll("\n", ""));

        System.setOut(originalOut);

    }

}
