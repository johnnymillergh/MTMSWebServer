package dao;

import util.MySQLUtils;
import entity.UserEntity;

import java.sql.*;

@SuppressWarnings("Duplicates")
public class UserDao implements IDao<UserEntity> {

    @Override
    public int save(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        Statement statement = null;
        String sql = "INSERT INTO user (email, password, username) VALUES (?, ?, ?)";
        try {
            statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            boolean status = preparedStatement.execute();
            System.out.println("save: " + !status);
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
        Connection connection = MySQLUtils.getConnection();
        Statement statement = null;
        String sql = "UPDATE user SET email=?, password=?, username=? WHERE id=?";
        try {
            statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setInt(4, entity.getId());
            boolean status = preparedStatement.execute();
            System.out.println("update: " + !status);
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

    public void updateByEmail(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "UPDATE user SET password=?, username=? WHERE email=?";
        try {
            Statement stmt = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setString(3, entity.getEmail());
            boolean status = preparedStatement.execute();
            System.out.println("update: " + !status);
            connection.commit();
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
    }

    @Override
    public UserEntity queryById(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM user WHERE id='" + entity.getId() + "'";
//        String sql = "SELECT * FROM user WHERE id=?";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            UserEntity userEntity = new UserEntity();
            userEntity.setId(resultSet.getInt("id"));
            userEntity.setEmail(resultSet.getString("email"));
            userEntity.setPassword(resultSet.getString("password"));
            userEntity.setUsername(resultSet.getString("username"));
            resultSet.close();
            return userEntity;
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

    public UserEntity queryByEmail(UserEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM user WHERE email='" + entity.getEmail() + "'";
//        String sql = "SELECT * FROM user WHERE email=?";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            UserEntity userEntity = new UserEntity();
            userEntity.setId(resultSet.getInt("id"));
            userEntity.setEmail(resultSet.getString("email"));
            userEntity.setPassword(resultSet.getString("password"));
            userEntity.setUsername(resultSet.getString("username"));
            resultSet.close();
            return userEntity;
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
    public void delete(UserEntity entity) {
    }
}
