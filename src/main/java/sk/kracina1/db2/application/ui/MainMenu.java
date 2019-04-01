package sk.kracina1.db2.application.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;

import sk.kracina1.db2.application.rdg.*;
import sk.kracina1.db2.application.validation.*;

public class MainMenu extends Menu {

    @Override
    public void print() {
        System.out.println("***********************************");
        System.out.println("* 1. Login                        *");
        System.out.println("* 2. Register                     *");
        System.out.println("* 3. Exit                         *");
        System.out.println("***********************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1":   login(); break;
                case "2":   register(); break;
                case "3":   exit(); break;

                default:    System.out.println("Unknown option"); break;
            }
        } catch(SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void login() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Username or mail: ");
        String usernameOrMail = br.readLine();

        System.out.print("Password ");
        String password = br.readLine();

        boolean logged = Login.getInstance().login(usernameOrMail, password);
        if (logged) {
            System.out.println("\nWelcome " + usernameOrMail);
            LoggedMenu loggedMenu = new LoggedMenu();
            loggedMenu.run();
        } else {
            System.out.println("Username or password doesn't exist");
        }
    }

    private void register() {
        Registration registration = new Registration();
        registration.createUsername();
        registration.createMail();
        registration.createPassword();
        registration.createFirstName();
        registration.createSurname();
        registration.createAge();
        registration.register();
    }

//    private void listAllUsers() throws SQLException {
//        for (User user: UserFinder.getInstance().findAll()) {
//            UserPrinter.getInstance().print(user);
//        }
//    }
//
//    private void showAnUser() throws IOException, SQLException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.print("Enter an user's id: ");
//        int userId = Integer.parseInt(br.readLine());
//
//        User user = UserFinder.getInstance().findById(userId);
//
//        if (user == null) {
//            System.out.println("No such user exists");
//        } else {
//            UserPrinter.getInstance().print(user);
//        }
//    }
//
//    private void deleteAnUser() throws SQLException, IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.print("Enter an user's id: ");
//        int userId = Integer.parseInt(br.readLine());
//
//        User user = UserFinder.getInstance().findById(userId);
//
//        if (user == null) {
//            System.out.println("No such user exists");
//        } else {
//            user.delete();
//            System.out.println("The user has been successfully deleted");
//        }
//    }

}