package com.gmail.juliarusakevich.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//утилитный: final class + private конструктор
public final class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String DRIVER_KEY = "db.driver";

    private ConnectionManager() {
    }

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection get() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USER_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
