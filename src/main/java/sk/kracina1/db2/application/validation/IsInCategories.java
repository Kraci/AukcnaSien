package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.CategoryFinder;

import java.sql.SQLException;

public class IsInCategories implements Rule {

    private static final String errorMessage = "Category does not exist.";

    @Override
    public boolean passes(String value) {
        try {
            return CategoryFinder.getInstance().findById(Integer.parseInt(value)) != null;
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
