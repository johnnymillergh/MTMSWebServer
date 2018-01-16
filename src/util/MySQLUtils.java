package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.*;

public class MySQLUtils implements ServletContextListener {

    // C3P0
    private static DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Initial C3P0: " + getClass());
        ds = new ComboPooledDataSource("MySQLConnection");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Destroy C3P0: " + getClass());
    }

    public static Connection getConnection() {
        try {
            Connection connection = ds.getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Configurations for connection without connection pool.
    private static final String URL = "jdbc:mysql://localhost:3306/movie_ticket_management_system?useSSL=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static Connection connection;


    public static Connection getConnectionNoConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
