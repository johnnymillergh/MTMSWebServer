package dao;

import entity.WatchlistEntity;
import util.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchlistDao implements IDao<WatchlistEntity> {
    @Override
    public int save(WatchlistEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO watchlist (user_id, movie_title) " +
                "VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getMovieTitle());
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
    public int update(WatchlistEntity entity) {
        return 0;
    }

    @Override
    public WatchlistEntity queryById(WatchlistEntity entity) {
        return null;
    }

    public WatchlistEntity queryByUserIdAndMovieTitle(WatchlistEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id FROM watchlist WHERE user_id=? AND movie_title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getMovieTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                WatchlistEntity watchlistEntity = new WatchlistEntity();
                watchlistEntity.setId(resultSet.getInt("id"));
                resultSet.close();
                connection.commit();
                System.out.println("queryByUserIdAndMovieTitle: " + getClass() + ", " + watchlistEntity.getUserId());
                return watchlistEntity;
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
    public int delete(WatchlistEntity entity) {
        return deleteById(entity);
    }

    @SuppressWarnings("Duplicates")
    private int deleteById(WatchlistEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "DELETE FROM watchlist WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            int status = preparedStatement.executeUpdate();
            System.out.println("deleteById: " + getClass() + ", " + status);
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

    public int deleteByUserIdAndMovieTitle(WatchlistEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "DELETE FROM watchlist WHERE user_id=? AND movie_title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getMovieTitle());
            int status = preparedStatement.executeUpdate();
            System.out.println("deleteByUserIdAndMovieTitle: " + getClass() + ", " + status);
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
    public List<WatchlistEntity> getAll() {
        return null;
    }

    public List<WatchlistEntity> getAllByUserId(WatchlistEntity entity) {
        List<WatchlistEntity> watchlist = new ArrayList<>();
        WatchlistEntity userReview;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM user_review WHERE user_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userReview = new WatchlistEntity();
                userReview.setId(resultSet.getInt("id"));
                userReview.setUserId(resultSet.getInt("user_id"));
                userReview.setMovieTitle(resultSet.getString("movie_title"));
                watchlist.add(userReview);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAllByUserId: " + getClass());
            return watchlist;
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
