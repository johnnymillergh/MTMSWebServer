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
        UserDao dao = new UserDao();
        UserEntity entityInput = new UserEntity();
        entityInput.setEmail(request.getParameter("email"));
        entityInput.setPassword(request.getParameter("password"));
        entityInput.setUsername(request.getParameter("username"));
        int status = dao.save(entityInput);
        if (status == 1) {
            PrintWriter out = response.getWriter();
            out.println("{\"signUpStatus\":\"succeed\"}");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("{\"signUpStatus\":\"failed\"}");
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
