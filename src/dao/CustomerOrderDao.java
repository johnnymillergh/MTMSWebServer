package dao;

import entity.CustomerOrderEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class CustomerOrderDao implements IDao<CustomerOrderEntity> {

    /**
     * Save all order information but <b>payment_datetime</b> and <b>used_datetime</b>
     *
     * @param entity CustomerOrderEntity
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */
    @Override
    public int save(CustomerOrderEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO customer_order" +
                "(user_id, order_datetime, movie_schedule_id, " +
                "movie_title, showtime, seat_id, seat_location, auditorium_name, theater_name, theater_location, " +
                "is_paid, is_used, ticket_amount, total_price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setTimestamp(2, entity.getOrderDatetime());
            preparedStatement.setInt(3, entity.getMovieScheduleId());
            preparedStatement.setString(4, entity.getMovieTitle());
            preparedStatement.setTimestamp(5, entity.getShowtime());
            preparedStatement.setString(6, entity.getSeatId());
            preparedStatement.setString(7, entity.getSeatLocation());
            preparedStatement.setString(8, entity.getAuditoriumName());
            preparedStatement.setString(9, entity.getTheaterName());
            preparedStatement.setString(10, entity.getTheaterLocation());
            preparedStatement.setBoolean(11, entity.getIsPaid());
            preparedStatement.setBoolean(12, entity.getIsUsed());
            preparedStatement.setInt(13, entity.getTicketAmount());
            preparedStatement.setFloat(14, entity.getTotalPrice());
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
        return updateByUserIdAndOrderDatetime(entity);
    }

    public int updateByUserIdAndOrderDatetime(CustomerOrderEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE customer_order SET user_id=?, order_datetime=?, movie_schedule_id=?, is_paid=?, is_used=?, ticket_amount=?, total_price=? " +
                "WHERE user_id=? AND order_datetime=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setTimestamp(2, entity.getOrderDatetime());
            preparedStatement.setInt(3, entity.getMovieScheduleId());
            preparedStatement.setBoolean(4, entity.getIsPaid());
            preparedStatement.setBoolean(5, entity.getIsUsed());
            preparedStatement.setInt(6, entity.getTicketAmount());
            preparedStatement.setFloat(7, entity.getTotalPrice());
            preparedStatement.setInt(8, entity.getUserId());
            preparedStatement.setTimestamp(9, entity.getOrderDatetime());
            int status = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("updateByUserIdAndOrderDatetime: " + getClass() + ", Row: " + status);
            if (status > 0) {
                return status;
            } else {
                return -2;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return -1;
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

    /**
     * Update payment information by <b>user id</b> and <b>order datetime</b>
     *
     * @param entity CustomerOrderEntity
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */
    public int updatePayment(CustomerOrderEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE customer_order SET is_paid=?, payment_datetime=?" +
                "WHERE user_id=? AND order_datetime=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, entity.getIsPaid());
            preparedStatement.setTimestamp(2, entity.getPaymentDatetime());
            preparedStatement.setInt(3, entity.getUserId());
            preparedStatement.setTimestamp(4, entity.getOrderDatetime());
            int status = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("updatePayment: " + getClass() + ", Row: " + status);
            if (status > 0) {
                return status;
            } else {
                return -2;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return -1;
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

    /**
     * Update usage information by <b>user id</b> and <b>order datetime</b>
     *
     * @param entity CustomerOrderEntity
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     */
    public int updateUsed(CustomerOrderEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE customer_order SET is_used=?, used_datetime=?" +
                "WHERE user_id=? AND order_datetime=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, entity.getIsUsed());
            preparedStatement.setTimestamp(2, entity.getUsedDatetime());
            preparedStatement.setInt(3, entity.getUserId());
            preparedStatement.setTimestamp(4, entity.getOrderDatetime());
            int status = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("updateUsed: " + getClass() + ", Row: " + status);
            if (status > 0) {
                return status;
            } else {
                return -2;
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return -1;
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

    @Override
    public CustomerOrderEntity queryById(CustomerOrderEntity entity) {
        return null;
    }

    public CustomerOrderEntity queryByUserIdAndOrderDatetime(CustomerOrderEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM customer_order WHERE user_id=? AND order_datetime=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setTimestamp(2, entity.getOrderDatetime());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
                customerOrderEntity.setId(resultSet.getInt("id"));
                customerOrderEntity.setUserId(resultSet.getInt("user_id"));
                customerOrderEntity.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                customerOrderEntity.setMovieScheduleId(resultSet.getInt("movie_schedule_id"));
                customerOrderEntity.setMovieTitle(resultSet.getString("movie_title"));
                customerOrderEntity.setShowtime(resultSet.getTimestamp("showtime"));
                customerOrderEntity.setSeatId(resultSet.getString("seat_id"));
                customerOrderEntity.setSeatLocation(resultSet.getString("seat_location"));
                customerOrderEntity.setAuditoriumName(resultSet.getString("auditorium_name"));
                customerOrderEntity.setTheaterName(resultSet.getString("theater_name"));
                customerOrderEntity.setTheaterLocation(resultSet.getString("theater_location"));
                customerOrderEntity.setIsPaid(resultSet.getBoolean("is_paid"));
                customerOrderEntity.setPaymentDatetime(resultSet.getTimestamp("payment_datetime"));
                customerOrderEntity.setIsUsed(resultSet.getBoolean("is_used"));
                customerOrderEntity.setUsedDatetime(resultSet.getTimestamp("used_datetime"));
                customerOrderEntity.setTicketAmount(resultSet.getInt("ticket_amount"));
                customerOrderEntity.setTotalPrice(resultSet.getFloat("total_price"));
                resultSet.close();
                connection.commit();
                System.out.println("queryByUserIdAndOrderDatetime: " + getClass() + ", Total price: " + customerOrderEntity.getTotalPrice());
                return customerOrderEntity;
            }
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
        Connection connection = MySQLUtil.getConnection();
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
                order.setMovieTitle(resultSet.getString("movie_title"));
                order.setShowtime(resultSet.getTimestamp("showtime"));
                order.setSeatId(resultSet.getString("seat_id"));
                order.setSeatLocation(resultSet.getString("seat_location"));
                order.setAuditoriumName(resultSet.getString("auditorium_name"));
                order.setTheaterName(resultSet.getString("theater_name"));
                order.setTheaterLocation(resultSet.getString("theater_location"));
                order.setIsPaid(resultSet.getBoolean("is_paid"));
                order.setPaymentDatetime(resultSet.getTimestamp("payment_datetime"));
                order.setIsUsed(resultSet.getBoolean("is_used"));
                order.setUsedDatetime(resultSet.getTimestamp("used_datetime"));
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

    public List<CustomerOrderEntity> getAllByUserId(CustomerOrderEntity entity) {
        List<CustomerOrderEntity> orders = new ArrayList<>();
        CustomerOrderEntity order;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM customer_order WHERE user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = new CustomerOrderEntity();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                order.setMovieScheduleId(resultSet.getInt("movie_schedule_id"));
                order.setMovieTitle(resultSet.getString("movie_title"));
                order.setShowtime(resultSet.getTimestamp("showtime"));
                order.setSeatId(resultSet.getString("seat_id"));
                order.setSeatLocation(resultSet.getString("seat_location"));
                order.setAuditoriumName(resultSet.getString("auditorium_name"));
                order.setTheaterName(resultSet.getString("theater_name"));
                order.setTheaterLocation(resultSet.getString("theater_location"));
                order.setIsPaid(resultSet.getBoolean("is_paid"));
                order.setPaymentDatetime(resultSet.getTimestamp("payment_datetime"));
                order.setIsUsed(resultSet.getBoolean("is_used"));
                order.setUsedDatetime(resultSet.getTimestamp("used_datetime"));
                order.setTicketAmount(resultSet.getInt("ticket_amount"));
                order.setTotalPrice(resultSet.getFloat("total_price"));
                orders.add(order);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAllByUserId: " + getClass());
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
