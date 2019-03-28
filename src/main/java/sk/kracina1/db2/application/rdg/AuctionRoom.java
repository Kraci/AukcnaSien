package sk.kracina1.db2.application.rdg;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuctionRoom extends BaseGateway {

    private Integer id;
    private Integer category_id;

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

    public void insert() throws SQLException {
        super.insert("INSERT INTO auction_rooms (category_id) VALUES (?)");
    }

    @Override
    protected void insertFill(PreparedStatement s) throws SQLException {
        s.setInt(1, category_id);
    }

    @Override
    protected void insertUpdateKeys(ResultSet r) throws SQLException {
        id = r.getInt(1);
    }

    @Override
    protected void updateFill(PreparedStatement s) throws SQLException {

    }

    public void delete() throws SQLException {
        super.delete("DELETE FROM auction_rooms WHERE id = ?");
    }

    @Override
    protected void deleteFill(PreparedStatement s) throws SQLException {
        s.setInt(1, id);
    }
}
