package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StringHasFirstLetterUppercaseTest {

    private StringHasFirstLetterUppercase stringHasFirstLetterUppercase;

    @BeforeEach
    void setUp() {
        stringHasFirstLetterUppercase = new StringHasFirstLetterUppercase();
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertEquals(stringHasFirstLetterUppercase.passes(""), false);
    }

    @org.junit.jupiter.api.Test
    void withoutUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("string"), false);
    }

    @org.junit.jupiter.api.Test
    void onlyUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("STRING"), true);
    }

    @org.junit.jupiter.api.Test
    void firstUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("String"), true);
    }

    @org.junit.jupiter.api.Test
    void lastUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("strinG"), false);
    }

    @org.junit.jupiter.api.Test
    void containsNumberWithoutUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("st9ring"), false);
    }

    @org.junit.jupiter.api.Test
    void containsNumberFirstUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("S9tring"), true);
    }

    @org.junit.jupiter.api.Test
    void containsCharacterFirstUppercase() {
        assertEquals(stringHasFirstLetterUppercase.passes("S!!tring"), true);
    }

}