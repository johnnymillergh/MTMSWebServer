package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovieDao;
import dao.TopMovieDao;
import entity.MovieEntity;
import entity.TopMovieEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.add;

@SuppressWarnings("Duplicates")
@WebServlet(name = "TopMovieManagement")
public class TopMovieManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("topMovieOperation");
        System.out.println("topMovieOperation: " + radio);
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
        List<TopMovieEntity> topMovies = new ArrayList<>();
        TopMovieDao topMovieDao = new TopMovieDao();
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();

        List<String> titles = new ArrayList<>();
        titles.add(request.getParameter("title1"));
        titles.add(request.getParameter("title2"));
        titles.add(request.getParameter("title3"));
        titles.add(request.getParameter("title4"));
        titles.add(request.getParameter("title5"));

        for (int i = 0; i < 5; i++) {
            movieEntity.setTitle(titles.get(i));
            movieEntity = movieDao.queryByTitle(movieEntity);
            if (movieEntity == null) {
                break;
            } else {
                TopMovieEntity topMovieEntity = new TopMovieEntity();
                topMovieEntity.setId(i + 1);
                topMovieEntity.setMovieId(movieEntity.getId());
                topMovieEntity.setMovieTitle(movieEntity.getTitle());
                topMovies.add(topMovieEntity);
            }
        }

        if (topMovies.size() != 5) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: fail to add.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            for (TopMovieEntity entity : topMovies) {
                topMovieDao.save(entity);
            }

            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: added.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<TopMovieEntity> topMovies = new ArrayList<>();
        TopMovieDao topMovieDao = new TopMovieDao();
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();

        List<String> titles = new ArrayList<>();
        titles.add(request.getParameter("title1"));
        titles.add(request.getParameter("title2"));
        titles.add(request.getParameter("title3"));
        titles.add(request.getParameter("title4"));
        titles.add(request.getParameter("title5"));

        for (int i = 0; i < 5; i++) {
            movieEntity.setTitle(titles.get(i));
            movieEntity = movieDao.queryByTitle(movieEntity);
            if (movieEntity == null) {
                break;
            } else {
                TopMovieEntity topMovieEntity = new TopMovieEntity();
                topMovieEntity.setId(i + 1);
                topMovieEntity.setMovieId(movieEntity.getId());
                topMovieEntity.setMovieTitle(movieEntity.getTitle());
                topMovies.add(topMovieEntity);
            }
        }

        if (topMovies.size() != 5) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: fail to update.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            for (TopMovieEntity entity : topMovies) {
                topMovieDao.update(entity);
            }

            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: updated.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TopMovieDao topMovieDao = new TopMovieDao();
        List<TopMovieEntity> topMovieList = topMovieDao.getAll();
        List<Integer> ids = new ArrayList<>();
        if (topMovieList != null) {
            int id;
            for (TopMovieEntity topMovie : topMovieList) {
                topMovieDao.delete(topMovie);
                id = topMovie.getId();
                ids.add(id);
            }

            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: deleted(id): " + ids.toString() + ".');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: Top movie table has no records.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) {
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
}
