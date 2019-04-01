package sk.kracina1.db2.application;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String username = "postgres";
    private static final String password = "";

    private static final ConnectionTest INSTANCE = new ConnectionTest();

    public static ConnectionTest getInstance() { return INSTANCE; }

    private ConnectionTest() { }

    public void connect() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DbContext.setConnection(connection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clear() {
        DbContext.clear();
    }

}
