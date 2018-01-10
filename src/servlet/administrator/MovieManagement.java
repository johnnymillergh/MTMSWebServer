package servlet.administrator;

import dao.MovieDao;
import entity.MovieEntity;
import util.FileUtils;

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
import java.nio.file.Path;

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
        System.out.println(title + "\n" + duration + "\n" + genre + "\n" + director + "\n" + stars + "\n" + country +
                "\n" + language + "\n" + releaseDate + "\n" + filmingLocation + "\n" + runtime + "\n" +
                aspectRatio + "\n" + description);
        //特殊参数用part取
        Part part = request.getPart("poster");
        //获取文件名
        String contentDisposition = part.getHeader("Content-Disposition");
        System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
        //
        String savePath = FileUtils.convertBackslash2Slash(FileUtils.pictureDirectory.getPath());
        int filenameIndex = contentDisposition.indexOf("filename=");
        String filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
        filename = FileUtils.getRealName(filename);
        System.out.println("savePath: " + savePath + "/" + filename);
        part.write(savePath + "/" + filename);
        // Read uploaded file.
        File file = new File(savePath + "/" + filename);
        Long fileLength = file.length();
        System.out.println("fileLength: " + fileLength + " bytes");
        byte[] bytes = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File path: " + savePath + "/" + filename);

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
        movieDao.save(movieEntity);

        // Delete file
        file.delete();

        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('MovieManagement added.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieEntity movieEntity = new MovieEntity();
        MovieDao movieDao = new MovieDao();

        // Get parameter
        Part part = request.getPart("poster");
        //获取文件名
        String contentDisposition = part.getHeader("Content-Disposition");
        System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
        String savePath = "D:/MTMS/upload/pic";
        int filenameIndex = contentDisposition.indexOf("filename=");
        String filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
        part.write(savePath + "/" + filename);
        System.out.println("File path: " + savePath + "/" + filename);
        File file = new File(savePath + "/" + filename);
        Long fileLength = file.length();
        byte[] bytes = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        movieDao.update(movieEntity);
        file.delete();

        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('MovieManagement updated.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('MovieManagement deleted.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();
        String title = request.getParameter("title");
        if (title.compareTo("") != 0) {
            movieEntity.setTitle(title);
            movieEntity = movieDao.queryByTitle(movieEntity);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('MovieManagement query: " + movieEntity.getLanguage() + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('MovieManagement query.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/movieList.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
