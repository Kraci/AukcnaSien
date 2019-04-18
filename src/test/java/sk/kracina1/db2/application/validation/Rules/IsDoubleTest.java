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
    void passesInteger() {
        assertTrue(isDouble.passes("3"));
    }

    @org.junit.jupiter.api.Test
    void passesDouble() {
        assertTrue(isDouble.passes("3.02"));
    }

    @org.junit.jupiter.api.Test
    void passesString() {
        assertFalse(isDouble.passes("3.02a"));
    }

}
