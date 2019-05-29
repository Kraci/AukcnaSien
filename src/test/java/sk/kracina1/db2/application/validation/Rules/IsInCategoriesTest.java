package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.DbContext;
import sk.kracina1.db2.application.rdg.Category;
import sk.kracina1.db2.application.rdg.CategoryFinder;
import sk.kracina1.db2.application.rdg.Item;
import sk.kracina1.db2.application.ui.MainMenu;
import sk.kracina1.db2.application.validation.IsInCategories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class IsInCategoriesTest {

    private IsInCategories isInCategories;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        isInCategories = new IsInCategories();
    }

//    @org.junit.jupiter.api.Test
//    void createCategory() {
//        String url = "jdbc:postgresql://localhost:5432/postgres";
//        String username = "postgres";
//        String password = "";
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            DbContext.setConnection(connection);
//
//            Category category = new Category();
//
//            category.setName("Ahoj");
//
//            category.insert();
//
//            assertNotNull(CategoryFinder.getInstance().findRoomById(category.getId()));
//
//        } catch (SQLException e) {
//        } finally {
//            try {
//                DbContext.getConnection().rollback();
//            } catch (SQLException e) {
//            }
//            DbContext.clear();
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    void isInCategory() {
//        String url = "jdbc:postgresql://localhost:5432/postgres";
//        String username = "postgres";
//        String password = "";
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            DbContext.setConnection(connection);
//
//            Category category = new Category();
//
//            category.setName("Ahoj");
//
//            category.insert();
//
//            assertTrue(isInCategories.passes("" + category.getId()));
//
//        } catch (SQLException e) {
//        } finally {
//            try {
//                DbContext.getConnection().rollback();
//            } catch (SQLException e) {
//            }
//            DbContext.clear();
//        }
//    }
}
