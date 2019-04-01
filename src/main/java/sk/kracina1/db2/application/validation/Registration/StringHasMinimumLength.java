package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.validation.Rule;

public class StringHasMinimumLength implements Rule {

    private static final String errorMessage = "Password is not long enough, minimum number of characters is ";
    private int minimumLength;

    public StringHasMinimumLength(int minimumLength) {
        this.minimumLength = minimumLength;
    }

    private int getMinimumLength() {
        return minimumLength;
    }

    @Override
    public boolean passes(String value) {
        return value.length() >= getMinimumLength();
    }

    @Override
    public String message() {
        return errorMessage + getMinimumLength() + ".";
    }

}
