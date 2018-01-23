package dao;

import entity.TopMovieEntity;
import util.MySQLUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopMovieDao implements IDao<TopMovieEntity> {
    @Override
    public int save(TopMovieEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        String sql = "INSERT INTO top_movie (id, movie_id, movie_title) " +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getMovieId());
            preparedStatement.setString(3, entity.getMovieTitle());
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
    public int update(TopMovieEntity entity) {
        return updateById(entity);
    }

    private int updateById(TopMovieEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "UPDATE top_movie SET movie_id=?, movie_title=? " +
                "WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setString(2, entity.getMovieTitle());
            preparedStatement.setInt(3, entity.getId());
            boolean status = preparedStatement.execute();
            connection.commit();
            System.out.println("updateById: " + getClass() + ", " + !status);
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
    public TopMovieEntity queryById(TopMovieEntity entity) {
        return null;
    }

    @Override
    public int delete(TopMovieEntity entity) {
        return deleteById(entity);
    }

    private int deleteById(TopMovieEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "DELETE FROM top_movie WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            boolean status = preparedStatement.execute();
            System.out.println("deleteById: " + getClass() + ", id: " + entity.getId());
            connection.commit();
            return 1;
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
    public List<TopMovieEntity> getAll() {
        List<TopMovieEntity> topMivies = new ArrayList<>();
        TopMovieEntity topMovie;
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM top_movie";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                topMovie = new TopMovieEntity();
                topMovie.setId(resultSet.getInt("id"));
                topMovie.setMovieId(resultSet.getInt("movie_id"));
                topMovie.setMovieTitle(resultSet.getString("movie_title"));
                topMivies.add(topMovie);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            if (topMivies.size() == 0) {
                return null;
            }
            return topMivies;
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
