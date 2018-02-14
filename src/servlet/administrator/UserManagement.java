package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UserDao;
import entity.MessageEntity;
import entity.UserEntity;
import util.FileUtil;
import util.MobileTerminalUtil;

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
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "UserManagement")
@MultipartConfig
public class UserManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("userOperation");
        System.out.println("userOperation: " + radio);
        switch (radio) {
            case "add":
                try {
                    add(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "secondaryLogon":
                try {
                    secondaryLogon(request, response);
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
            case "getOnline":
                try {
                    getOnlineUser(request, response);
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
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();

        // Get Parameter
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String homeLocation = request.getParameter("homeLocation");

        // Get part parameter
        Part part = request.getPart("avatar");
        // Get file name
        String contentDisposition = part.getHeader("Content-Disposition");
        System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
        // Save file to work directory
        String savePath = FileUtil.getPictureSavingPath();
        int filenameIndex = contentDisposition.indexOf("filename=");
        String filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
        filename = FileUtil.getRealName(filename);
        part.write(savePath + "/" + filename);
        System.out.println("Saving file, path: " + savePath + "/" + filename);
        // Get file length
        File file = new File(savePath + "/" + filename);
        Long fileLength = file.length();
        System.out.println("fileLength: " + fileLength + " bytes");
        byte[] bytes = new byte[fileLength.intValue()];
        // Read file to memory
        FileInputStream in = new FileInputStream(file);
        in.read(bytes);
        in.close();

        // Task of checking the validations of parameter is assigned to web browser

        // Set Entity and save
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setAvatar(bytes);
        user.setHomeLocation(homeLocation);
        int status = userDao.save(user);

        if (status > 0) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " add: Success.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " add: Failure.');window.history.go(-1);</script>");
            out.flush();
            out.close();
        }
    }

    private void secondaryLogon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();

        // Get Parameter
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int port = Integer.parseInt(request.getParameter("port"));

        user.setEmail(email);
        user.setPassword(password);
        user = userDao.queryByEmailAndPassword(user);
        if (user != null) {
            String ip = request.getRemoteAddr();
            MobileTerminalUtil.userEmail2Ip.put(email, ip);
            MobileTerminalUtil.ip2RemotePort.put(ip, port);
            System.out.println("secondaryLogon: Saving user's IP and port [" + ip + ":" + port + "]");

            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setMessage("secondaryLogonStatus:success");
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String json = gson.toJson(messageEntity);

            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        } else {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setMessage("secondaryLogonStatus:failure");
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String json = gson.toJson(messageEntity);

            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();

        // Get Parameter
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String homeLocation = request.getParameter("homeLocation");

        // Get part parameter
        Part part = request.getPart("avatar");
        // Get file name
        String contentDisposition = part.getHeader("Content-Disposition");
        System.out.println("Poster picture: " + contentDisposition);// form-data; name="file"; filename="UserManagement.sql"
        // Save file to work directory
        String savePath = FileUtil.getPictureSavingPath();
        int filenameIndex = contentDisposition.indexOf("filename=");
        String filename = contentDisposition.substring(filenameIndex + 10, contentDisposition.length() - 1);
        filename = FileUtil.getRealName(filename);
        part.write(savePath + "/" + filename);
        System.out.println("Saving file, path: " + savePath + "/" + filename);
        // Get file length
        File file = new File(savePath + "/" + filename);
        Long fileLength = file.length();
        System.out.println("fileLength: " + fileLength + " bytes");
        byte[] bytes = new byte[fileLength.intValue()];
        // Read file to memory
        FileInputStream in = new FileInputStream(file);
        in.read(bytes);
        in.close();

        // Task of checking the validations of parameter is assigned to web browser

        // Set Entity and save
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setAvatar(bytes);
        user.setHomeLocation(homeLocation);
        int status = userDao.update(user);

        if (status > 0) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " update: Success.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " update: Failure.');window.history.go(-1);</script>");
            out.flush();
            out.close();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();

        // Get Parameter
        String email = request.getParameter("email");

        // Task of checking the validations of parameter is assigned to web browser

        // Delete by email
        user.setEmail(email);
        int status = userDao.delete(user);
        if (status == 1) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " delete: Success.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " delete: Failure.');window.history.go(-1);</script>");
            out.flush();
            out.close();
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();

        // Get Parameter
        String email = request.getParameter("email");

        // Query by email
        user.setEmail(email);
        user = userDao.queryByEmail(user);

        if (user != null) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " query: User found.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " query: User not found.');window.history.go(-1);</script>");
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        out.println("<script>window.open('/userList.jsp');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getOnlineUser(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");

        UserDao dao = new UserDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        String email = request.getParameter("email");

        if (email.compareTo("") != 0) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(email);
            userEntity = dao.queryByEmail(userEntity);
            if (userEntity != null) {
                PrintWriter out = response.getWriter();
                String json = gson.toJson(userEntity);
                out.println(json);
                out.flush();
                System.out.println("getJson: " + getClass());
                out.close();
                return;
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('" + getClass() + " getJson: ERROR: User not found');window.history.go(-1);</script>");
                out.flush();
                out.close();
                return;
            }
        }

        List<UserEntity> entities = dao.getAll();
        PrintWriter out = response.getWriter();
        String json = gson.toJson(entities);
        out.println(json);
        out.flush();
        System.out.println("getJson: " + getClass());
        out.close();
    }
}
