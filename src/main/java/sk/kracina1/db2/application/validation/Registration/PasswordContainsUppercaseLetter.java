package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.validation.Rule;

public class PasswordContainsUppercaseLetter implements Rule {

    private static final String errorMessage = "Your password must contain at least one uppercase letter.";

    @Override
    public boolean passes(String value) {
        return value.matches(".*[A-Z].*");
    }

    @Override
    public String message() {
        return errorMessage;
    }

}
