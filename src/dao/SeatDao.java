package dao;

import entity.SeatEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class SeatDao implements IDao<SeatEntity> {
    @Override
    public int save(SeatEntity entity) {
        return 0;
    }

    @Override
    public int update(SeatEntity entity) {
        return updateById(entity);
    }

    public int updateById(SeatEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE seat SET is_selected=?, user_id=?, user_email=?, order_datetime=? " +
                "WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, entity.getIsSelected());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setString(3, entity.getUserEmail());
            preparedStatement.setTimestamp(4, entity.getOrderDatetime());
            preparedStatement.setInt(5, entity.getId());
            int status = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("updateById: " + getClass() + ", " + status);
            if (status > 0) {
                return status;
            } else {
                return -1;
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

    public int updateByAuditoriumId(SeatEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE seat SET is_selected=?, user_id=?, user_email=?, order_datetime=? " +
                "WHERE auditorium_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, entity.getIsSelected());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setString(3, entity.getUserEmail());
            preparedStatement.setTimestamp(4, entity.getOrderDatetime());
            preparedStatement.setInt(5, entity.getAuditoriumId());
            int status = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("updateByAuditoriumId: " + getClass() + ", " + status);
            if (status > 0) {
                return status;
            } else {
                return -1;
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
    public SeatEntity queryById(SeatEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM seat WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                SeatEntity seatEntity = new SeatEntity();
                seatEntity.setId(resultSet.getInt("id"));
                seatEntity.setAuditoriumId(resultSet.getInt("auditorium_id"));
                seatEntity.setRowNumber(resultSet.getInt("row_number"));
                seatEntity.setColNumber(resultSet.getInt("col_number"));
                seatEntity.setIsSelected(resultSet.getBoolean("is_selected"));
                seatEntity.setUserId(resultSet.getInt("user_id"));
                seatEntity.setUserEmail(resultSet.getString("user_email"));
                seatEntity.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                resultSet.close();
                System.out.println("queryById: " + getClass() + ", isSelected: " + seatEntity.getIsSelected());
                return seatEntity;
            } else {
                return null;
            }
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

    @Override
    public int delete(SeatEntity entity) {
        return 0;
    }

    @Override
    public List<SeatEntity> getAll() {
        List<SeatEntity> entities = new ArrayList<>();
        SeatEntity entity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM theater";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entity = new SeatEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setAuditoriumId(resultSet.getInt("auditorium_id"));
                entity.setRowNumber(resultSet.getInt("row_number"));
                entity.setColNumber(resultSet.getInt("col_number"));
                entity.setIsSelected(resultSet.getBoolean("is_selected"));
                entity.setUserId(resultSet.getInt("user_id"));
                entity.setUserEmail(resultSet.getString("user_email"));
                entity.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                entities.add(entity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            if (entities.size() == 0) {
                return null;
            } else {
                return entities;
            }
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

    public List<SeatEntity> getSeatOfAuditorium(SeatEntity entity) {
        List<SeatEntity> entities = new ArrayList<>();
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM seat WHERE auditorium_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getAuditoriumId());
            ResultSet resultSet = preparedStatement.executeQuery();
            SeatEntity seatEntity;
            while (resultSet.next()) {
                seatEntity = new SeatEntity();
                seatEntity.setId(resultSet.getInt("id"));
                seatEntity.setAuditoriumId(resultSet.getInt("auditorium_id"));
                seatEntity.setRowNumber(resultSet.getInt("row_number"));
                seatEntity.setColNumber(resultSet.getInt("col_number"));
                seatEntity.setIsSelected(resultSet.getBoolean("is_selected"));
                seatEntity.setUserId(resultSet.getInt("user_id"));
                seatEntity.setUserEmail(resultSet.getString("user_email"));
                seatEntity.setOrderDatetime(resultSet.getTimestamp("order_datetime"));
                entities.add(seatEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            if (entities.size() == 0) {
                return null;
            } else {
                return entities;
            }
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
