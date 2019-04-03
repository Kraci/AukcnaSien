package sk.kracina1.db2.application.validation;

public class IsDouble implements Rule {

    private static final String errorMessage = "Number has to be double.";

    @Override
    public boolean passes(String value) {
        try {
            Double.parseDouble(value);
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
