package servlet.customer;

import com.google.gson.GsonBuilder;
import dao.WatchlistDao;
import entity.WatchlistEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "WatchlistManagement")
public class WatchlistManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("watchlistOperation");
        System.out.println("watchlistOperation: " + radio);
        switch (radio) {
            case "add":
                try {
                    add(request, response);
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
            case "getAll":
                try {
                    getAll(request, response);
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

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String movieTitle = request.getParameter("movieTitle");

        WatchlistDao dao = new WatchlistDao();
        WatchlistEntity entity = new WatchlistEntity();
        entity.setUserId(userId);
        entity.setMovieTitle(movieTitle);

        int status = dao.save(entity);

        if (status > 0) {
            PrintWriter out = response.getWriter();
            out.write("Success: add to watchlist");
            out.flush();
            out.close();
        } else {
            response.sendError(404, "Fail to add to watchlist");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String movieTitle = request.getParameter("movieTitle");

        WatchlistDao dao = new WatchlistDao();
        WatchlistEntity entity = new WatchlistEntity();
        entity.setUserId(userId);
        entity.setMovieTitle(movieTitle);

        int status = dao.delete(entity);

        if (status > 0) {
            PrintWriter out = response.getWriter();
            out.write("Success: delete from watchlist");
            out.flush();
            out.close();
        } else {
            response.sendError(404, "Fail to delete from watchlist");
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        WatchlistDao dao = new WatchlistDao();
        WatchlistEntity entity = new WatchlistEntity();
        entity.setUserId(userId);

        List<WatchlistEntity> watchlist = dao.getAllByUserId(entity);

        String json = new GsonBuilder().disableHtmlEscaping().create().toJson(watchlist);

        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
