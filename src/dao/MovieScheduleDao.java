package dao;

import entity.MovieScheduleEntity;
import util.MySQLUtils;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressWarnings("Duplicates")
public class MovieScheduleDao implements IDao<MovieScheduleEntity> {
    @Override
    public int save(MovieScheduleEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        String sql = "INSERT INTO movie_schedule (movie_id, auditorium_id, auditorium_theater_id, " +
                "price, showtime, date_of_show, time_of_show) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setInt(2, entity.getAuditoriumId());
            preparedStatement.setInt(3, entity.getAuditoriumTheaterId());
            preparedStatement.setFloat(4, entity.getPrice());
            preparedStatement.setTimestamp(5, entity.getShowtime());
            preparedStatement.setDate(6, entity.getDateOfShow());
            preparedStatement.setTime(7, entity.getTimeOfShow());
            boolean status = preparedStatement.execute();
            System.out.println("MovieScheduleDao save: " + !status);
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
    public int update(MovieScheduleEntity entity) {
        return 0;
    }

    @Override
    public MovieScheduleEntity queryById(MovieScheduleEntity entity) {
        return null;
    }

    @Override
    public int delete(MovieScheduleEntity entity) {
        return 0;
    }
}
