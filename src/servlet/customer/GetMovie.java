package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovieDao;
import entity.MovieEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetMovie")
public class GetMovie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        String movieOperation = request.getParameter("movieOperation");
        switch (movieOperation) {
            case "getAll":
                try {
                    getAllMoviesWithoutPoster(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getPoster":
                try {
                    getPosterOfMovie(request, response);
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

    private void getAllMoviesWithoutPoster(HttpServletResponse response) throws Exception {
        MovieDao movieDao = new MovieDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<MovieEntity> movies = movieDao.getAll();
        PrintWriter out = response.getWriter();
        String json = gson.toJson(movies);
        out.println(json);
        out.flush();
        out.close();
        System.out.println("getAllMoviesWithoutPoster: " + getClass());
    }

    private void getPosterOfMovie(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        // Get parameter
        String title = request.getParameter("title");

        if (title.compareTo("") != 0) {
            movieEntity.setTitle(title);
            movieEntity = movieDao.getPoster(movieEntity);
            String json = gson.toJson(movieEntity);
            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
            System.out.println("getPosterOfMovie: " + getClass());
        }
    }
}
