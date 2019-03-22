package sk.kracina1.db2.application.rdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item extends BaseGateway {

    private Integer  id;
    private Integer category_id;
    private Integer user_id;
    private Integer count;
    private String name;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected void insert() throws SQLException {
        super.insert("INSERT INTO items (categorry_id, user_id, count, name, description) VALUES (?,?,?,?,?)");
    }

    @Override
    protected void insertFill(PreparedStatement s) throws SQLException {
        s.setInt(1, category_id);
        s.setInt(2,user_id);
        s.setInt(3, count);
        s.setString(4, name);
        s.setString(5, description);
    }

    @Override
    protected void insertUpdateKeys(ResultSet r) throws SQLException {
        id = r.getInt(1);
    }

    protected void update() throws SQLException {
        super.update("UPDATE items SET category_id = ?, user_id = ?, count = ?, name = ?, description = ? WHERE id = ?");
    }

    @Override
    protected void updateFill(PreparedStatement s) throws SQLException {
        s.setInt(1, category_id);
        s.setInt(2, user_id);
        s.setInt(3, count);
        s.setString(4, name);
        s.setString(5, description);
        s.setInt(6, id);
    }

    public void delete() throws SQLException {
        if (id == null){
            throw new IllegalStateException("id is not set");
        }
        super.delete("DELETE FROM items WHERE id = ?");
    }

    @Override
    protected void deleteFill(PreparedStatement s) throws SQLException {
        s.setInt(1, id);
    }
}
