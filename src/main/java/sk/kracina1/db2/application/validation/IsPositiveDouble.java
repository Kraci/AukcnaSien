package sk.kracina1.db2.application.validation;

public class IsPositiveDouble implements Rule {

    private static final String errorMessage = "Number has to be positive.";

    @Override
    public boolean passes(String value) {
        try {
            Double doubleValue = Double.parseDouble(value);
            return doubleValue >= 0;
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
