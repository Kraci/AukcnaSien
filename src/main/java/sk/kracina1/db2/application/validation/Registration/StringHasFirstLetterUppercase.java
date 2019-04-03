package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.validation.Rule;

public class StringHasFirstLetterUppercase implements Rule {

    private static final String errorMessage = "First letter must be uppercase";

    @Override
    public boolean passes(String value) {
        return value.matches("^[A-Z].*");
    }

    @Override
    public String message() {
        return errorMessage;
    }

}
