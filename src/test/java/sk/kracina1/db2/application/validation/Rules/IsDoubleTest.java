package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.validation.IsDouble;

import static org.junit.jupiter.api.Assertions.*;

class IsDoubleTest {

    private IsDouble isDouble;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isDouble = new IsDouble();
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertFalse(isDouble.passes(""));
    }

    @org.junit.jupiter.api.Test
    void zero() {
        assertTrue(isDouble.passes("0"));
    }

    @org.junit.jupiter.api.Test
    void doubleZero() {
        assertTrue(isDouble.passes("0.0"));
    }

    @org.junit.jupiter.api.Test
    void integerValue() {
        assertTrue(isDouble.passes("3"));
    }

    @org.junit.jupiter.api.Test
    void doubleValue() {
        assertTrue(isDouble.passes("3.02"));
    }

    @org.junit.jupiter.api.Test
    void doubleLongValue() {
        assertTrue(isDouble.passes("3.00000000000002"));
    }

    @org.junit.jupiter.api.Test
    void stringValue() {
        assertFalse(isDouble.passes("3.02a"));
    }

}