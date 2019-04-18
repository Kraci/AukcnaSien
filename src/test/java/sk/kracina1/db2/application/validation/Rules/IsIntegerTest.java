package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.validation.IsInteger;

import static org.junit.jupiter.api.Assertions.*;

public class IsIntegerTest {

    private IsInteger isInteger;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isInteger = new IsInteger();
    }

    @org.junit.jupiter.api.Test
    void emptyInput() {
        assertFalse(isInteger.passes(""));
    }

    @org.junit.jupiter.api.Test
    void doubleValue() {
        assertFalse(isInteger.passes("3.02"));
    }

    @org.junit.jupiter.api.Test
    void stringInput() {
        assertFalse(isInteger.passes("abc"));
    }

    @org.junit.jupiter.api.Test
    void wrongInteger() {
        assertFalse(isInteger.passes(".5"));
    }

    @org.junit.jupiter.api.Test
    void correctInteger() {
        assertTrue(isInteger.passes("1"));
    }

    @org.junit.jupiter.api.Test
    void negativeInteger() {
        assertTrue(isInteger.passes("-5"));
    }

}
