package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.validation.Rule;

public class IsAdult implements Rule {

    private static final String errorMessage = "You have to be at least 18 years old.";

    @Override
    public boolean passes(String value) {
        try {
            return Integer.parseInt(value) >= 18;
        } catch (NumberFormatException e) {
            //System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String message() {
        return errorMessage;
    }

}
