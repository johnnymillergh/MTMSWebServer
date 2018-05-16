package dao;

import com.mysql.jdbc.MySQLConnection;
import entity.MovieRankingEntity;
import entity.PageEntity;
import entity.UserEntity;
import util.ImageUtil;
import util.MySQLUtil;
import entity.MovieEntity;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"Duplicates", "ConstantConditions"})
public class MovieDao implements IDao<MovieEntity> {
    @Override
    public int save(MovieEntity entity) {
        Connection connection = MySQLUtil.getConnectionNoConnectionPool();
        String sql = "INSERT INTO movie (title, duration, genre, director, stars, " +
                "country, language, release_date, filming_location, runtime, aspect_ratio, description, poster) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
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
    public int update(MovieEntity entity) {
        return updateByTitle(entity);
    }

    private int updateByTitle(MovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "UPDATE movie SET duration=?, genre=? ,director=?, stars=?, country=?, language=?, " +
                "release_date=?, filming_location=?, runtime=?, aspect_ratio=?, description=?, poster=? WHERE title=?";
        try {
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
            int status = preparedStatement.executeUpdate();
            System.out.println("updateByTitle: " + getClass() + ", " + status);
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
    public MovieEntity queryById(MovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM movie WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movieEntity.setTitleCHS(resultSet.getString("title_chs"));// 2
                movieEntity.setDuration(resultSet.getString("duration"));// 3
                movieEntity.setGenre(resultSet.getString("genre"));// 4
                movieEntity.setDirector(resultSet.getString("director"));// 5
                movieEntity.setStars(resultSet.getString("stars"));// 6
                movieEntity.setCountry(resultSet.getString("country"));// 7
                movieEntity.setLanguage(resultSet.getString("language"));// 8
                movieEntity.setReleaseDate(resultSet.getString("release_date"));// 9
                movieEntity.setFilmingLocation(resultSet.getString("filming_location"));// 10
                movieEntity.setRuntime(resultSet.getString("runtime"));// 11
                movieEntity.setAspectRatio(resultSet.getString("aspect_ratio"));// 12
                movieEntity.setDescription(resultSet.getString("description"));// 13
                resultSet.close();
                connection.commit();
                System.out.println("queryById: " + getClass() + ", " + movieEntity.getTitle());
                return movieEntity;
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

    public MovieEntity queryByTitle(MovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM movie WHERE title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movieEntity.setTitleCHS(resultSet.getString("title_chs"));// 2
                movieEntity.setDuration(resultSet.getString("duration"));// 3
                movieEntity.setGenre(resultSet.getString("genre"));// 4
                movieEntity.setDirector(resultSet.getString("director"));// 5
                movieEntity.setStars(resultSet.getString("stars"));// 6
                movieEntity.setCountry(resultSet.getString("country"));// 7
                movieEntity.setLanguage(resultSet.getString("language"));// 8
                movieEntity.setReleaseDate(resultSet.getString("release_date"));// 9
                movieEntity.setFilmingLocation(resultSet.getString("filming_location"));// 10
                movieEntity.setRuntime(resultSet.getString("runtime"));// 11
                movieEntity.setAspectRatio(resultSet.getString("aspect_ratio"));// 12
                movieEntity.setDescription(resultSet.getString("description"));// 13
                movieEntity.setPoster(resultSet.getBytes("poster"));// 14
                resultSet.close();
                connection.commit();
                System.out.println("queryByTitle: " + getClass() + ", " + movieEntity.getTitle());
                return movieEntity;
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
    public int delete(MovieEntity entity) {
        return -1;
    }

    @Override
    public List<MovieEntity> getAll() {
        List<MovieEntity> movies = new ArrayList<>();
        MovieEntity movieEntity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT * FROM movie";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movieEntity.setTitleCHS(resultSet.getString("title_chs"));// 2
                movieEntity.setDuration(resultSet.getString("duration"));// 3
                movieEntity.setGenre(resultSet.getString("genre"));// 4
                movieEntity.setDirector(resultSet.getString("director"));// 5
                movieEntity.setStars(resultSet.getString("stars"));// 6
                movieEntity.setCountry(resultSet.getString("country"));// 7
                movieEntity.setLanguage(resultSet.getString("language"));// 8
                movieEntity.setReleaseDate(resultSet.getString("release_date"));// 9
                movieEntity.setFilmingLocation(resultSet.getString("filming_location"));// 10
                movieEntity.setRuntime(resultSet.getString("runtime"));// 11
                movieEntity.setAspectRatio(resultSet.getString("aspect_ratio"));// 12
                movieEntity.setDescription(resultSet.getString("description"));// 13
                //movieEntity.setPosterStr(resultSet.getString("title"));//14: use title to set the poster name.
                movies.add(movieEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAll: " + getClass());
            return movies;
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

    public List<MovieEntity> getAllMovie() {
        List<MovieEntity> movies = new ArrayList<>();
        MovieEntity movieEntity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, title FROM movie";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movies.add(movieEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getAllMovie: " + getClass());
            return movies;
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

    public List<MovieEntity> getCommonMovie(UserEntity entity) {
        List<MovieEntity> movies = new ArrayList<>();
        MovieEntity movieEntity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT DISTINCT movie_id, movie.title " +
                "FROM user_review ur LEFT JOIN movie " +
                "ON movie_id = movie.id " +
                "WHERE ur.movie_id IN (SELECT movie_id FROM user_review WHERE user_id = ?) AND ur.user_id != ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("movie_id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movies.add(movieEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getCommonMovie: " + getClass());
            return movies;
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

    public MovieEntity getPoster(MovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, title, poster FROM movie WHERE title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movieEntity.setPosterStr(ImageUtil.encode(resultSet.getBytes("poster")));// 14
                resultSet.close();
                connection.commit();
                System.out.println("getPosterStr(title): " + getClass() + ", " + movieEntity.getTitle());
                return movieEntity;
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

    public MovieEntity getPosterBytes(MovieEntity entity) {
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT id, title, poster FROM movie WHERE title=? or title_chs=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            MovieEntity movieEntity = new MovieEntity();
            if (resultSet.first()) {
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movieEntity.setPoster(resultSet.getBytes("poster"));// 14
                resultSet.close();
                connection.commit();
                System.out.println("getPosterBytes: " + getClass() + ", id: " + movieEntity.getId() + ", title: " +
                        movieEntity.getTitle());
                return movieEntity;
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

    public void getAll(PageEntity<MovieEntity> pageEntity) {

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
        String sql = "SELECT * FROM movie LIMIT ?,?";
        Connection connection = MySQLUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, index);
            preparedStatement.setInt(2, count);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MovieEntity> pageData = new ArrayList<>();
            while (resultSet.next()) {
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
                movieEntity.setTitleCHS(resultSet.getString("title_chs"));// 2
                movieEntity.setDuration(resultSet.getString("duration"));// 3
                movieEntity.setGenre(resultSet.getString("genre"));// 4
                movieEntity.setDirector(resultSet.getString("director"));// 5
                movieEntity.setStars(resultSet.getString("stars"));// 6
                movieEntity.setCountry(resultSet.getString("country"));// 7
                movieEntity.setLanguage(resultSet.getString("language"));// 8
                movieEntity.setReleaseDate(resultSet.getString("release_date"));// 9
                movieEntity.setFilmingLocation(resultSet.getString("filming_location"));// 10
                movieEntity.setRuntime(resultSet.getString("runtime"));// 11
                movieEntity.setAspectRatio(resultSet.getString("aspect_ratio"));// 12
                movieEntity.setDescription(resultSet.getString("description"));// 13
                //movieEntity.setPosterStr(resultSet.getString("title"));//14: use title to set the poster name.
                pageData.add(movieEntity);
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
        String sql = "SELECT COUNT(*) AS row_count FROM movie";
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

    public List<MovieRankingEntity> getTopRated() {
        List<MovieRankingEntity> movies = new ArrayList<>();
        MovieRankingEntity movieEntity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT (SELECT title FROM movie WHERE id=user_review.movie_id) AS title, " +
                "(SELECT genre FROM movie WHERE id=user_review.movie_id) as genre, " +
                "avg(score) AS average_score " +
                "FROM user_review GROUP BY movie_id ORDER BY average_score DESC;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                movieEntity = new MovieRankingEntity();
                movieEntity.setTitle(resultSet.getString("title"));
                movieEntity.setGenre(resultSet.getString("genre"));
                movieEntity.setAverageScore(resultSet.getFloat("average_score"));
                movies.add(movieEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getTopRated: " + getClass());
            return movies;
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

    public List<MovieRankingEntity> getTopSelling() {
        List<MovieRankingEntity> movies = new ArrayList<>();
        MovieRankingEntity movieEntity;
        Connection connection = MySQLUtil.getConnection();
        String sql = "SELECT movie_title AS title, SUM(total_price) AS gross " +
                "FROM customer_order " +
                "GROUP BY movie_title " +
                "ORDER BY gross DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                movieEntity = new MovieRankingEntity();
                movieEntity.setTitle(resultSet.getString("title"));
                movieEntity.setGross(resultSet.getFloat("gross"));
                movies.add(movieEntity);
            }
            resultSet.close();
            connection.commit();
            System.out.println("getTopSelling: " + getClass());
            return movies;
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

    public MovieRankingEntity getGross(MovieEntity movieEntity) {
        Connection connection = MySQLUtil.getConnection();
        MovieRankingEntity movieRankingEntity;
        String sql = "SELECT movie_title, SUM(total_price) AS gross " +
                "FROM customer_order " +
                "WHERE movie_title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, movieEntity.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                movieRankingEntity = new MovieRankingEntity();
                movieRankingEntity.setTitle(resultSet.getString("movie_title"));
                movieRankingEntity.setGross(resultSet.getFloat("gross"));
                resultSet.close();
                connection.commit();
                System.out.println("getGross: " + getClass().getSimpleName());
                return movieRankingEntity;
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
