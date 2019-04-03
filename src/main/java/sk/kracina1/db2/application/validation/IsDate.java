package sk.kracina1.db2.application.validation;


import java.sql.Date;

public class IsDate implements Rule {

    private static final String errorMessage = "Date is not in format YYYY-MM-DD";

    @Override
    public boolean passes(String value) {
        try {
            Date.valueOf(value);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
