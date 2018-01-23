package dao;

import entity.UserReviewEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserReviewDao implements IDao<UserReviewEntity> {
    @Override
    public int save(UserReviewEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO user_review (user_id, movie_id, score, title, text, date_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getMovieId());
            preparedStatement.setInt(3, entity.getScore());
            preparedStatement.setString(4, entity.getTitle());
            preparedStatement.setString(5, entity.getText());
            preparedStatement.setTimestamp(6, entity.getDateTime());
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
    public int update(UserReviewEntity entity) {
        return updateByUserIdAndMovieId(entity);
    }

    private int updateByUserIdAndMovieId(UserReviewEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE user_review SET score=?, title=?, text=?, date_time=? WHERE user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getScore());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setTimestamp(4, entity.getDateTime());
            int status = preparedStatement.executeUpdate();
            System.out.println("updateByUserIdAndMovieId: " + getClass() + ", " + status);
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
    public UserReviewEntity queryById(UserReviewEntity entity) {
        return null;
    }

    public UserReviewEntity queryByUserIdAndMovieId(UserReviewEntity entity) {
        Connection connection = MySQLUtil.getConnection();
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
                entity1.setTitle(resultSet.getString("title"));
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
    public List<UserReviewEntity> getAll() {
        List<UserReviewEntity> userReviewList = new ArrayList<>();
        UserReviewEntity userReview;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM user_review";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                userReview = new UserReviewEntity();
                userReview.setId(resultSet.getInt("id"));
                userReview.setUserId(resultSet.getInt("user_id"));
                userReview.setMovieId(resultSet.getInt("movie_id"));
                userReview.setScore(resultSet.getInt("score"));
                userReview.setTitle(resultSet.getString("title"));
                userReview.setText(resultSet.getString("text"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReviewList.add(userReview);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            return userReviewList;
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

    public List<UserReviewEntity> getAllReviewsOfUser(UserReviewEntity entity) {
        List<UserReviewEntity> userReviewList = new ArrayList<>();
        UserReviewEntity userReview;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM user_review WHERE user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userReview = new UserReviewEntity();
                userReview.setId(resultSet.getInt("id"));
                userReview.setUserId(resultSet.getInt("user_id"));
                userReview.setMovieId(resultSet.getInt("movie_id"));
                userReview.setScore(resultSet.getInt("score"));
                userReview.setTitle(resultSet.getString("title"));
                userReview.setText(resultSet.getString("text"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReviewList.add(userReview);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAllReviewsOfUser: " + getClass());
            return userReviewList;
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

    public List<UserReviewEntity> getAllReviewsOfMovie(UserReviewEntity entity) {
        List<UserReviewEntity> userReviewList = new ArrayList<>();
        UserReviewEntity userReview;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM user_review WHERE movie_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userReview = new UserReviewEntity();
                userReview.setId(resultSet.getInt("id"));
                userReview.setUserId(resultSet.getInt("user_id"));
                userReview.setMovieId(resultSet.getInt("movie_id"));
                userReview.setScore(resultSet.getInt("score"));
                userReview.setTitle(resultSet.getString("title"));
                userReview.setText(resultSet.getString("text"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReviewList.add(userReview);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAllReviewsOfMovie: " + getClass());
            return userReviewList;
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
    public int delete(UserReviewEntity entity) {
        return 0;
    }
}
