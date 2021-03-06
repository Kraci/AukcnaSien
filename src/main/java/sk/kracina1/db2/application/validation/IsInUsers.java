package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.UserFinder;

import java.sql.SQLException;

public class IsInUsers implements Rule {

    private static final String errorMessage = "User does not exist.";

    @Override
    public boolean passes(String value) {
        try {
            return UserFinder.getInstance().findById(Integer.parseInt(value)) != null;
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
