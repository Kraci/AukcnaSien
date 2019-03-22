package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryFinder extends BaseFinder<Category> {

    private static final CategoryFinder INSTANCE = new CategoryFinder();

    public static CategoryFinder getInstance() { return INSTANCE; }

    private CategoryFinder() { }

    public List<Category> findAll() throws SQLException {
        return findAll("SELECT * FROM categories");
    }

    public Category findById(int id) throws SQLException {
        return findByInt("SELECT * FROM categories WHERE id = ?", id);
    }

    @Override
    protected Category load(ResultSet r) throws SQLException {
        Category category = new Category();

        category.setId(r.getInt("id"));
        category.setName(r.getString("name"));

        return category;
    }
}
