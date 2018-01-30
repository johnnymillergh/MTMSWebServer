package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovieDao;
import dao.TopMovieDao;
import entity.MovieEntity;
import entity.TopMovieEntity;
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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "TopMovieManagement")
@MultipartConfig
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

        Part part;
        String contentDisposition;
        String savePath = FileUtil.getPictureSavingPath();
        int filenameIndex;
        String filename;
        File file;
        Long fileLength;
        byte[] bytes;
        ArrayList<byte[]> bytesList = new ArrayList<>();
        FileInputStream in;
        for (int i = 0; i < 5; i++) {
            // Get part parameter
            part = request.getPart("poster" + (i + 1));
            // Get file name
            contentDisposition = part.getHeader("Content-Disposition");
            System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
            filenameIndex = contentDisposition.indexOf("filename=");
            filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
            filename = FileUtil.getRealName(filename);
            // Save file to work directory
            part.write(savePath + "/" + filename);
            System.out.println("Saving file, path: " + savePath + "/" + filename);
            file = new File(savePath + "/" + filename);
            // Get file length
            fileLength = file.length();
            System.out.println("fileLength: " + fileLength + " bytes");
            // Read file to memory
            bytes = new byte[fileLength.intValue()];
            in = new FileInputStream(file);
            in.read(bytes);
            in.close();
            bytesList.add(bytes);
        }

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
                topMovieEntity.setPoster(bytesList.get(i));
                topMovies.add(topMovieEntity);
            }
        }

        if (topMovies.size() != 5) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: fail to add.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            int status = 0;
            for (TopMovieEntity entity : topMovies) {
                status = topMovieDao.save(entity);
            }
            if (status > 0) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('TopMovieManagement: added.');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('TopMovieManagement: fail to add.');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            }
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

        Part part;
        String contentDisposition;
        String savePath = FileUtil.getPictureSavingPath();
        int filenameIndex;
        String filename;
        File file;
        Long fileLength;
        byte[] bytes;
        ArrayList<byte[]> bytesList = new ArrayList<>();
        FileInputStream in;
        for (int i = 0; i < 5; i++) {
            // Get part parameter
            part = request.getPart("poster" + (i + 1));
            // Get file name
            contentDisposition = part.getHeader("Content-Disposition");
            System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
            filenameIndex = contentDisposition.indexOf("filename=");
            filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
            filename = FileUtil.getRealName(filename);
            // Save file to work directory
            part.write(savePath + "/" + filename);
            System.out.println("Saving file, path: " + savePath + "/" + filename);
            file = new File(savePath + "/" + filename);
            // Get file length
            fileLength = file.length();
            System.out.println("fileLength: " + fileLength + " bytes");
            // Read file to memory
            bytes = new byte[fileLength.intValue()];
            in = new FileInputStream(file);
            in.read(bytes);
            in.close();
            bytesList.add(bytes);
        }

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
                topMovieEntity.setPoster(bytesList.get(i));
                topMovies.add(topMovieEntity);
            }
        }

        if (topMovies.size() != 5) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('TopMovieManagement: fail to update.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            int status = 0;
            for (TopMovieEntity entity : topMovies) {
                status = topMovieDao.update(entity);
            }

            if (status > 0) {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('TopMovieManagement: updated.');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('TopMovieManagement: fail to update.');window.location.href='/administrator.jsp'</script>");
                out.flush();
                out.close();
            }
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

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        out.println("<script>window.open('/topMovieList.jsp');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");

        TopMovieDao dao = new TopMovieDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<TopMovieEntity> topMovieList = dao.getAll();

        PrintWriter out = response.getWriter();
        String json = gson.toJson(topMovieList);
        out.println(json);
        out.flush();
        System.out.println("getJson: " + getClass());
        out.close();
    }
}
