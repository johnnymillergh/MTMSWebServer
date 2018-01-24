package servlet.administrator;

import dao.MovieDao;
import entity.MovieEntity;
import util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("Duplicates")
@WebServlet(name = "MovieManagement")
@MultipartConfig
public class MovieManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("movieOperation");
        System.out.println("movieOperation: " + radio);
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
            default:
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();
        // Get parameter
        String title = request.getParameter("title");
        String duration = request.getParameter("duration");
        String genre = request.getParameter("genre");
        String director = request.getParameter("director");
        String stars = request.getParameter("stars");
        String country = request.getParameter("country");
        String language = request.getParameter("language");
        String releaseDate = request.getParameter("releaseDate");
        String filmingLocation = request.getParameter("filmingLocation");
        String runtime = request.getParameter("runtime");
        String aspectRatio = request.getParameter("aspectRatio");
        String description = request.getParameter("description");
        String savePath = null;
        String filename = null;
        File file = null;
        byte[] bytes = new byte[0];

        // Get part parameter
        Part part = request.getPart("poster");
        // Get file name
        String contentDisposition = part.getHeader("Content-Disposition");
        System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
        // Save file to work directory
        savePath = FileUtil.getPictureSavingPath();
        int filenameIndex = contentDisposition.indexOf("filename=");
        filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
        filename = FileUtil.getRealName(filename);
        part.write(savePath + "/" + filename);
        System.out.println("Saving file, path: " + savePath + "/" + filename);
        // Get file length
        file = new File(savePath + "/" + filename);
        Long fileLength = file.length();
        System.out.println("fileLength: " + fileLength + " bytes");
        bytes = new byte[fileLength.intValue()];
        // Read file to memory
        FileInputStream in = new FileInputStream(file);
        in.read(bytes);
        in.close();

        // Set entity and save
        movieEntity.setTitle(title);
        movieEntity.setDuration(duration);
        movieEntity.setGenre(genre);
        movieEntity.setDirector(director);
        movieEntity.setStars(stars);
        movieEntity.setCountry(country);
        movieEntity.setLanguage(language);
        movieEntity.setReleaseDate(releaseDate);
        movieEntity.setFilmingLocation(filmingLocation);
        movieEntity.setRuntime(runtime);
        movieEntity.setAspectRatio(aspectRatio);
        movieEntity.setDescription(description);
        movieEntity.setPoster(bytes);
        int status = movieDao.save(movieEntity);

        // Delete file
        file.delete();

        if (status > 0) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " add: Success.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " add: Failure.');window.history.go(-1)</script>");
            out.flush();
            out.close();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();
        File file = null;
        byte[] bytes = new byte[0];

        // Get parameter
        Part part = request.getPart("poster");
        // Get file name
        String contentDisposition = part.getHeader("Content-Disposition");
        System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
        // Save file to work directory
        String savePath = FileUtil.getPictureSavingPath();
        int filenameIndex = contentDisposition.indexOf("filename=");
        String filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
        part.write(savePath + "/" + filename);
        System.out.println("Saving file, path: " + savePath + "/" + filename);
        // Get file length
        file = new File(savePath + "/" + filename);
        Long fileLength = file.length();
        bytes = new byte[fileLength.intValue()];
        // Read file to memory
        FileInputStream in = new FileInputStream(file);
        in.read(bytes);
        in.close();

        // Set entity
        movieEntity.setTitle(request.getParameter("title"));
        movieEntity.setDuration(request.getParameter("duration"));
        movieEntity.setGenre(request.getParameter("genre"));
        movieEntity.setDirector(request.getParameter("director"));
        movieEntity.setStars(request.getParameter("stars"));
        movieEntity.setCountry(request.getParameter("country"));
        movieEntity.setLanguage(request.getParameter("language"));
        movieEntity.setReleaseDate(request.getParameter("releaseDate"));
        movieEntity.setFilmingLocation(request.getParameter("filmingLocation"));
        movieEntity.setRuntime(request.getParameter("runtime"));
        movieEntity.setAspectRatio(request.getParameter("aspectRatio"));
        movieEntity.setDescription(request.getParameter("description"));
        movieEntity.setPoster(bytes);

        // Update
        int status = movieDao.update(movieEntity);
        file.delete();

        if (status > 0) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " update: Success.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " update: Failure.');window.history.go(-1)</script>");
            out.flush();
            out.close();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + getClass() + " delete: Still in construction.');window.history.go(-1)</script>");
        out.flush();
        out.close();
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();

        String title = request.getParameter("title");

        movieEntity.setTitle(title);
        movieEntity = movieDao.queryByTitle(movieEntity);

        if (movieEntity != null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " query: Movie found.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " query: Movie not found.');window.history.go(-1)</script>");
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Open a new tab and keep opened page stay
        PrintWriter out = response.getWriter();
        out.println("<script>window.open(\"\\movieList.jsp\");window.history.go(-1)</script>");
        out.flush();
        out.close();
    }
}
