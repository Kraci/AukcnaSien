package sk.kracina1.db2.application.rdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends BaseGateway {

    private Integer id;
    private String username;
    private String mail;
    private String password;
    private String firstName;
    private String surname;
    private Integer age;
    private Double money;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getMoney() { return money; }
    public void setMoney(Double money) { this.money = money; }

    public void insert() throws SQLException {
        insert("INSERT INTO users (username, mail, password, first_name, surname, age, money) VALUES (?,?,?,?,?,?,?)");
    }

    @Override
    protected void insertFill(PreparedStatement s) throws SQLException {
        s.setString(1, username);
        s.setString(2, mail);
        s.setString(3, password);
        s.setString(4, firstName);
        s.setString(5, surname);
        s.setInt(6, age);
        s.setDouble(7, money);
    }

    @Override
    protected void insertUpdateKeys(ResultSet r) throws SQLException {
        id = r.getInt(1);
    }

    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id is not set");
        }
        update("UPDATE users SET username = ?, mail = ?, password = ?, first_name = ?, surname = ?, age = ?, money = ? WHERE id = ?");
    }

    @Override
    protected void updateFill(PreparedStatement s) throws SQLException {
        s.setString(1, username);
        s.setString(2, mail);
        s.setString(3, password);
        s.setString(4, firstName);
        s.setString(5, surname);
        s.setInt(6, age);
        s.setDouble(7, money);
        s.setInt(8, id);
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id is not set");
        }
        delete("DELETE FROM users WHERE id = ?");
    }

    @Override
    protected void deleteFill(PreparedStatement s) throws SQLException {
        s.setInt(1, id);
    }

}
