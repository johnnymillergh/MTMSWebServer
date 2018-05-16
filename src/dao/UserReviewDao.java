package dao;

import entity.PageEntity;
import entity.UserReviewEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserReviewDao implements IDao<UserReviewEntity> {
    @Override
    public int save(UserReviewEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO user_review (user_id, movie_id, score, title, text, is_spoilers, date_time, username) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getMovieId());
            preparedStatement.setInt(3, entity.getScore());
            preparedStatement.setString(4, entity.getTitle());
            preparedStatement.setString(5, entity.getText());
            preparedStatement.setBoolean(6, entity.getIsSpoilers());
            preparedStatement.setTimestamp(7, entity.getDateTime());
            preparedStatement.setString(8, entity.getUsername());
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
        String sql = "UPDATE user_review SET score=?, title=?, text=?, is_spoilers=?, date_time=?" +
                "WHERE user_id=? AND movie_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getScore());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getText());
            preparedStatement.setBoolean(4, entity.getIsSpoilers());
            preparedStatement.setTimestamp(5, entity.getDateTime());
            preparedStatement.setInt(6, entity.getUserId());
            preparedStatement.setInt(7, entity.getMovieId());
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
                entity1.setIsSpoilers(resultSet.getBoolean("is_spoilers"));
                entity1.setDateTime(resultSet.getTimestamp("date_time"));
                entity1.setUsername(resultSet.getString("username"));
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
                userReview.setIsSpoilers(resultSet.getBoolean("is_spoilers"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReview.setUsername(resultSet.getString("username"));
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
                userReview.setIsSpoilers(resultSet.getBoolean("is_spoilers"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReview.setUsername(resultSet.getString("username"));
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
                userReview.setIsSpoilers(resultSet.getBoolean("is_spoilers"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReview.setUsername(resultSet.getString("username"));
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

    public List<UserReviewEntity> getReviewOfUserAndMovie(UserReviewEntity entity) {
        List<UserReviewEntity> userReviewList = new ArrayList<>();
        UserReviewEntity userReview;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM user_review WHERE user_id=? AND movie_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getMovieId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userReview = new UserReviewEntity();
                userReview.setId(resultSet.getInt("id"));
                userReview.setUserId(resultSet.getInt("user_id"));
                userReview.setMovieId(resultSet.getInt("movie_id"));
                userReview.setScore(resultSet.getInt("score"));
                userReview.setTitle(resultSet.getString("title"));
                userReview.setText(resultSet.getString("text"));
                userReview.setIsSpoilers(resultSet.getBoolean("is_spoilers"));
                userReview.setDateTime(resultSet.getTimestamp("date_time"));
                userReview.setUsername(resultSet.getString("username"));
                userReviewList.add(userReview);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getReviewOfUserAndMovie: " + getClass());
            if (userReviewList.size() != 0) {
                return userReviewList;
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
    public int delete(UserReviewEntity entity) {
        return 0;
    }

    public void getAll(PageEntity<UserReviewEntity> pageEntity) {

        // Get totalCount and use it to get how many pages will be generated
        int totalCount = this.getTotalCount();
        pageEntity.setTotalCount(totalCount);

        /*
         * Problem:
         * 1. If current page is the first page, clicking Prev will throw exception;
         * 2. If current page is the last page, clicking Next will also break down;
         *
         * Solution:
         * 1. If variable 'currentPage' <= 0, set the currentPage to equal 1;
         * 2. If variable 'currentPage' > variable 'totalPage', set the currentPage to equal variable 'totalPage';
         */
        if (pageEntity.getCurrentPage() <= 0) {
            pageEntity.setCurrentPage(1);
        } else if (pageEntity.getCurrentPage() > pageEntity.getTotalPage()) {
            pageEntity.setCurrentPage(pageEntity.getTotalPage());
        }

        // Use currentPage and page offset to get index
        int currentPage = pageEntity.getCurrentPage();
        int index = (currentPage - 1) * pageEntity.getRowCount();
        int count = pageEntity.getRowCount();

        // Query data
        String sql = "SELECT * FROM user_review LIMIT ?,?";
        Connection connection = MySQLUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, index);
            preparedStatement.setInt(2, count);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<UserReviewEntity> pageData = new ArrayList<>();
            while (resultSet.next()) {
                UserReviewEntity entity = new UserReviewEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setUserId(resultSet.getInt("user_id"));
                entity.setMovieId(resultSet.getInt("movie_id"));
                entity.setScore(resultSet.getInt("score"));
                entity.setTitle(resultSet.getString("title"));
                entity.setText(resultSet.getString("text"));
                entity.setIsSpoilers(resultSet.getBoolean("is_spoilers"));
                entity.setDateTime(resultSet.getTimestamp("date_time"));
                entity.setUsername(resultSet.getString("username"));
                pageData.add(entity);
            }
            pageEntity.setPageData(pageData);
        } catch (Exception e) {
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

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) AS row_count FROM user_review";
        Connection connection = MySQLUtil.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("row_count");
            } else {
                return -1;
            }
        } catch (Exception e) {
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
     * SELECT cartesian_product.user_id, cartesian_product.movie_id, IFNULL(score, 0) AS score
     * FROM(SELECT u.id AS user_id, m.id AS movie_id FROM user u JOIN movie m) AS cartesian_product
     * LEFT JOIN(SELECT user_id, movie_id, score FROM user_review) AS user_review
     * ON cartesian_product.user_id = user_review.user_id AND cartesian_product.movie_id = user_review.movie_id
     * ORDER BY cartesian_product.user_id ASC , cartesian_product.movie_id ASC;
     */
    public HashMap<Integer, List<UserReviewEntity>> queryUserScoreMatrix() {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT cartesian_product.user_id, cartesian_product.movie_id, IFNULL(score, 0) AS score " +
                "FROM(SELECT u.id AS user_id, m.id AS movie_id FROM user u JOIN movie m) AS cartesian_product " +
                "LEFT JOIN(SELECT user_id, movie_id, score FROM user_review) AS user_review " +
                "ON cartesian_product.user_id = user_review.user_id AND cartesian_product.movie_id = user_review.movie_id " +
                "ORDER BY cartesian_product.user_id ASC , cartesian_product.movie_id ASC;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            HashMap<Integer, List<UserReviewEntity>> userScoreMatrix = new HashMap<>();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                List<UserReviewEntity> userReviewEntityList = userScoreMatrix.get(userId);
                if (userReviewEntityList == null) {
                    userReviewEntityList = new ArrayList<>();
                    userScoreMatrix.put(userId, userReviewEntityList);
                }
                UserReviewEntity userReviewEntity = new UserReviewEntity();
                userReviewEntity.setMovieId(resultSet.getInt("movie_id"));
                userReviewEntity.setScore(resultSet.getInt("score"));
                userReviewEntityList.add(userReviewEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("queryUserScoreMatrix: " + getClass());
            return userScoreMatrix;
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
}
