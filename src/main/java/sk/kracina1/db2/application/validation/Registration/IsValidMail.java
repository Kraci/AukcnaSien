package sk.kracina1.db2.application.validation.Registration;

import sk.kracina1.db2.application.validation.Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsValidMail implements Rule {

    private static final String errorMessage = "Not valid e-mail address.";

    @Override
    public boolean passes(String value) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    @Override
    public String message() {
        return errorMessage;
    }

}
