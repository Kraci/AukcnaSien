package sk.kracina1.db2.application.rdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category extends BaseGateway {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void insert() throws SQLException {
        super.insert("");
    }

    @Override
    protected void insertFill(PreparedStatement s) throws SQLException {

    }

    @Override
    protected void insertUpdateKeys(ResultSet r) throws SQLException {

    }

    @Override
    protected void updateFill(PreparedStatement s) throws SQLException {

    }

    @Override
    protected void deleteFill(PreparedStatement s) throws SQLException {

    }
}
