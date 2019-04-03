package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemFinder extends BaseFinder<Item> {

    private static final ItemFinder INSTANCE = new ItemFinder();

    public static ItemFinder getInstance() { return INSTANCE; }

    private ItemFinder() { }

    public Item findById(int id) throws SQLException {
        return findByInt("SELECT * FROM items WHERE id = ?", id);
    }

    public List<Item> findFreeByUserId(int userId) throws SQLException {
        return findAll("SELECT * FROM items WHERE user_id = ? and id not in (SELECT item_id FROM auction_items)", userId);
    }

    @Override
    protected Item load(ResultSet r) throws SQLException {
        Item item = new Item();
        item.setId(r.getInt("id"));
        item.setUser_id(r.getInt("user_id"));
        item.setCategory_id(r.getInt("category_id"));
        item.setName(r.getString("name"));
        item.setCount(r.getInt("count"));
        item.setDescription(r.getString("description"));
        return item;
    }
}
