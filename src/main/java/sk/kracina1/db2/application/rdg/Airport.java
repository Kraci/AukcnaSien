package sk.kracina1.db2.application.rdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Airport extends BaseGateway {

    private Integer id;
    private String name;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void insert() throws SQLException {
        insert("INSERT INTO airports (name) VALUES (?)");
    }

    @Override
    protected void insertFill(PreparedStatement s) throws SQLException {
        s.setString(1, name);
    }

    @Override
    protected void insertUpdateKeys(ResultSet r) throws SQLException {
        id = r.getInt(1);
    }

    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id is not set");
        }
        update("UPDATE airports SET name = ?, city_id = ? WHERE id = ?");
    }

    @Override
    protected void updateFill(PreparedStatement s) throws SQLException {
        s.setString(1, name);
        s.setInt(2, id);
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id is not set");
        }
        delete("DELETE FROM airports WHERE id = ?");
    }

    @Override
    protected void deleteFill(PreparedStatement s) throws SQLException {
        s.setInt(1, id);
    }

}
