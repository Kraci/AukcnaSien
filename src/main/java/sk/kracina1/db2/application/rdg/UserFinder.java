package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserFinder extends BaseFinder<User> {

    private static final UserFinder INSTANCE = new UserFinder();

    public static UserFinder getInstance() { return INSTANCE; }

    protected UserFinder() { }

    public User findById(int id) throws SQLException {
        return findByInt("SELECT * FROM users WHERE id = ?", id);
    }

    public List<User> findAll() throws SQLException {
        return findAll("SELECT * FROM users");
    }

    public User findUserByName(String username) throws SQLException {
        return findByString("SELECT * FROM users WHERE username = ?", username);
    }

    public User findUserByMail(String mail) throws SQLException {
        return findByString("SELECT * FROM users WHERE mail = ?", mail);
    }

    public User findByUsernameAndPassword(String name, String password) throws SQLException {
        return findByString("SELECT * FROM users WHERE username = ? AND password = ?", name, password);
    }

    public User findByMailAndPassword(String mail, String password) throws SQLException {
        return findByString("SELECT * FROM users WHERE mail = ? AND password = ?", mail, password);
    }

    @Override
    protected User load(ResultSet r) throws SQLException {
        User user = new User();

        user.setId(r.getInt("id"));
        user.setUsername(r.getString("username"));
        user.setMail(r.getString("mail"));
        user.setPassword(r.getString("password"));
        user.setFirstName(r.getString("first_name"));
        user.setSurname(r.getString("surname"));
        user.setAge(r.getInt("age"));
        user.setMoney(r.getDouble("money"));

        return user;
    }

}
