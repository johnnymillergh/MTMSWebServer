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

        TopMovieDao dao = new TopMovieDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<TopMovieEntity> topMovieList = dao.getAll();

        PrintWriter out = null;
        try {
            out = response.getWriter();
            String json = gson.toJson(topMovieList);
            out.println(json);
            out.flush();
            System.out.println("getJson: " + getClass());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
