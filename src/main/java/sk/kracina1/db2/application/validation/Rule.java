package sk.kracina1.db2.application.validation;

import java.sql.SQLException;

public interface Rule {

    /**
     * Determine if the validation rule passes.
     * @param value
     * @return
     */
    public boolean passes(String value) throws SQLException;

    /**
     * Get the validation error message.
     * @return
     */
    public String message();
}
