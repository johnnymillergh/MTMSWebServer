package dao;

import entity.MovieScheduleEntity;
import util.MySQLUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"Duplicates", "ConstantConditions"})
public class MovieScheduleDao implements IDao<MovieScheduleEntity> {
    @Override
    public int save(MovieScheduleEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO movie_schedule (movie_id, movie_title, auditorium_theater_id, theater_name, location," +
                "auditorium_id, auditorium_name, price, showtime, date_of_show, time_of_show) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setString(2, entity.getMovieTitle());
            preparedStatement.setInt(3, entity.getAuditoriumTheaterId());
            preparedStatement.setString(4, entity.getTheaterName());
            preparedStatement.setString(5, entity.getLocation());
            preparedStatement.setInt(6, entity.getAuditoriumId());
            preparedStatement.setString(7, entity.getAuditoriumName());
            preparedStatement.setFloat(8, entity.getPrice());
            preparedStatement.setTimestamp(9, entity.getShowtime());
            preparedStatement.setDate(10, entity.getDateOfShow());
            preparedStatement.setTime(11, entity.getTimeOfShow());
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
    public int update(MovieScheduleEntity entity) {
        return updateById(entity);
    }

    private int updateById(MovieScheduleEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE movie_schedule SET movie_id=?, movie_title=?, auditorium_theater_id=?, theater_name=?," +
                "location=?, auditorium_id=?, auditorium_name=?, price=?, showtime=?, date_of_show=?, time_of_show=?" +
                "WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getMovieId());
            preparedStatement.setString(2, entity.getMovieTitle());
            preparedStatement.setInt(3, entity.getAuditoriumTheaterId());
            preparedStatement.setString(4, entity.getTheaterName());
            preparedStatement.setString(5, entity.getLocation());
            preparedStatement.setInt(6, entity.getAuditoriumId());
            preparedStatement.setString(7, entity.getAuditoriumName());
            preparedStatement.setFloat(8, entity.getPrice());
            preparedStatement.setTimestamp(9, entity.getShowtime());
            preparedStatement.setDate(10, entity.getDateOfShow());
            preparedStatement.setTime(11, entity.getTimeOfShow());
            preparedStatement.setInt(12, entity.getId());
            int status = preparedStatement.executeUpdate();
            System.out.println("updateById: " + getClass() + ", " + status);
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
    public MovieScheduleEntity queryById(MovieScheduleEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM movie_schedule WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();
                movieScheduleEntity.setId(resultSet.getInt("id"));// 1
                movieScheduleEntity.setMovieId(resultSet.getInt("movie_id"));// 2
                movieScheduleEntity.setMovieTitle(resultSet.getString("movie_title"));// 3
                movieScheduleEntity.setAuditoriumTheaterId(resultSet.getInt("auditorium_theater_id"));// 4
                movieScheduleEntity.setTheaterName(resultSet.getString("theater_name"));// 5
                movieScheduleEntity.setLocation(resultSet.getString("location"));// 5
                movieScheduleEntity.setAuditoriumId(resultSet.getInt("auditorium_id"));// 7
                movieScheduleEntity.setAuditoriumName(resultSet.getString("auditorium_name"));// 8
                movieScheduleEntity.setPrice(resultSet.getFloat("price"));// 9
                movieScheduleEntity.setShowtime(resultSet.getTimestamp("showtime"));// 10
                movieScheduleEntity.setDateOfShow(resultSet.getDate("date_of_show"));// 11
                movieScheduleEntity.setTimeOfShow(resultSet.getTime("time_of_show"));// 12
                resultSet.close();
                connection.commit();
                System.out.println("queryById: " + getClass() + ", " + "Price: " + movieScheduleEntity.getPrice());
                return movieScheduleEntity;
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

    public List<MovieScheduleEntity> getAllByMovieTitle(MovieScheduleEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM movie_schedule WHERE movie_title=?";
        List<MovieScheduleEntity> movieSchedules = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getMovieTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entity = new MovieScheduleEntity();
                entity.setId(resultSet.getInt("id"));// 1
                entity.setMovieId(resultSet.getInt("movie_id"));// 2
                entity.setMovieTitle(resultSet.getString("movie_title"));// 3
                entity.setAuditoriumTheaterId(resultSet.getInt("auditorium_theater_id"));// 4
                entity.setTheaterName(resultSet.getString("theater_name"));// 5
                entity.setLocation(resultSet.getString("location"));// 6
                entity.setAuditoriumId(resultSet.getInt("auditorium_id"));// 7
                entity.setAuditoriumName(resultSet.getString("auditorium_name"));// 8
                entity.setPrice(resultSet.getFloat("price"));// 9
                entity.setShowtime(resultSet.getTimestamp("showtime"));// 10
                entity.setDateOfShow(resultSet.getDate("date_of_show"));// 11
                entity.setTimeOfShow(resultSet.getTime("time_of_show"));// 12
                movieSchedules.add(entity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAllByMovieTitle: " + getClass());
            return movieSchedules;
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
    public int delete(MovieScheduleEntity entity) {
        return 0;
    }

    @Override
    public List<MovieScheduleEntity> getAll() {
        List<MovieScheduleEntity> movieScheduleEntities = new ArrayList<>();
        MovieScheduleEntity entity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM movie_schedule";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entity = new MovieScheduleEntity();
                entity.setId(resultSet.getInt("id"));// 1
                entity.setMovieId(resultSet.getInt("movie_id"));// 2
                entity.setMovieTitle(resultSet.getString("movie_title"));// 3
                entity.setAuditoriumTheaterId(resultSet.getInt("auditorium_theater_id"));// 4
                entity.setTheaterName(resultSet.getString("theater_name"));// 5
                entity.setLocation(resultSet.getString("location"));// 6
                entity.setAuditoriumId(resultSet.getInt("auditorium_id"));// 7
                entity.setAuditoriumName(resultSet.getString("auditorium_name"));// 8
                entity.setPrice(resultSet.getFloat("price"));// 9
                entity.setShowtime(resultSet.getTimestamp("showtime"));// 10
                entity.setDateOfShow(resultSet.getDate("date_of_show"));// 11
                entity.setTimeOfShow(resultSet.getTime("time_of_show"));// 12
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
