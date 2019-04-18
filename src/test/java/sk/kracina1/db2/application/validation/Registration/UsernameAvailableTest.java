package sk.kracina1.db2.application.validation.Registration;

import org.junit.jupiter.api.BeforeEach;
import sk.kracina1.db2.application.rdg.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UsernameAvailableTest {

    ArrayList<User> users = new ArrayList<>();

    User findUserByUsername(String username) {
        List<User> response = users.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 10; i++) {
            User a = new User();
            String userName = "palo" + String.valueOf(i);
            a.setUsername(userName);
            users.add(a);
        }
    }

    @org.junit.jupiter.api.Test
    void notAvailable() {
        User user = findUserByUsername("palo1");
        assertEquals(user != null, true);
    }


    @org.junit.jupiter.api.Test
    void available() {
        User user = findUserByUsername("Jozo");
        assertEquals(user == null, true);
    }

    @org.junit.jupiter.api.Test
    void addAndCheckNewUser() {
        User newUser = new User();
        newUser.setUsername("Robert");
        users.add(newUser);

        User user = findUserByUsername(newUser.getUsername());
        assertEquals(user != null, true);
    }

}