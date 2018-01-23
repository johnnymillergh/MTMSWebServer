package servlet.customer;

import dao.UserDao;
import entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUp")
public class SignUp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        UserDao dao = new UserDao();
        UserEntity entityInput = new UserEntity();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        int status = -1;
        if (email.compareTo("") != 0 && password.compareTo("") != 0 && username.compareTo("") != 0) {
            entityInput.setEmail(email);
            entityInput.setPassword(password);
            entityInput.setUsername(username);
            status = dao.save(entityInput);
        }
        if (status == 1) {
            PrintWriter out = response.getWriter();
            out.println("{\"signUpStatus\":\"success\"}");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("{\"signUpStatus\":\"failure\"}");
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
