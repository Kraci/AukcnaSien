package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StringWithoutNumberTest {

    private StringWithoutNumber stringWithoutNumber;

    @BeforeEach
    void setUp() {
        stringWithoutNumber = new StringWithoutNumber();
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertEquals(stringWithoutNumber.passes(""), true);
    }

    @org.junit.jupiter.api.Test
    void withoutUppercase() {
        assertEquals(stringWithoutNumber.passes("password"), true);
    }

    @org.junit.jupiter.api.Test
    void withUppercase() {
        assertEquals(stringWithoutNumber.passes("Password"), true);
    }

    @org.junit.jupiter.api.Test
    void allUppercase() {
        assertEquals(stringWithoutNumber.passes("PASSWORD"), true);
    }

    @org.junit.jupiter.api.Test
    void numberAtStart() {
        assertEquals(stringWithoutNumber.passes("1password"), false);
    }

    @org.junit.jupiter.api.Test
    void numberAtEnd() {
        assertEquals(stringWithoutNumber.passes("password2"), false);
    }

    @org.junit.jupiter.api.Test
    void numberAtCenter() {
        assertEquals(stringWithoutNumber.passes("pass2word"), false);
    }

    @org.junit.jupiter.api.Test
    void multipleNumbers() {
        assertEquals(stringWithoutNumber.passes("pa3ss2wo5rd"), false);
    }

}