package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.UserFinder;

import java.sql.SQLException;

public class IsInUsers implements Rule {
    @Override
    public boolean passes(String value) throws SQLException {
        return UserFinder.getInstance().findById(Integer.parseInt(value)) != null;
    }

    @Override
    public String message() {
        return "User does not exist.";
    }
}
