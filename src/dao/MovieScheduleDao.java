package dao;

import entity.MovieScheduleEntity;
import util.MySQLUtils;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class MovieScheduleDao implements IDao<MovieScheduleEntity> {
    @Override
    public int save(MovieScheduleEntity entity) {
        Connection connection = MySQLUtils.getConnectionNoConnectionPool();
        String sql = "INSERT INTO movie_schedule (movie_id, auditorium_theater_id, auditorium_id, " +
                "price, showtime, date_of_show, time_of_show) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setInt(2, entity.getAuditoriumTheaterId());
            preparedStatement.setInt(3, entity.getAuditoriumId());
            preparedStatement.setFloat(4, entity.getPrice());
            preparedStatement.setTimestamp(5, entity.getShowtime());
            preparedStatement.setDate(6, entity.getDateOfShow());
            preparedStatement.setTime(7, entity.getTimeOfShow());
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
    public int update(MovieScheduleEntity entity) {
        return updateById(entity);
    }

    private int updateById(MovieScheduleEntity entity) {
        Connection connection = MySQLUtils.getConnection();
        String sql = "UPDATE movie_schedule SET movie_id=?, auditorium_theater_id=?, auditorium_id=?, price=?, " +
                "showtime=?, date_of_show=?, time_of_show=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setInt(2, entity.getAuditoriumTheaterId());
            preparedStatement.setInt(3, entity.getAuditoriumId());
            preparedStatement.setFloat(4, entity.getPrice());
            preparedStatement.setTimestamp(5, entity.getShowtime());
            preparedStatement.setDate(6, entity.getDateOfShow());
            preparedStatement.setTime(7, entity.getTimeOfShow());
            preparedStatement.setInt(8, entity.getId());
            boolean status = preparedStatement.execute();
            System.out.println("updateById: " + getClass() + ", " + !status);
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
    public MovieScheduleEntity queryById(MovieScheduleEntity entity) {
        return null;
    }

    @Override
    public int delete(MovieScheduleEntity entity) {
        return 0;
    }

    @Override
    public List<MovieScheduleEntity> getAll() {
        List<MovieScheduleEntity> movieScheduleEntities = new ArrayList<>();
        MovieScheduleEntity entity;
        Connection connection = MySQLUtils.getConnection();
        String sql = "SELECT * FROM movie_schedule";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entity = new MovieScheduleEntity();
                entity.setId(resultSet.getInt("id"));
                entity.setMovieId(resultSet.getInt("movie_id"));
                entity.setAuditoriumId(resultSet.getInt("auditorium_id"));
                entity.setAuditoriumTheaterId(resultSet.getInt("auditorium_theater_id"));
                entity.setPrice(resultSet.getFloat("price"));
                entity.setShowtime(resultSet.getTimestamp("showtime"));
                entity.setDateOfShow(resultSet.getDate("date_of_show"));
                entity.setTimeOfShow(resultSet.getTime("time_of_show"));
                movieScheduleEntities.add(entity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            return movieScheduleEntities;
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
