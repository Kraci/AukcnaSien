package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.rdg.UserFinder;
import sk.kracina1.db2.application.validation.Rule;

import java.sql.SQLException;

public class UsernameAvailable implements Rule {

    private static final String errorMessage = "Username is already taken.";

    @Override
    public boolean passes(String value) {
        try {
            return UserFinder.getInstance().findUserByName(value) == null;
        } catch (SQLException e) {
            //System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String message() {
        return errorMessage;
    }

}
