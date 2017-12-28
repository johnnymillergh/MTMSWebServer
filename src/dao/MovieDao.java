package dao;

import util.MySQLUtils;
import entity.MovieEntity;

import java.io.ByteArrayInputStream;
import java.sql.*;

public class MovieDao implements IDao<MovieEntity> {
    @Override
    public int save(MovieEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        Statement statement = null;
        String sql = "INSERT INTO movie (title, duration, genre, director, stars, " +
                "country, language, release_date, filming_location, runtime, aspect_ratio, description, poster) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDuration());
            preparedStatement.setString(3, entity.getGenre());
            preparedStatement.setString(4, entity.getDirector());
            preparedStatement.setString(5, entity.getStars());
            preparedStatement.setString(6, entity.getCountry());
            preparedStatement.setString(7, entity.getLanguage());
            preparedStatement.setString(8, entity.getReleaseDate());
            preparedStatement.setString(9, entity.getFilmingLocation());
            preparedStatement.setString(10, entity.getRuntime());
            preparedStatement.setString(11, entity.getAspectRatio());
            preparedStatement.setString(12, entity.getDescription());
            preparedStatement.setBinaryStream(13, new ByteArrayInputStream(entity.getPoster()));
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
    public int update(MovieEntity entity) {
        return updateByTitle(entity);
    }

    @Override
    public MovieEntity queryById(MovieEntity entity) {
        return null;
    }

    @Override
    public int delete(MovieEntity entity) {
        return -1;
    }

    private int updateByTitle(MovieEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "UPDATE movie SET duration=?, genre=? ,director=?, stars=?, country=?, language=?, " +
                "release_date=?, filming_location=?, runtime=?, aspect_ratio=?, description=?, poster=? WHERE title=?";
        try {
//            Statement stmt = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getDuration());
            preparedStatement.setString(2, entity.getGenre());
            preparedStatement.setString(3, entity.getDirector());
            preparedStatement.setString(4, entity.getStars());
            preparedStatement.setString(5, entity.getCountry());
            preparedStatement.setString(6, entity.getLanguage());
            preparedStatement.setString(7, entity.getReleaseDate());
            preparedStatement.setString(8, entity.getFilmingLocation());
            preparedStatement.setString(9, entity.getRuntime());
            preparedStatement.setString(10, entity.getAspectRatio());
            preparedStatement.setString(11, entity.getDescription());
            preparedStatement.setBinaryStream(12, new ByteArrayInputStream(entity.getPoster()));
            preparedStatement.setString(13, entity.getTitle());
            boolean status = preparedStatement.execute();
            System.out.println("updateByTitle: " + !status);
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

    public MovieEntity queryByTitle(MovieEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM movie WHERE title='" + entity.getTitle() + "'";
//        String sql = "SELECT * FROM movie WHERE title=?";
        try {
            Statement statement = connection.createStatement();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, entity.getTitle());
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setId(resultSet.getInt("id"));
            movieEntity.setTitle(resultSet.getString("title"));
            movieEntity.setDuration(resultSet.getString("duration"));
            movieEntity.setGenre(resultSet.getString("genre"));
            movieEntity.setDirector(resultSet.getString("director"));
            movieEntity.setStars(resultSet.getString("stars"));
            movieEntity.setCountry(resultSet.getString("country"));
            movieEntity.setLanguage(resultSet.getString("language"));
            movieEntity.setReleaseDate(resultSet.getString("release_date"));
            movieEntity.setFilmingLocation(resultSet.getString("filming_location"));
            movieEntity.setRuntime(resultSet.getString("runtime"));
            movieEntity.setAspectRatio(resultSet.getString("aspect_ratio"));
            movieEntity.setDescription(resultSet.getString("description"));
            movieEntity.setPoster(resultSet.getBytes("poster"));
            resultSet.close();
            connection.commit();
            System.out.println("queryByTitle: "+movieEntity.getLanguage());
            return movieEntity;
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
