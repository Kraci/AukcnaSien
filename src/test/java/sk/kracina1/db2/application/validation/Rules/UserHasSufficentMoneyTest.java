package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.validation.UserHasSufficentMoney;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class UserHasSufficentMoneyTest {

    private static final double userMoney = 100.0;
    private UserHasSufficentMoneyTestClass userHasSufficientMoney;

    private class UserHasSufficentMoneyTestClass extends UserHasSufficentMoney {
        public UserHasSufficentMoneyTestClass(int userId) {
            super(userId);
        }

        protected double money() throws SQLException {
            return userMoney;
        }
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        userHasSufficientMoney = new UserHasSufficentMoneyTestClass(0);
    }

    @org.junit.jupiter.api.Test
    void userHasSame() {
        assertTrue(userHasSufficientMoney.passes(Integer.toString((int)userMoney)));
    }

    @org.junit.jupiter.api.Test
    void userHasMore() {
        assertTrue(userHasSufficientMoney.passes(Integer.toString((int)(userMoney*.9))));
    }

    @org.junit.jupiter.api.Test
    void userHasLess() {
        assertFalse(userHasSufficientMoney.passes(Integer.toString((int)(userMoney*1.1))));
    }

}
