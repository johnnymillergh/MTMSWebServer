package dao;

import entity.TopMovieEntity;
import util.ImageUtil;
import util.MySQLUtil;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopMovieDao implements IDao<TopMovieEntity> {
    @Override
    public int save(TopMovieEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO top_movie (id, movie_id, movie_title, poster) " +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getMovieId());
            preparedStatement.setString(3, entity.getMovieTitle());
            preparedStatement.setBinaryStream(4, new ByteArrayInputStream(entity.getPoster()));
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
    public int update(TopMovieEntity entity) {
        return updateById(entity);
    }

    private int updateById(TopMovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE top_movie SET movie_id=?, movie_title=?, poster=?" +
                "WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setString(2, entity.getMovieTitle());
            preparedStatement.setBinaryStream(3, new ByteArrayInputStream(entity.getPoster()));
            preparedStatement.setInt(4, entity.getId());
            int status = preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("updateById: " + getClass() + ", " + status);
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
    public TopMovieEntity queryById(TopMovieEntity entity) {
        return null;
    }

    @Override
    public int delete(TopMovieEntity entity) {
        return deleteById(entity);
    }

    private int deleteById(TopMovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "DELETE FROM top_movie WHERE id=?";
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
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM top_movie";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                topMovie = new TopMovieEntity();
                topMovie.setId(resultSet.getInt("id"));
                topMovie.setMovieId(resultSet.getInt("movie_id"));
                topMovie.setMovieTitle(resultSet.getString("movie_title"));
//                topMovie.setPoster(resultSet.getBytes("poster"));
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

    public TopMovieEntity getPosterBytes(TopMovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, poster FROM top_movie WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            TopMovieEntity topMovieEntity = new TopMovieEntity();
            if (resultSet.first()) {
                topMovieEntity.setId(resultSet.getInt("id"));
                topMovieEntity.setPoster(resultSet.getBytes("poster"));
                resultSet.close();
                connection.commit();
                System.out.println("getPosterBytes: " + getClass() + ", id: " + topMovieEntity.getId());
                return topMovieEntity;
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

    public TopMovieEntity getPoster(TopMovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, poster FROM top_movie WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                TopMovieEntity topMovieEntity = new TopMovieEntity();
                topMovieEntity.setId(resultSet.getInt("id"));
                topMovieEntity.setPoster(resultSet.getBytes("poster"));
                resultSet.close();
                connection.commit();
                System.out.println("getPoster: " + getClass() + ", id: " + topMovieEntity.getId());
                return topMovieEntity;
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
}
