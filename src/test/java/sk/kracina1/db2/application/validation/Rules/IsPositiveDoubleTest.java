package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.validation.IsPositiveDouble;

import static org.junit.jupiter.api.Assertions.*;

class IsPositiveDoubleTest {

    private IsPositiveDouble isPositiveDouble;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isPositiveDouble = new IsPositiveDouble();
    }

    @org.junit.jupiter.api.Test
    void zero() {
        assertTrue(isPositiveDouble.passes("0"));
    }

    @org.junit.jupiter.api.Test
    void negative() {
        assertFalse(isPositiveDouble.passes("-1"));
    }

    @org.junit.jupiter.api.Test
    void negativeDouble() {
        assertFalse(isPositiveDouble.passes("-0.99"));
    }

    @org.junit.jupiter.api.Test
    void positive() {
        assertTrue(isPositiveDouble.passes("1"));
    }

    @org.junit.jupiter.api.Test
    void positiveDouble() {
        assertTrue(isPositiveDouble.passes("0.1"));
    }

    @org.junit.jupiter.api.Test
    void stringValue() {
        assertFalse(isPositiveDouble.passes("0.1a"));
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertFalse(isPositiveDouble.passes(""));
    }

}