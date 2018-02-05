import com.mchange.v2.c3p0.ComboPooledDataSource;
import util.MySQLUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws Exception{
        DataSource dataSource;
        dataSource = new ComboPooledDataSource("MySQLConnection");
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("SELECT COUNT(*) AS row_count FROM user");
        ResultSet resultSet = preparedStatement.executeQuery();
        int id;
        String username;
//        while (resultSet.next()) {
//            id = resultSet.getInt("id");
//            username = resultSet.getString("username");
//            System.out.println("ID: " + id + ", Username: " + username);
//        }
        if (resultSet.next()) {
            System.out.println("Row count: "+resultSet.getInt("row_count"));
        }
    }
}
