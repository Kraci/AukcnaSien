package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;
import sk.kracina1.db2.application.rdg.User;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class MailAvailableTest {

    ArrayList<User> users = new ArrayList<>();

    User findUserByMail(String mail) {
        List<User> response = users.stream().filter(user -> user.getMail().equals(mail)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 10; i++) {
            User a = new User();
            String mail = "user" + String.valueOf(i) + "@gmail.com";
            a.setMail(mail);
            users.add(a);
        }
    }

    @org.junit.jupiter.api.Test
    void notAvailable() {
        User user = findUserByMail("user1@gmail.com");
        assertEquals(user != null, true);
    }


    @org.junit.jupiter.api.Test
    void available() {
        User user = findUserByMail("userX@gmail.com");
        assertEquals(user == null, true);
    }

    @org.junit.jupiter.api.Test
    void addAndCheckNewUser() {
        User newUser = new User();
        newUser.setMail("userY@gmail.com");
        users.add(newUser);

        User user = findUserByMail(newUser.getMail());
        assertEquals(user != null, true);
    }

}