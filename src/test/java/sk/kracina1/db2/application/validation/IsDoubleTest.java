package sk.kracina1.db2.application.validation;

import static org.junit.jupiter.api.Assertions.*;

class IsDoubleTest {

    private IsDouble isDouble;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isDouble = new IsDouble();
    }

    @org.junit.jupiter.api.Test
    void passesInteger() {
        assertEquals(isDouble.passes("3"), true);
    }

    @org.junit.jupiter.api.Test
    void passesDouble() {
        assertEquals(isDouble.passes("3.02"), true);
    }

    @org.junit.jupiter.api.Test
    void passesString() {
        assertEquals(isDouble.passes("3.02a"), false);
    }

}
