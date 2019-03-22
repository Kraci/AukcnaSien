package sk.kracina1.db2.application.validation;

public class IsDouble implements Rule {
    public IsDouble() {
    }

    @Override
    public boolean passes(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    @Override
    public String message() {
        return "Value must be double.";
    }
}
