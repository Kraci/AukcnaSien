package sk.kracina1.db2.application;

import java.sql.Connection;

/**
 *
 * @author shanki
 */
public class DbContext {
		
    private static Connection connection;

    public static void setConnection(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("connection cannot be null");
        }

        DbContext.connection = connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("connection must be set before calling this method");
        }

        return connection;
    }

    public static void clear() {
        connection = null;
    }
	
}