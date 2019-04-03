package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.ItemFinder;

import java.sql.SQLException;

public class IsInItems implements Rule {

    private static final String errorMessage = "Item does not exist.";

    @Override
    public boolean passes(String value) {
        try {
            return ItemFinder.getInstance().findById(Integer.parseInt(value)) != null;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
