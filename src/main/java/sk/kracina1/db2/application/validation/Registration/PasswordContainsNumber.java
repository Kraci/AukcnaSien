package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.validation.Rule;

public class PasswordContainsNumber implements Rule {

    private static final String errorMessage = "Your password must contain at least one number.";

    @Override
    public boolean passes(String value) {
        return value.matches(".*\\d.*");
    }

    @Override
    public String message() {
        return errorMessage;
    }

}