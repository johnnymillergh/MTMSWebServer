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

        String movieTitle = request.getParameter("movieTitle");

        MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();
        MovieScheduleDao movieScheduleDao = new MovieScheduleDao();
        movieScheduleEntity.setMovieTitle(movieTitle);
        List<MovieScheduleEntity> movieSchedules = movieScheduleDao.getAllByMovieTitle(movieScheduleEntity);

        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        PrintWriter out = response.getWriter();
        String json = gson.toJson(movieSchedules);
        out.write(json);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
