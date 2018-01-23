package dao;

import entity.OrderEntity;
import util.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements IDao<OrderEntity> {
    @Override
    public int save(OrderEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        String sql = "INSERT INTO order (user_id, order_datetime, movie_schedule_id, is_paid, is_used, ticket_amount, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
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
    public int update(OrderEntity entity) {
        return 0;
    }

    @Override
    public OrderEntity queryById(OrderEntity entity) {
        return null;
    }

    @Override
    public int delete(OrderEntity entity) {
        return 0;
    }

    @Override
    public List<OrderEntity> getAll() {
        return null;
    }
}
