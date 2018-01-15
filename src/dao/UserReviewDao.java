package dao;

import entity.UserReviewEntity;
import util.MySQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("Duplicates")
public class UserReviewDao implements IDao<UserReviewEntity> {
    @Override
    public int save(UserReviewEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        String sql = "INSERT INTO user_review (user_id, movie_id, score, text, date_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getMovieId());
            preparedStatement.setInt(3, entity.getScore());
            preparedStatement.setString(4, entity.getText());
            preparedStatement.setTimestamp(5, entity.getDateTime());
            boolean status = preparedStatement.execute();
            System.out.println("save: " + getClass() + !status);
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
    public int update(UserReviewEntity entity) {
        return 0;
    }

    @Override
    public UserReviewEntity queryById(UserReviewEntity entity) {
        return null;
    }

    public UserReviewEntity queryByUserIdAndMovieId(UserReviewEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM user_review WHERE user_id=? AND movie_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getMovieId());
            ResultSet resultSet = preparedStatement.executeQuery();
            UserReviewEntity entity1 = new UserReviewEntity();
            if (resultSet.first()) {
                entity1.setId(resultSet.getInt("id"));
                entity1.setUserId(resultSet.getInt("user_id"));
                entity1.setMovieId(resultSet.getInt("movie_id"));
                entity1.setScore(resultSet.getInt("score"));
                entity1.setText(resultSet.getString("text"));
                entity1.setDateTime(resultSet.getTimestamp("date_time"));
                resultSet.close();
                connection.commit();
                System.out.println("queryByUserIdAndMovieId: " + getClass() + ", score: " + entity1.getScore());
                return entity1;
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
    public int delete(UserReviewEntity entity) {
        return 0;
    }
}
