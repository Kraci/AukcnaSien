package sk.kracina1.db2.application.validation;

public interface Rule {

    /**
     * Determine if the validation rule passes.
     * @param value
     * @return
     */
    public boolean passes(String value);

    /**
     * Get the validation error message.
     * @return
     */
    public String message();

}
