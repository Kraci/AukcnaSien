package sk.kracina1.db2.application.validation;

public class Required implements Rule {

    private static final String errorMessage = "Value is required.";

    @Override
    public boolean passes(String value) {
        return !value.trim().equals("");
    }

    @Override
    public String message() {
        return errorMessage;
    }

}
