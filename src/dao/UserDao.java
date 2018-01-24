package dao;

import util.MySQLUtil;
import entity.UserEntity;

import java.sql.*;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserDao implements IDao<UserEntity> {

    @Override
    public int save(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "INSERT INTO user (email, password, username) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
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
    public int update(UserEntity entity) {
        return updateByEmail(entity);
    }

    private int updateByEmail(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE user SET password=?, username=? WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getEmail());
            int status = preparedStatement.executeUpdate();
            System.out.println("updateByEmail: " + getClass() + ", " + status);
            connection.commit();
            return status;
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
    public UserEntity queryById(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
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
    public int delete(UserEntity entity) {
        return deleteByEmail(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }


    public UserEntity queryByEmail(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
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


    private int deleteByEmail(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "DELETE FROM user WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            int status = preparedStatement.executeUpdate();
            System.out.println("deleteByEmail: " + getClass() + ", " + status);
            connection.commit();
            return status;
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
}
