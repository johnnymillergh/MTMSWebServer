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
                getAllMoviesWithoutPoster(response);
                break;
            case "getPoster":
                getPosterOfMovie(request, response);
                break;
            default:
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void getAllMoviesWithoutPoster(HttpServletResponse response) {
        MovieDao movieDao = new MovieDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<MovieEntity> movies = movieDao.getAll();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String json = gson.toJson(movies);
            out.println(json);
            out.flush();
            System.out.println("getAllMoviesWithoutPoster");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private void getPosterOfMovie(HttpServletRequest request, HttpServletResponse response) {
        MovieDao movieDao = new MovieDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        MovieEntity movieEntity = new MovieEntity();
        String title = request.getParameter("title");
        PrintWriter out = null;
        if (title.compareTo("") != 0) {
            movieEntity.setTitle(title);
            movieEntity = movieDao.getPoster(movieEntity);
            String json = gson.toJson(movieEntity);
            try {
                out = response.getWriter();
                out.println(json);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }
}
