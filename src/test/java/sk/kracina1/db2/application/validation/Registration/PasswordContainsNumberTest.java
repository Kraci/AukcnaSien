package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PasswordContainsNumberTest {

    private PasswordContainsNumber passwordContainsNumber;

    @BeforeEach
    void setUp() {
        passwordContainsNumber = new PasswordContainsNumber();
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertEquals(passwordContainsNumber.passes(""), false);
    }

    @org.junit.jupiter.api.Test
    void withoutNumber() {
        assertEquals(passwordContainsNumber.passes("password"), false);
    }

    @org.junit.jupiter.api.Test
    void numberOnEnd() {
        assertEquals(passwordContainsNumber.passes("password23"), true);
    }

    @org.junit.jupiter.api.Test
    void numberOnStart() {
        assertEquals(passwordContainsNumber.passes("3password"), true);
    }

    @org.junit.jupiter.api.Test
    void justNumber() {
        assertEquals(passwordContainsNumber.passes("11"), true);
    }

    @org.junit.jupiter.api.Test
    void numberInCenter() {
        assertEquals(passwordContainsNumber.passes("some1thing"), true);
    }

    @org.junit.jupiter.api.Test
    void multipleNumbers() {
        assertEquals(passwordContainsNumber.passes("pa11ssword3"), true);
    }

}