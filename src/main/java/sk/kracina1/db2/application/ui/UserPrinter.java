package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.User;

public class UserPrinter {

    private static final UserPrinter INSTANCE = new UserPrinter();

    public static UserPrinter getInstance() { return INSTANCE; }

    private UserPrinter() {}

    public void print(User user) {
        if (user == null) {
            throw new NullPointerException("User cannot be null");
        }

        System.out.print("id:         ");
        System.out.println(user.getId());
        System.out.print("username:   ");
        System.out.println(user.getUsername());
        System.out.print("mail:       ");
        System.out.println(user.getMail());
        System.out.print("password:   ");
        System.out.println(user.getPassword());
        System.out.print("first name: ");
        System.out.println(user.getFirstName());
        System.out.print("surname:    ");
        System.out.println(user.getSurname());
        System.out.print("age:        ");
        System.out.println(user.getAge());
        System.out.print("money:      ");
        System.out.println(user.getMoney());
        System.out.println();
        System.out.println();
    }

}
