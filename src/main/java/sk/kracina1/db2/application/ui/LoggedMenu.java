package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.Category;
import sk.kracina1.db2.application.rdg.CategoryFinder;
import sk.kracina1.db2.application.rdg.Item;
import sk.kracina1.db2.application.validation.IsInCategories;
import sk.kracina1.db2.application.validation.IsInteger;
import sk.kracina1.db2.application.validation.Required;
import sk.kracina1.db2.application.validation.Validation;

import java.sql.SQLException;
import java.util.Arrays;

public class LoggedMenu extends Menu {

    @Override
    public void print() {
        System.out.println("***********************************");
        System.out.println("* 1. Add Item                     *");
        System.out.println("* 2. Log out                      *");
        System.out.println("***********************************");
    }

    @Override
    public void handle(String option) {
        try {
            switch (option) {
                case "1":   addItem(); break;
                case "2":   logout(); break;

                default:    System.out.println("Unknown option"); break;
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void logout() {
        Login.getInstance().logout();
        exit();
    }

    private void addItem() throws SQLException {
        Item item = new Item();

        // TODO auth user
        item.setUser_id(1);

        System.out.println("Add new Item.");
        String name = Validation.getInstance().validate("Name: ", Arrays.asList(
                new Required()
        ));
        item.setName(name);
        String count = Validation.getInstance().validate("Count: ", Arrays.asList(
                new Required(),
                new IsInteger()
        ));
        item.setCount(Integer.parseInt(count));

        String description = Validation.getInstance().validate("Description: ", Arrays.asList(
                new Required()
        ));
        System.out.println();
        item.setDescription(description);

        for (Category category : CategoryFinder.getInstance().findAll()) {
            CategoryPrinter.getInstance().print(category);
        }
        String category = Validation.getInstance().validate("Category id: ", Arrays.asList(
                new Required(),
                new IsInteger(),
                new IsInCategories()
        ));
        item.setCategory_id(CategoryFinder.getInstance().findById(Integer.parseInt(category)).getId());

        item.insert();
        System.out.println();
        ItemPrinter.getInstance().print(item);
    }

}
