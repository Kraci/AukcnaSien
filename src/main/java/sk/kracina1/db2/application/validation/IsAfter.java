package sk.kracina1.db2.application.validation;

import java.sql.Date;
import java.util.Calendar;

public class IsAfter implements Rule {

    private static final String errorMessage = "Date must be after this date.";

    private int days;

    public IsAfter(int days) {
        this.days = days;
    }

    @Override
    public boolean passes(String value) {
        Date date = Date.valueOf(value);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, this.days);
        return date.after(c.getTime());
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
