package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.User;
import sk.kracina1.db2.application.validation.IsInteger;
import sk.kracina1.db2.application.validation.Registration.*;
import sk.kracina1.db2.application.validation.Required;
import sk.kracina1.db2.application.validation.Validation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;

public class Registration {

    private User user;

    Registration() {
        user = new User();
    }

    public void register() {
        try {
            user.insert();
            UserPrinter.getInstance().print(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUsername() {
        String username = Validation.getInstance().validate("Username: ", Arrays.asList(
                new Required(),
                new UsernameAvailable()
        ));
        user.setUsername(username);
    }

    public void createMail() {
        String mail = Validation.getInstance().validate("Mail: ", Arrays.asList(
                new Required(),
                new MailAvailable(),
                new IsValidMail()
        ));
        user.setMail(mail);
    }

    public void createPassword() {
        String password = Validation.getInstance().validate("Password: ", Arrays.asList(
                new Required(),
                new StringHasMinimumLength(6),
                new PasswordContainsNumber(),
                new PasswordContainsUppercaseLetter()
        ));
        user.setPassword(password);
    }

    public void createFirstName() {
        String firstName = Validation.getInstance().validate("First Name: ", Arrays.asList(
                new StringHasFirstLetterUppercase(),
                new StringWithoutNumber()
        ));
        user.setFirstName(firstName);
    }

    public void createSurname() {
        String surname = Validation.getInstance().validate("Surname: ", Arrays.asList(
                new StringHasFirstLetterUppercase(),
                new StringWithoutNumber()
        ));
        user.setSurname(surname);
    }

    public void createAge() {
        String age = Validation.getInstance().validate("Age: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsAdult()
        ));
        user.setAge(Integer.parseInt(age));
    }

}
