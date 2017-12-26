package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MySQLUtils {

//     C3P0.
    private static final DataSource ds = new ComboPooledDataSource("MySQLConnection");
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

//    public static void mainTest(String[] args) throws Exception {
//        Connection connection = MySQLUtils.getConnection();
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("select * from parent");
//        while (rs.next()) {
//            System.out.println(rs.getString("id"));
//        }
//        connection.close();
//    }
}
