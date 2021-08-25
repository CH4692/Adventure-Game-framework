package ias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import student.Factory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TerminalTest extends AbstractTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private static Terminal terminal;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testGoSouth() {
        provideInput("go S");
        terminal = Factory.getTerminal();
        String[] input = terminal.readInput();
        assertEquals("go", input[0]);
        assertEquals("S", input[1]);
    }

    @Test
    public void testTake() {
        provideInput("take book");
        terminal = Factory.getTerminal();
        String[] input = terminal.readInput();
        assertEquals("take", input[0]);
        assertEquals("book", input[1]);
    }

    @Test
    public void testOutput() {
        terminal = Factory.getTerminal();
        terminal.promptInput("Play adventure games");
        assertStartsWith("Play adventure games", testOut.toString());
    }
}
