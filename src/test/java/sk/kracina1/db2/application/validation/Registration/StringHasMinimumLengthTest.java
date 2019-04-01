package sk.kracina1.db2.application.validation.Registration;

import static org.junit.jupiter.api.Assertions.*;

class StringHasMinimumLengthTest {

    private StringHasMinimumLength stringHasMinimumLength;

    @org.junit.jupiter.api.Test
    void emptyActual6() {
        stringHasMinimumLength = new StringHasMinimumLength(6);
        assertEquals(stringHasMinimumLength.passes(""), false);
    }

    @org.junit.jupiter.api.Test
    void length6actual6() {
        stringHasMinimumLength = new StringHasMinimumLength(6);
        assertEquals(stringHasMinimumLength.passes("123456"), true);
    }

    @org.junit.jupiter.api.Test
    void emptyActual0() {
        stringHasMinimumLength = new StringHasMinimumLength(0);
        assertEquals(stringHasMinimumLength.passes(""), true);
    }

    @org.junit.jupiter.api.Test
    void length1Actual2() {
        stringHasMinimumLength = new StringHasMinimumLength(2);
        assertEquals(stringHasMinimumLength.passes("a"), false);
    }

}