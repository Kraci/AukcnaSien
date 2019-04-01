package sk.kracina1.db2.application.validation.Registration;

import static org.junit.jupiter.api.Assertions.*;

class IsAdultTest {

    private IsAdult isAdult;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isAdult = new IsAdult();
    }

    @org.junit.jupiter.api.Test
    void negativeAge() {
        assertEquals(isAdult.passes("-2"), false);
    }

    @org.junit.jupiter.api.Test
    void zeroAge() {
        assertEquals(isAdult.passes("0"), false);
    }

    @org.junit.jupiter.api.Test
    void doubleAge() {
        assertEquals(isAdult.passes("17.5"), false);
    }

    @org.junit.jupiter.api.Test
    void youngestAge() {
        assertEquals(isAdult.passes("18"), true);
    }

    @org.junit.jupiter.api.Test
    void normalAge() {
        assertEquals(isAdult.passes("30"), true);
    }

    @org.junit.jupiter.api.Test
    void notANumberAge() {
        assertEquals(isAdult.passes("N0"), false);
    }

}