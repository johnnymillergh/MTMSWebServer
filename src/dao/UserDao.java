package dao;

import util.ImageUtil;
import util.MySQLUtil;
import entity.UserEntity;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserDao implements IDao<UserEntity> {

    @Override
    public int save(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "INSERT INTO user (email, password, username, avatar, gender, home_location) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getUsername());
            preparedStatement.setBinaryStream(4, new ByteArrayInputStream(entity.getAvatar()));
            preparedStatement.setString(5, entity.getGender());
            preparedStatement.setString(6, entity.getHomeLocation());
            int status = preparedStatement.executeUpdate();
            System.out.println("save: " + getClass() + ", " + status);
            connection.commit();
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
    public int update(UserEntity entity) {
        return updateByEmail(entity);
    }

    private int updateByEmail(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE user SET password=?, username=?, avatar=?, gender=?, home_location=? WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setString(2, entity.getUsername());
            preparedStatement.setBinaryStream(3, new ByteArrayInputStream(entity.getAvatar()));
            preparedStatement.setString(4, entity.getGender());
            preparedStatement.setString(5, entity.getHomeLocation());
            preparedStatement.setString(6, entity.getEmail());
            int status = preparedStatement.executeUpdate();
            System.out.println("updateByEmail: " + getClass() + ", " + status);
            connection.commit();
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
        List<UserEntity> users = new ArrayList<>();
        UserEntity userEntity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setPassword(resultSet.getString("password"));
                userEntity.setUsername(resultSet.getString("username"));
//                userEntity.setAvatar(resultSet.getBytes("avatar"));
                userEntity.setGender(resultSet.getString("gender"));
                userEntity.setHomeLocation(resultSet.getString("home_location"));
                users.add(userEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            if (users.size() == 0) {
                return null;
            }
            return users;
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

    public UserEntity getPoster(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, email, avatar FROM user WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setAvatarStr(ImageUtil.encode(resultSet.getBytes("avatar")));
                resultSet.close();
                connection.commit();
                System.out.println("getPoster: " + getClass() + ", email: " + userEntity.getEmail());
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

    public UserEntity getPosterBytes(UserEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, email, avatar FROM user WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            UserEntity userEntity = new UserEntity();
            if (resultSet.first()) {
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setAvatar(resultSet.getBytes("avatar"));
                resultSet.close();
                connection.commit();
                System.out.println("getPosterBytes: " + getClass() + ", email: " + userEntity.getEmail());
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
        String sql = "DELETE FROM user WHERE email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getEmail());
            int status = preparedStatement.executeUpdate();
            System.out.println("deleteByEmail: " + getClass() + ", " + status);
            connection.commit();
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
}
