package sk.kracina1.db2.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sk.kracina1.db2.application.ui.MainMenu;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DbContext.setConnection(connection);

            MainMenu mainMenu = new MainMenu();
            mainMenu.run();

        } finally {
            DbContext.clear();
        }
    }

}