package dao;

import util.MySQLUtils;
import entity.MovieEntity;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MovieDao implements IDao<MovieEntity> {
    @Override
    public void save(MovieEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        Statement statement = null;
        String sql = "INSERT INTO movie (title, duration, genre, director, stars, " +
                "country, language, filmingLocation, runtime, aspectRatio, description, poster) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            preparedStatement.setString(8, entity.getFilmingLocation());
            preparedStatement.setString(9, entity.getRuntime());
            preparedStatement.setString(10, entity.getAspectRatio());
            preparedStatement.setString(11, entity.getDescription());
            preparedStatement.setBinaryStream(12, new ByteArrayInputStream(entity.getPoster()));
            boolean status = preparedStatement.execute();
            System.out.println("save: " + !status);
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
    public void update(MovieEntity entity) {

    }

    @Override
    public MovieEntity queryById(MovieEntity entity) {
        return null;
    }

    @Override
    public void delete(MovieEntity entity) {

    }
}
