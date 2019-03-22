package sk.kracina1.db2.application.validation;

import java.io.IOException;

public class IsInteger implements Rule {


    @Override
    public boolean passes(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    @Override
    public String message() {
        return "Value must be int.";
    }
}
