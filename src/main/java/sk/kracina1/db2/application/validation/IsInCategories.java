package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.CategoryFinder;

import java.sql.SQLException;

public class IsInCategories implements Rule {
    public IsInCategories() {
    }

    @Override
    public boolean passes(String value) throws SQLException {
        return CategoryFinder.getInstance().findById(Integer.parseInt(value)) != null;
    }

    @Override
    public String message() {
        return "Category does not exist.";
    }
}
