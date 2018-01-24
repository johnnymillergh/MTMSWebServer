package dao;

import entity.AuditoriumEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditoriumDao implements IDao<AuditoriumEntity> {
    @Override
    public int save(AuditoriumEntity entity) {
        return 0;
    }

    @Override
    public int update(AuditoriumEntity entity) {
        return 0;
    }

    @Override
    public AuditoriumEntity queryById(AuditoriumEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM auditorium WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                AuditoriumEntity auditoriumEntity = new AuditoriumEntity();
                auditoriumEntity.setId(resultSet.getInt("id"));
                auditoriumEntity.setTheaterId(resultSet.getInt("theater_id"));
                auditoriumEntity.setName(resultSet.getString("name"));
                auditoriumEntity.setCapacity(resultSet.getInt("capacity"));
                resultSet.close();
                System.out.println("queryById: " + getClass() + ", name: " + auditoriumEntity.getName());
                return auditoriumEntity;
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
    public int delete(AuditoriumEntity entity) {
        return 0;
    }

    @Override
    public List<AuditoriumEntity> getAll() {
        List<AuditoriumEntity> entities = new ArrayList<>();
        AuditoriumEntity entity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM auditorium";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entity = new AuditoriumEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setTheaterId(resultSet.getInt("theater_id"));
                entity.setName(resultSet.getString("name"));
                entity.setCapacity(resultSet.getInt("capacity"));
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
