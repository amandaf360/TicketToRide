package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLTransactionManager {

    private static final String JDBC_DRIVER_CLASS = "org.sqlite.JDBC";

    static {
        try {
            Class.forName(JDBC_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Connection connection;

    private SQLTransactionManager() {
        return;
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException();
        }

        return connection;
    }

    public static void open() throws SQLException {
        String dbName = "db/bookclub.sqlite";
        String connectionURL = "jdbc:sqlite:" + dbName;

        if (connection != null) {
            throw new IllegalStateException();
        }

        connection = DriverManager.getConnection(connectionURL);
        connection.setAutoCommit(false);
    }

    public static void close(boolean commit) throws SQLException {
        if (connection == null) {
            throw new IllegalStateException();
        }

        if (commit) {
            connection.commit();
        } else {
            connection.rollback();
        }

        connection = null;
    }
}