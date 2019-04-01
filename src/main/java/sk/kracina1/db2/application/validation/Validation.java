package sk.kracina1.db2.application.validation;

import sk.kracina1.db2.application.rdg.BaseFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

public class Validation {

    private Validation() {
    }

    private static final Validation INSTANCE = new Validation();

    public static Validation getInstance() { return INSTANCE; }

    private String check(String value, List<Rule> rules) {
        for (Rule rule: rules) {
            if (!rule.passes(value)) {
                return rule.message();
            }
        }
        return "";
    }

    public String validate(String messageText, List<Rule>  rules) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String value = "";
        boolean validated = false;
        while (!validated){
            System.out.print(messageText);
            try {
                value = br.readLine();
                String error = check(value,rules);
                if ("".equals(error)){
                    validated = true;
                } else {
                    System.out.println();
                    System.out.println(error);
                    System.out.println();
                }
            } catch (IOException e) {
                System.out.println("IO Exception: " + e);
            }
        }
        return value;
    }

}
