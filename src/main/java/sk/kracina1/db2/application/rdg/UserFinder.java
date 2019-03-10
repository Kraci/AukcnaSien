package sk.kracina1.db2.application.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserFinder extends BaseFinder<User> {

    private static final UserFinder INSTANCE = new UserFinder();

    public static UserFinder getInstance() { return INSTANCE; }

    private UserFinder() { }

    public User findById(int id) throws SQLException {
        return findByInt("SELECT * FROM users WHERE id = ?", id);
    }

    public List<User> findAll() throws SQLException {
        return findAll("SELECT * FROM users");
    }

    @Override
    protected User load(ResultSet r) throws SQLException {
        User user = new User();

        user.setId(r.getInt("id"));
        user.setUsername(r.getString("username"));
        user.setMail(r.getString("mail"));
        user.setPassword(r.getString("password"));
        user.setFirstName(r.getString("first_name"));
        user.setSurName(r.getString("surname"));
        user.setAge(r.getInt("age"));

        return user;
    }

}
