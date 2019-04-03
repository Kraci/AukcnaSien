package sk.kracina1.db2.application.validation;

public class IsInteger implements Rule {

    private static final String errorMessage = "Value must be int.";

    @Override
    public boolean passes(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e){
            //System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
