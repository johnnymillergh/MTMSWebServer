package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.TopMovieDao;
import entity.TopMovieEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "GetTopMovie")
public class GetTopMovie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        String radio = request.getParameter("topMovieOperation");
        System.out.println("topMovieOperation: " + radio);
        switch (radio) {
            case "getAll":
                try {
                    getAllTopMoviesWithoutPoster(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getPoster":
                try {
                    getPosterOfTopMovie(request, response);
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

    private void getAllTopMoviesWithoutPoster(HttpServletResponse response) throws IOException {
        TopMovieDao dao = new TopMovieDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<TopMovieEntity> topMovieList = dao.getAll();

        PrintWriter out = null;
        out = response.getWriter();
        String json = gson.toJson(topMovieList);
        out.println(json);
        out.flush();
        System.out.println("getJson: " + getClass());
        out.close();
    }

    private void getPosterOfTopMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TopMovieDao dao = new TopMovieDao();
        TopMovieEntity entity = new TopMovieEntity();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        // Get parameter
        String id = request.getParameter("id");

        PrintWriter out = null;
        if (id.compareTo("") != 0) {
            entity.setId(Integer.parseInt(id));
            entity = dao.getPosterStr(entity);
            String json = gson.toJson(entity);
            out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
            System.out.println("getPosterOfMovie: " + getClass());
        }
    }
}
