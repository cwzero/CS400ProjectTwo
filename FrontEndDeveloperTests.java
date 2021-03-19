import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Predicate;

public class FrontEndDeveloperTests {
    private void runTest(Frontend frontend, String input, Predicate<String> outputTest) {
        PrintStream standardOut = System.out;
        InputStream standardIn = System.in;

        try {
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            BackendInterface backend = new BackendDummy(); // TODO: replace with real backend?
            frontend.run(backend);

            assertTrue(outputTest.test(outputStreamCaptor.toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.setOut(standardOut);
            System.setIn(standardIn);
        }

    }

    private void runTest(String input, Predicate<String> outputTest) {
        runTest(new Frontend(), input, outputTest);
    }

    @Test
    public void testGetAllScores() {
        runTest("a\nx\n", output -> output.contains("All Scores:") &&
                output.contains("siglemic") && output.contains("cheese") && output.contains("toastrider"));
    }

    @Test
    public void testGetHighScores() {
        runTest("h\nx\n", output -> output.contains("High Scores:") && output.contains("siglemic"));
    }

    @Test
    public void testGetFastest() {
        runTest("f\nx\n", output -> output.contains("Fastest score:") && output.contains("siglemic"));
    }

    @Test
    public void testGetSlowest() {
        runTest("s\nx\n", output -> output.contains("Slowest score:") && output.contains("toastrider"));
    }

    @Test
    public void testGetPlayer() {
        runTest("u\ncheese\nx\n", output -> output.contains("Searching by user (cheese): ") && output.contains("2014"));
    }
}

