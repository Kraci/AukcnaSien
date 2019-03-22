package sk.kracina1.db2.application.validation;

public class Required implements Rule {
    public Required() {
    }

    @Override
    public boolean passes(String value) {
        return !value.trim().equals("");
    }

    @Override
    public String message() {
        return "Value is required.";
    }
}
