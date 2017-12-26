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
@WebServlet(name = "User")
public class User extends HttpServlet {
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
            default:
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = new UserEntity();
        UserDao userDao = new UserDao();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        // Get Parameter
        String email = request.getParameter("emailText");
        String username = request.getParameter("usernameText");
        String password = request.getParameter("passwordText");

        // Set Entity and save
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        userDao.save(user);
        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('User added.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('User updateed.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('User deleted.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Go to administrator.jsp
        PrintWriter out = response.getWriter();
        out.println("<script>alert('User query.');window.location.href='/administrator.jsp'</script>");
        out.flush();
        out.close();
    }
}
