package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.validation.Required;

import static org.junit.jupiter.api.Assertions.*;

public class RequiredTest {

    private Required required;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        required = new Required();
    }

    @org.junit.jupiter.api.Test
    void emptyInput() {
        assertFalse(required.passes(""));
    }

    @org.junit.jupiter.api.Test
    void correctInput() {
        assertTrue(required.passes("abcd"));
    }


}
