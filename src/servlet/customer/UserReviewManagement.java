package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovieDao;
import dao.UserDao;
import dao.UserReviewDao;
import entity.MovieEntity;
import entity.UserEntity;
import entity.UserReviewEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.add;

@SuppressWarnings("Duplicates")
@WebServlet(name = "UserReviewManagement")
public class UserReviewManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("userReviewOperation");
        System.out.println("userReviewOperation: " + radio);
        switch (radio) {
            case "add":
                try {
                    add(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                try {
                    update(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    delete(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "query":
                try {
                    query(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getJson":
                try {
                    getJson(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserReviewDao userReviewDao = new UserReviewDao();
        UserReviewEntity userReviewEntity = new UserReviewEntity();

        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();

        // Get parameters
        String email = request.getParameter("email");
        String movieTitle = request.getParameter("movieTitle");
        int score = Integer.parseInt(request.getParameter("score"));
        String userReviewTitle = request.getParameter("userReviewTitle");
        String text = request.getParameter("text");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        Timestamp dateTime = Timestamp.valueOf(date + " " + time);

        // Query: if the user have rated this movie
        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);
        movieEntity.setTitle(movieTitle);
        movieEntity = movieDao.queryByTitle(movieEntity);
        if (userEntity != null && movieEntity != null) {
            userReviewEntity.setUserId(userEntity.getId());
            userReviewEntity.setMovieId(movieEntity.getId());
            userReviewEntity = userReviewDao.queryByUserIdAndMovieId(userReviewEntity);
            if (userReviewEntity == null) {
                userReviewEntity = new UserReviewEntity();
                userReviewEntity.setUserId(userEntity.getId());
                userReviewEntity.setMovieId(movieEntity.getId());
                userReviewEntity.setScore(score);
                userReviewEntity.setTitle(userReviewTitle);
                userReviewEntity.setText(text);
                userReviewEntity.setDateTime(dateTime);
                int status = userReviewDao.save(userReviewEntity);
                if (status == 1) {
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + getClass() + " add: " + userEntity.getUsername() + ", " +
                            movieEntity.getTitle() + "');window.location.href='/administrator.jsp'</script>");
                    out.flush();
                    out.close();
                } else {
                    System.out.println(userReviewEntity.toString());
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('" + getClass() + " Add failed." +
                            "');window.location.href='/administrator.jsp'</script>");
                    out.flush();
                    out.close();
                }
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('" + getClass() + " ERROR: User rated: " + userEntity.getUsername() + ", " +
                        movieEntity.getTitle() + "');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " ERROR: User or movie not found.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Todo: update user review by user id and movie id.
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Todo: delete user review by user id and movie id.
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");

        UserReviewDao userReviewDao = new UserReviewDao();
        UserReviewEntity userReviewEntity = new UserReviewEntity();

        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();

        // Get parameter
        String email = request.getParameter("email");
        String movieTitle = request.getParameter("movieTitle");

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<UserReviewEntity> userReviewEntityList;
        PrintWriter out = null;

        if (email == null) {
            email = "";
        }
        if (movieTitle == null) {
            movieTitle = "";
        }

        if (email.compareTo("") == 0 && movieTitle.compareTo("") == 0) {
            userReviewEntityList = userReviewDao.getAll();
            out = response.getWriter();
            String json = gson.toJson(userReviewEntityList);
            out.println(json);
            out.flush();
            System.out.println("getJson: " + getClass());
            out.close();
        } else if (email.compareTo("") == 0 && movieTitle.compareTo("") != 0) {
            movieEntity.setTitle(movieTitle);
            movieEntity = movieDao.queryByTitle(movieEntity);
            if (movieEntity != null) {
                userReviewEntity.setMovieId(movieEntity.getId());
                userReviewEntityList = userReviewDao.getAllReviewsOfMovie(userReviewEntity);

                out = response.getWriter();
                String json = gson.toJson(userReviewEntityList);
                out.println(json);
                out.flush();
                System.out.println("getJson: " + getClass());
                out.close();
            }
        } else if (email.compareTo("") != 0 && movieTitle.compareTo("") == 0) {
            userEntity.setEmail(email);
            userEntity = userDao.queryByEmail(userEntity);
            if (userEntity != null) {
                userReviewEntity.setUserId(userEntity.getId());
                userReviewEntityList = userReviewDao.getAllReviewsOfUser(userReviewEntity);

                out = response.getWriter();
                String json = gson.toJson(userReviewEntityList);
                out.println(json);
                out.flush();
                System.out.println("getJson: " + getClass());
                out.close();
            }
        } else {
            out = response.getWriter();
            out.println("Parameters ERROR!");
            out.flush();
            out.close();
        }
    }
}
