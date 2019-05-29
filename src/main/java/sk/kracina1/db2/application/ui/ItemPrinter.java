package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.CategoryFinder;
import sk.kracina1.db2.application.rdg.Item;

import java.sql.SQLException;

public class ItemPrinter {

    private static final ItemPrinter INSTANCE = new ItemPrinter();

    public static ItemPrinter getInstance() { return INSTANCE; }

    private ItemPrinter() {}

    public void print(Item item) throws SQLException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }



        System.out.print("id:          ");
        System.out.println(item.getId());
        System.out.print("name:        ");
        System.out.println(item.getName());
        System.out.print("count:       ");
        System.out.println(item.getCount());
        System.out.print("description: ");
        System.out.println(item.getDescription());
        System.out.print("category:    ");
        System.out.println(CategoryFinder.getInstance().findById(item.getCategory_id()).getName());
        System.out.println();
    }

}
