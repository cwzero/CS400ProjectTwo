// --== CS400 File Header Information ==--
// Name: Eric Gjerde
// Email: egjerde@wisc.edu
// Team: DE blue
// Role: Frontend Developer
// TA: Dan Kiel
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
Tests for the Frontend
 */
public class FrontEndDeveloperTests {
    private Frontend frontend;
    private BackendInterface backend;
    private PrintStream standardOut;
    private InputStream standardIn;
    private ByteArrayOutputStream outputStreamCaptor;

    /*
    Before each test, remember the System.in, System.out, and set up the backend/frontend and a capture stream for program output
     */
    @BeforeEach
    public void setUp() {
        standardOut = System.out;
        standardIn = System.in;

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        backend = new BackendDummy();
        frontend = new Frontend();
    }

    /*
    After each test, restore System.in, System.out
     */
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    /*
    Helper method/shared boilerplate code
     */
    private void runTest(String input, Predicate<String> outputTest) {
        try {
            InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStreamSimulator);

            frontend.init();
            frontend.run(backend);

            assertTrue(outputTest.test(outputStreamCaptor.toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    Test the a = get all scores command
     */
    @Test
    public void testGetAllScores() {
        runTest("a\nx\n", output -> output.contains("All Scores:") &&
                output.contains("siglemic") && output.contains("cheese") && output.contains("toastrider"));
    }

    /*
    Test the h = get high scores (under 7000) command
     */
    @Test
    public void testGetHighScores() {
        runTest("h\n7000\nx\n", output -> output.contains("High Scores:") && output.contains("siglemic"));
    }

    /*
    Test the f = get fastest score command
     */
    @Test
    public void testGetFastest() {
        runTest("f\nx\n", output -> output.contains("Fastest Score:") && output.contains("siglemic"));
    }

    /*
    Test the s = get slowest score command
     */
    @Test
    public void testGetSlowest() {
        runTest("s\nx\n", output -> output.contains("Slowest Score:") && output.contains("toastrider"));
    }

    /*
    Test the u = search by user command
     */
    @Test
    public void testGetUser() {
        runTest("u\ncheese\nx\n", output -> output.contains("Searching by User (cheese):") && output.contains("2014"));
    }
}

