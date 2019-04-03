package sk.kracina1.db2.application.ui;

import sk.kracina1.db2.application.rdg.User;
import sk.kracina1.db2.application.rdg.UserFinder;

import java.sql.SQLException;

public class Login {

    private static final Login INSTANCE = new Login();

    public static Login getInstance() { return INSTANCE; }

    private boolean loggedIn = false;
    private int userID = -1;

    private Login() { }

    public boolean login(String usernameOrMail, String password) throws SQLException {
        User user;

        user = UserFinder.getInstance().findByUsernameAndPassword(usernameOrMail, password);
        if (user == null) {
            user = UserFinder.getInstance().findByMailAndPassword(usernameOrMail, password);
        }

        if (user == null) {
            return false;
        }

        setLoggedIn(true);
        setUserID(user.getId());

        return true;
    }

    public void logout() {
        setLoggedIn(false);
        setUserID(-1);
    }

    private void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    private void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}
