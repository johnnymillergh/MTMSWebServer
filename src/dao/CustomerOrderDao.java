package dao;

import entity.CustomerOrderEntity;
import util.MySQLUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDao implements IDao<CustomerOrderEntity> {
    @Override
    public int save(CustomerOrderEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        String sql = "INSERT INTO customer_order (user_id, order_datetime, movie_schedule_id, is_paid, is_used, ticket_amount, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setTimestamp(2, entity.getOrderDatetime());
            preparedStatement.setInt(3, entity.getMovieScheduleId());
            preparedStatement.setBoolean(4, entity.getIsPaid());
            preparedStatement.setBoolean(5, entity.getIsUsed());
            preparedStatement.setInt(6, entity.getTicketAmount());
            preparedStatement.setFloat(7, entity.getTotalPrice());
            int status = preparedStatement.executeUpdate();
            System.out.println("save: " + getClass() + ", " + status);
            connection.commit();
            return status;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    @Override
    public int update(CustomerOrderEntity entity) {
        return 0;
    }

    public int updateByUserIdAndOrderDatetime(CustomerOrderEntity entity) {
        return -1;
    }

    @Override
    public CustomerOrderEntity queryById(CustomerOrderEntity entity) {
        return null;
    }

    @Override
    public int delete(CustomerOrderEntity entity) {
        return 0;
    }

    @Override
    public List<CustomerOrderEntity> getAll() {
        List<CustomerOrderEntity> orders = new ArrayList<>();
        CustomerOrderEntity order;
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM customer_order";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                order = new CustomerOrderEntity();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                order.setMovieScheduleId(resultSet.getInt("movie_schedule_id"));
                order.setIsPaid(resultSet.getBoolean("is_paid"));
                order.setIsUsed(resultSet.getBoolean("is_used"));
                order.setTicketAmount(resultSet.getInt("ticket_amount"));
                order.setTotalPrice(resultSet.getFloat("total_price"));
                orders.add(order);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            if (orders.size() == 0) {
                return null;
            }
            return orders;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
