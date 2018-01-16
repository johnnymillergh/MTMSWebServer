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
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "GetUserReview")
public class GetUserReview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
