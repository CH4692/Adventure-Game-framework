package ias;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractTest {

    protected final static String ERROR_PREFIX = "Sorry,";
    protected final static String YOU_GO = "You go";

    protected final static String RANDOM_OBJECT = "Object";
    protected final static String RANDOM_OBJECT_DESCRIPTION = "A random object";
    protected final static String RANDOM_OBJECT_STRING = RANDOM_OBJECT + " - " + RANDOM_OBJECT_DESCRIPTION;

    protected static void assertError(String result) {
        assertStartsWith(ERROR_PREFIX, result);
    }

    protected static void assertMoveSuccess(String result) {
        assertStartsWith(YOU_GO, result);
    }

    protected static void assertStartsWith(String prefix, String result) {
        if (!result.startsWith(prefix)) {
            System.out.println("Expected <" + prefix + "[...]> but was: <" + result + ">");
        }
        assertTrue(result.startsWith(prefix));
    }

    protected static void assertContains(String reference, String[] result) {
        assertContains("", reference, result);
    }

    protected static void assertContainsNot(String reference, String[] result) {
        assertContainsNot("", reference, result);
    }

    protected static void assertContainsCount(String reference, String[] result, int count) {
        int c = 0;
        for (String string : result) {
            if (reference.equals(string)) {
                c++;
            }
        }
        assertEquals(count, c);
    }

    protected static void assertContains(String message, String reference, String[] result) {
        boolean found = false;
        for (String string : result) {
            if (reference.equals(string)) {
                found = true;
            }
        }

        if (message.isEmpty()) {
            if (!found) {
                System.out.println("Expected <" + reference + ">, but was: <" + Arrays.toString(result) + ">");
            }
            assertTrue(found);
        } else {
            if (!found) {
                System.out.println(message + ": Expected <" + reference
                        + ">, but was: <" + Arrays.toString(result) + ">");
            }
            assertTrue(found);
        }
    }

    protected static void assertContainsNot(String message, String reference, String[] result) {
        boolean found = false;
        for (String string : result) {
            if (reference.equals(string)) {
                found = true;
            }
        }

        if (message.isEmpty()) {
            if (found) {
                System.out.println("<" + Arrays.toString(result) + "> contains not expected <" + reference + ">");
            }
            assertFalse(found);
        } else {
            if (found) {
                System.out.println(message + ": <" + Arrays.toString(result)
                        + "> contains not expected <" + reference + ">");
            }
            assertFalse(found);
        }
    }
}
