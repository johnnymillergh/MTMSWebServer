package dao;

import entity.TheaterEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class TheaterDao implements IDao<TheaterEntity> {
    @Override
    public int save(TheaterEntity entity) {
        return 0;
    }

    @Override
    public int update(TheaterEntity entity) {
        return 0;
    }

    @Override
    public TheaterEntity queryById(TheaterEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM theater WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                TheaterEntity theaterEntity = new TheaterEntity();
                theaterEntity.setId(resultSet.getInt("id"));
                theaterEntity.setName(resultSet.getString("name"));
                theaterEntity.setLocation(resultSet.getString("location"));
                resultSet.close();
                System.out.println("queryById: " + getClass() + ", " + theaterEntity.getName());
                return theaterEntity;
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

    public TheaterEntity queryByName(TheaterEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM theater WHERE name=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                TheaterEntity theaterEntity = new TheaterEntity();
                theaterEntity.setId(resultSet.getInt("id"));
                theaterEntity.setName(resultSet.getString("name"));
                theaterEntity.setLocation(resultSet.getString("location"));
                resultSet.close();
                System.out.println("queryByName: " + getClass() + ", " + theaterEntity.getLocation());
                return theaterEntity;
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
    public int delete(TheaterEntity entity) {
        return 0;
    }

    @Override
    public List<TheaterEntity> getAll() {
        List<TheaterEntity> entities = new ArrayList<>();
        TheaterEntity entity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM theater";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entity = new TheaterEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setName(resultSet.getString("name"));
                entity.setLocation(resultSet.getString("location"));
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
}
