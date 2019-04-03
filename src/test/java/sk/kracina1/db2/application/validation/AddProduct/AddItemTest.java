package sk.kracina1.db2.application.validation.AddProduct;

import sk.kracina1.db2.application.rdg.Category;
import sk.kracina1.db2.application.rdg.Item;
import sk.kracina1.db2.application.rdg.User;
import sk.kracina1.db2.application.validation.*;

import java.sql.SQLException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemTest {
    private IsInItems isInItems;
    private Required required;
    private IsInteger isInteger;
    private IsInCategories isInCategories;
    private Validation validation;
    private IsInAuctionItem isInAuctionItem;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isInItems = new IsInItems();
        required = new Required();
        isInteger = new IsInteger();
        isInCategories = new IsInCategories();
        validation = Validation.getInstance();
        isInAuctionItem = new IsInAuctionItem();
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
        assertEquals(validation.check("", Arrays.asList(required, isInteger, isInCategories)), required.message());
    }

    @org.junit.jupiter.api.Test
    void wrong1CategoryId() {
        assertEquals(validation.check("hi", Arrays.asList(required, isInteger, isInCategories)), isInteger.message());
    }
    // TODO database connection required
//    @org.junit.jupiter.api.Test
//    void wrong2CategoryId() {
//        assertEquals(validation.check("90000", Arrays.asList(required, isInteger, isInCategories)), isInCategories.message());
//    }

//    @org.junit.jupiter.api.Test
//    void correctInput() throws SQLException {
//        Category category = new Category();
//        category.setName("category 1");
//        category.insert();
//        assertEquals(validation.check(""+category.getId(), Arrays.asList(required, isInteger, isInCategories)), "");
//        // TODO rollback
//    }

    @org.junit.jupiter.api.Test
    void successInsert() throws SQLException {
        Item item = new Item();

        User user = new User();
        user.setAge(20);
        user.setFirstName("Matej");
        user.setSurname("Rychtarik");
        user.setMail("test@test.sk");
        user.setUsername("matej123456");
        user.setPassword("ahoj1234AHOJ");
        user.insert();

        item.setUser_id(user.getId());
        item.setDescription("This is top item.");
        item.setName("The best item");

        Category category = new Category();
        category.setName("category 1");
        category.insert();

        item.setCategory_id(category.getId());

        item.insert();

        assertEquals(validation.check(""+item.getId(), Arrays.asList(isInItems)), "");
    }

}
