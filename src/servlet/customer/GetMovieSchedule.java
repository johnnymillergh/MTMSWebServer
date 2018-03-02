package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovieScheduleDao;
import entity.MovieScheduleEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetMovieSchedule")
public class GetMovieSchedule extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        try {
            String movieTitle = request.getParameter("movieTitle");
            String movieScheduleIdStr = request.getParameter("movieScheduleId");

            MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();
            MovieScheduleDao movieScheduleDao = new MovieScheduleDao();

            PrintWriter out = response.getWriter();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            if (movieTitle != null) {
                movieScheduleEntity.setMovieTitle(movieTitle);
                List<MovieScheduleEntity> movieSchedules = movieScheduleDao.getAllByMovieTitle(movieScheduleEntity);

                String json = gson.toJson(movieSchedules);
                out.write(json);
                out.flush();
                out.close();
            } else if (movieScheduleIdStr != null) {
                int movieScheduleId = Integer.parseInt(movieScheduleIdStr);
                movieScheduleEntity.setId(movieScheduleId);
                movieScheduleEntity = movieScheduleDao.queryById(movieScheduleEntity);
                String json = gson.toJson(movieScheduleEntity);
                out.write(json);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
