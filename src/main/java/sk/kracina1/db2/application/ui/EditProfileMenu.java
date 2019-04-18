package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.User;
import sk.kracina1.db2.application.validation.*;
import sk.kracina1.db2.application.validation.Registration.*;

import java.sql.SQLException;
import java.util.Arrays;

public class EditProfileMenu extends Menu {

    private User editedUser;

    @Override
    public void print() {
        System.out.println("***********************************");
        System.out.println("* 1. Edit username                *");
        System.out.println("* 2. Edit mail                    *");
        System.out.println("* 3. Edit password                *");
        System.out.println("* 4. Edit first name              *");
        System.out.println("* 5. Edit surname                 *");
        System.out.println("* 6. Edit age                     *");
        System.out.println("* 7. Edit money                   *");
        System.out.println("* 8. Done editing                 *");
        System.out.println("***********************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1":   editUsername(); break;
                case "2":   editMail(); break;
                case "3":   editPassword(); break;
                case "4":   editFirstName(); break;
                case "5":   editSurname(); break;
                case "6":   editAge(); break;
                case "7":   editMoney(); break;
                case "8":   doneEditing(); break;

                default:    System.out.println("Unknown option"); break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setEditedUser(User user) {
        editedUser = user;
    }

    private void editUsername() throws SQLException {
        String username = Validation.getInstance().validate("Username: ", Arrays.asList(
                new Required(),
                new UsernameAvailable()
        ));
        editedUser.setUsername(username);
        editedUser.update();
    }

    private void editMail() throws SQLException {
        String mail = Validation.getInstance().validate("Mail: ", Arrays.asList(
                new Required(),
                new MailAvailable(),
                new IsValidMail()
        ));
        editedUser.setMail(mail);
        editedUser.update();
    }

    private void editPassword() throws SQLException {
        String password = Validation.getInstance().validate("Password: ", Arrays.asList(
                new Required(),
                new StringHasMinimumLength(6),
                new PasswordContainsNumber(),
                new PasswordContainsUppercaseLetter()
        ));
        editedUser.setPassword(password);
        editedUser.update();
    }

    private void editFirstName() throws SQLException {
        String firstName = Validation.getInstance().validate("First Name: ", Arrays.asList(
                new StringHasFirstLetterUppercase(),
                new StringWithoutNumber()
        ));
        editedUser.setPassword(firstName);
        editedUser.update();
    }

    private void editSurname() throws SQLException {
        String surname = Validation.getInstance().validate("Surname: ", Arrays.asList(
                new StringHasFirstLetterUppercase(),
                new StringWithoutNumber()
        ));
        editedUser.setSurname(surname);
        editedUser.update();
    }

    private void editAge() throws SQLException {
        String age = Validation.getInstance().validate("Age: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsAdult()
        ));
        editedUser.setAge(Integer.parseInt(age));
        editedUser.update();
    }

    private void editMoney() throws SQLException {
        String money = Validation.getInstance().validate("Money: ", Arrays.asList(
                new IsDouble(),
                new IsPositiveDouble()
        ));
        editedUser.setMoney(Double.parseDouble(money));
        editedUser.update();
    }

    private void doneEditing() {
        exit();
    }

}
