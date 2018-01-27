package dao;

import entity.PageEntity;
import util.ImageUtil;
import util.MySQLUtil;
import entity.MovieEntity;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
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
        return null;
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
                System.out.println("getPoster(title): " + getClass() + ", " + movieEntity.getTitle());
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
        String sql = "SELECT id, title, poster FROM movie WHERE title=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getTitle());
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

        //2. 查询总记录数;  设置到pageEntity对象中
        int totalCount = this.getTotalCount();
        pageEntity.setTotalCount(totalCount);

        /*
         * 问题： jsp页面，如果当前页为首页，再点击上一页报错！
         *              如果当前页为末页，再点下一页显示有问题！
         * 解决：
         *        1. 如果当前页 <= 0;       当前页设置当前页为1;
         *        2. 如果当前页 > 最大页数；  当前页设置为最大页数
         */
        // 判断
        if (pageEntity.getCurrentPage() <= 0) {
            pageEntity.setCurrentPage(1);                        // 把当前页设置为1
        } else if (pageEntity.getCurrentPage() > pageEntity.getTotalPage()) {
            pageEntity.setCurrentPage(pageEntity.getTotalPage());        // 把当前页设置为最大页数
        }

        //1. 获取当前页： 计算查询的起始行、返回的行数
        int currentPage = pageEntity.getCurrentPage();
        int index = (currentPage - 1) * pageEntity.getRowCount();        // 查询的起始行
        int count = pageEntity.getRowCount();                            // 查询返回的行数

        //3. 分页查询数据;  把查询到的数据设置到pageEntity对象中
        String sql = "SELECT * FROM movie LIMIT ?,?";
        Connection connection = MySQLUtil.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, index);
            preparedStatement.setInt(2, count);
            // 根据当前页，查询当前页数据(一页数据)
            ResultSet resultSet = preparedStatement.executeQuery();
            List<MovieEntity> pageData = new ArrayList<>();
            while (resultSet.next()) {
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(resultSet.getInt("id"));// 1
                movieEntity.setTitle(resultSet.getString("title"));// 2
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
            // 设置到pb对象中
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
}
