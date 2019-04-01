package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PasswordContainsUppercaseLetterTest {

    private PasswordContainsUppercaseLetter passwordContainsUppercaseLetter;

    @BeforeEach
    void setUp() {
        passwordContainsUppercaseLetter = new PasswordContainsUppercaseLetter();
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertEquals(passwordContainsUppercaseLetter.passes(""), false);
    }

    @org.junit.jupiter.api.Test
    void withoutUppercase() {
        assertEquals(passwordContainsUppercaseLetter.passes("password"), false);
    }

    @org.junit.jupiter.api.Test
    void number() {
        assertEquals(passwordContainsUppercaseLetter.passes("12345"), false);
    }

    @org.junit.jupiter.api.Test
    void uppercaseAtStart() {
        assertEquals(passwordContainsUppercaseLetter.passes("Yes"), true);
    }

    @org.junit.jupiter.api.Test
    void uppercaseAtEnd() {
        assertEquals(passwordContainsUppercaseLetter.passes("yeS"), true);
    }

    @org.junit.jupiter.api.Test
    void justUppercase() {
        assertEquals(passwordContainsUppercaseLetter.passes("PASSWORD"), true);
    }

    @org.junit.jupiter.api.Test
    void onlyOneUppercaseLetter() {
        assertEquals(passwordContainsUppercaseLetter.passes("P"), true);
    }

}