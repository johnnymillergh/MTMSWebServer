package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.AuditoriumDao;
import dao.MovieDao;
import dao.MovieScheduleDao;
import dao.TheaterDao;
import entity.AuditoriumEntity;
import entity.MovieEntity;
import entity.MovieScheduleEntity;
import entity.TheaterEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "MovieScheduleManagement")
public class MovieScheduleManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("movieScheduleOperation");
        System.out.println("movieScheduleOperation: " + radio);
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
            case "getAll":
                try {
                    getAll(request, response);
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
        MovieScheduleEntity entity = new MovieScheduleEntity();
        MovieScheduleDao dao = new MovieScheduleDao();

        // Get parameters
        String movieId = request.getParameter("movieId");
        String theaterId = request.getParameter("theaterId");
        String auditoriumId = request.getParameter("auditoriumId");
        String price = request.getParameter("price");
        String dateOfShow = request.getParameter("dateOfShow");
        String timeOfShow = request.getParameter("timeOfShow");

        // Query movie
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();
        movieEntity.setId(Integer.parseInt(movieId));
        movieEntity = movieDao.queryById(movieEntity);

        // Query theater
        TheaterEntity theaterEntity = new TheaterEntity();
        TheaterDao theaterDao = new TheaterDao();
        theaterEntity.setId(Integer.parseInt(theaterId));
        theaterEntity = theaterDao.queryById(theaterEntity);

        // Query auditorium
        AuditoriumEntity auditoriumEntity = new AuditoriumEntity();
        AuditoriumDao auditoriumDao = new AuditoriumDao();
        auditoriumEntity.setId(Integer.parseInt(auditoriumId));
        auditoriumEntity = auditoriumDao.queryById(auditoriumEntity);

        // Add
        if (movieEntity != null && theaterEntity != null && auditoriumEntity != null) {
            entity.setMovieId(Integer.parseInt(movieId));
            entity.setMovieTitle(movieEntity.getTitle());
            entity.setAuditoriumTheaterId(theaterEntity.getId());
            entity.setTheaterName(theaterEntity.getName());
            entity.setLocation(theaterEntity.getLocation());
            entity.setAuditoriumId(auditoriumEntity.getId());
            entity.setAuditoriumName(auditoriumEntity.getName());
            entity.setPrice(Float.parseFloat(price));
            entity.setShowtime(Timestamp.valueOf(dateOfShow + " " + timeOfShow));

            int status = dao.save(entity);
            if (status == 1) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('" + getClass() + " add: success.');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('" + getClass() + " add: failure.');window.history.go(-1);</script>");
                out.flush();
                out.close();
            }
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieScheduleDao dao = new MovieScheduleDao();
        MovieScheduleEntity entity = new MovieScheduleEntity();

        // Get parameters
        String id = request.getParameter("id");
        String movieId = request.getParameter("movieId");
        String theaterId = request.getParameter("theaterId");
        String auditoriumId = request.getParameter("auditoriumId");
        String price = request.getParameter("price");
        String dateOfShow = request.getParameter("dateOfShow");
        String timeOfShow = request.getParameter("timeOfShow");

        // Query movie
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();
        movieEntity.setId(Integer.parseInt(movieId));
        movieEntity = movieDao.queryById(movieEntity);

        // Query theater
        TheaterEntity theaterEntity = new TheaterEntity();
        TheaterDao theaterDao = new TheaterDao();
        theaterEntity.setId(Integer.parseInt(theaterId));
        theaterEntity = theaterDao.queryById(theaterEntity);

        // Query auditorium
        AuditoriumEntity auditoriumEntity = new AuditoriumEntity();
        AuditoriumDao auditoriumDao = new AuditoriumDao();
        auditoriumEntity.setId(Integer.parseInt(auditoriumId));
        auditoriumEntity = auditoriumDao.queryById(auditoriumEntity);

        if (movieEntity != null && theaterEntity != null && auditoriumEntity != null) {
            entity.setId(Integer.parseInt(id));
            entity.setMovieId(Integer.parseInt(movieId));
            entity.setMovieTitle(movieEntity.getTitle());
            entity.setAuditoriumTheaterId(theaterEntity.getId());
            entity.setTheaterName(theaterEntity.getName());
            entity.setLocation(theaterEntity.getLocation());
            entity.setAuditoriumId(auditoriumEntity.getId());
            entity.setAuditoriumName(auditoriumEntity.getName());
            entity.setPrice(Float.parseFloat(price));
            entity.setShowtime(Timestamp.valueOf(dateOfShow + " " + timeOfShow));

            int status = dao.update(entity);
            if (status == 1) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('" + getClass() + " update: success.');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('" + getClass() + " update: failure.');window.history.go(-1);</script>");
                out.flush();
                out.close();
            }
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + getClass() + " delete: Still in construction.');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + getClass() + " query: Still in construction.');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Open a new tab and keep opened page stay
        PrintWriter out = response.getWriter();
        out.println("<script>window.open('/movieScheduleList.jsp');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");

        MovieScheduleDao dao = new MovieScheduleDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<MovieScheduleEntity> entities = dao.getAll();
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String json = gson.toJson(entities);
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
}
