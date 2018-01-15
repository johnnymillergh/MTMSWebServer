package dao;

import util.MySQLUtils;
import entity.UserEntity;

import java.sql.*;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserDao implements IDao<UserEntity> {

    @Override
    public int save(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "INSERT INTO user (email, password, username) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            boolean status = preparedStatement.execute();
            System.out.println("save: " + getClass() + ", " + !status);
            connection.commit();
            return 1;
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
    public int update(UserEntity entity) {
        return updateByEmail(entity);
    }

    private int updateByEmail(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "UPDATE user SET password=?, username=? WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getEmail());
            boolean status = preparedStatement.execute();
            System.out.println("updateByEmail: " + getClass() + ", " + !status);
            connection.commit();
            return 1;
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
    public UserEntity queryById(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM user WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setPassword(resultSet.getString("password"));
                userEntity.setUsername(resultSet.getString("username"));
                resultSet.close();
                System.out.println("queryById: " + getClass() + ", " + userEntity.getUsername());
                return userEntity;
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
    public int delete(UserEntity entity) {
        return deleteByEmail(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }


    public UserEntity queryByEmail(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM user WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setPassword(resultSet.getString("password"));
                userEntity.setUsername(resultSet.getString("username"));
                resultSet.close();
                connection.commit();
                System.out.println("queryByEmail: " + getClass() + ", " + userEntity.getUsername());
                return userEntity;
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


    private int deleteByEmail(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "DELETE FROM user WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            boolean status = preparedStatement.execute();
            System.out.println("deleteByEmail: " + getClass() + ", id: " + entity.getId());
            connection.commit();
            return 1;
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
}
