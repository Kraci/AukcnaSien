package sk.kracina1.db2.application.validation.AddProduct;

import sk.kracina1.db2.application.rdg.Category;
import sk.kracina1.db2.application.rdg.Item;
import sk.kracina1.db2.application.rdg.User;
import sk.kracina1.db2.application.validation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemTest {

    ArrayList<User> users = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    User findUserByMail(String mail) {
        List<User> response = users.stream().filter(user -> user.getMail().equals(mail)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    Category findCategoryById(int id) {
        List<Category> response = categories.stream().filter(category -> category.getId().equals(id)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    Item findItemById(int id) {
        List<Item> response = items.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
        if (response.isEmpty()) {
            return null;
        }
        return response.get(0);
    }

    private Required required;
    private IsInteger isInteger;
    private Validation validation;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        required = new Required();
        isInteger = new IsInteger();
        validation = Validation.getInstance();

        for (int i = 1; i <= 10; i++) {
            User a = new User();
            String mail = "user" + String.valueOf(i) + "@gmail.com";
            a.setId(i);
            a.setMail(mail);
            users.add(a);
        }

        for (int i = 1; i <= 10; i++) {
            Category a = new Category();
            a.setId(i);
            String name = "category " + String.valueOf(i);
            a.setName(name);
            categories.add(a);
        }
    }

    @org.junit.jupiter.api.Test
    void emptyNameInput() {
        assertEquals(validation.check("", Arrays.asList(required)), required.message());
    }

    @org.junit.jupiter.api.Test
    void correctNameInput() {
        assertEquals(validation.check("Lorem Ipsum :-)", Arrays.asList(required)), "");
    }

    @org.junit.jupiter.api.Test
    void emptyDescription() {
        assertEquals(validation.check("", Arrays.asList(required)), required.message());
    }

    @org.junit.jupiter.api.Test
    void correctDescription() {
        assertEquals(validation.check("I am cool.", Arrays.asList(required)), "");
    }

    @org.junit.jupiter.api.Test
    void emptyCategoryId() {
        assertEquals(validation.check("", Arrays.asList(required, isInteger)), required.message());
    }

    @org.junit.jupiter.api.Test
    void wrong1CategoryId() {
        assertEquals(validation.check("hi", Arrays.asList(required, isInteger)), isInteger.message());
    }

    @org.junit.jupiter.api.Test
    void wrong2CategoryId() {
        Category category = findCategoryById(20);
        assertNull(category);
    }

    @org.junit.jupiter.api.Test
    void correctInput() throws SQLException {
        Category category = findCategoryById(2);
        assertNotNull(category);
    }

    @org.junit.jupiter.api.Test
    void successInsert() throws SQLException {
        Item item = new Item();
        item.setName("telkac");
        item.setDescription("stary");
        item.setCount(1);
        item.setId(items.size()+1);

        User loggedUser = findUserByMail("user1@gmail.com");
        item.setUser_id(loggedUser.getId());

        Category category = findCategoryById(1);
        item.setCategory_id(category.getId());

        items.add(item);

        Item searchItem = findItemById(item.getId());

        assertNotNull(searchItem);
    }

    @org.junit.jupiter.api.Test
    void successManyInsert() throws SQLException {
        User loggedUser = findUserByMail("user1@gmail.com");

        int count = items.size();

        for (int i = 0; i < 100; i++) {
            Item item = new Item();
            item.setName("item "+String.valueOf(i+1));
            item.setDescription("stary");
            item.setCount(1);
            item.setId(items.size()+1);

            Category category = findCategoryById(i%10 + 1);
            item.setCategory_id(category.getId());

            items.add(item);

            Item searchItem = findItemById(item.getId());

            assertNotNull(searchItem);
        }

        assertEquals(items.size(), count+100);
    }
}
