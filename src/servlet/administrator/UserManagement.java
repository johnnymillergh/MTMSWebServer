package servlet.administrator;

import dao.UserDao;
import entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("Duplicates")
@WebServlet(name = "UserManagement")
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

        // Task of checking the validations of parameter is assigned to web browser

        // Set Entity and save
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        int status = userDao.save(user);

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
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();

        // Get Parameter
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Task of checking the validations of parameter is assigned to web browser

        // Set Entity and save
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        int status = userDao.update(user);

        if (status > 0) {
            // Go to administrator.jsp
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " update: Success.');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " update: Failure.');window.history.go(-1)</script>");
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
            out.println("<script>alert('" + getClass() + " delete: Failure.');window.history.go(-1)</script>");
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
            out.println("<script>alert('" + getClass() + " query: User not found.');;window.history.go(-1)</script>");
            out.flush();
            out.close();
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getOnlineUser(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) {
    }
}
