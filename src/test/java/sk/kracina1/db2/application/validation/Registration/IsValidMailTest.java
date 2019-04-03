package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsValidMailTest {

    private IsValidMail isValidMail;

    @BeforeEach
    void setUp() {
        isValidMail = new IsValidMail();
    }

    @org.junit.jupiter.api.Test
    void empty() {
        assertEquals(isValidMail.passes(""), false);
    }

    @org.junit.jupiter.api.Test
    void plainText() {
        assertEquals(isValidMail.passes("mymail"), false);
    }

    @org.junit.jupiter.api.Test
    void firstPart() {
        assertEquals(isValidMail.passes("mymail@"), false);
    }

    @org.junit.jupiter.api.Test
    void secondPart() {
        assertEquals(isValidMail.passes("@gmail.com"), false);
    }

    @org.junit.jupiter.api.Test
    void withoutEnd() {
        assertEquals(isValidMail.passes("mymail@gmail"), false);
    }

    @org.junit.jupiter.api.Test
    void withoutAt() {
        assertEquals(isValidMail.passes("mymailgmail.com"), false);
    }

    @org.junit.jupiter.api.Test
    void withouSecondPart() {
        assertEquals(isValidMail.passes("mymail@.com"), false);
    }

    @org.junit.jupiter.api.Test
    void correct() {
        assertEquals(isValidMail.passes("mymail@gmail.com"), true);
    }

}