package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.Category;

public class CategoryPrinter {
    private static final CategoryPrinter INSTANCE = new CategoryPrinter();

    public static CategoryPrinter getInstance() { return INSTANCE; }

    private CategoryPrinter() {}

    public void print(Category category) {
        if (category == null) {
            throw new NullPointerException("Category cannot be null");
        }
        System.out.print("id:         ");
        System.out.println(category.getId());
        System.out.print("username:   ");
        System.out.println(category.getName());
        System.out.println();
    }
}
