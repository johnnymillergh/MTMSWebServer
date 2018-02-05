package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UserDao;
import entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetUserInfo")
public class GetUserInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao dao = new UserDao();
        UserEntity entity = new UserEntity();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        entity.setEmail(email);
        entity.setPassword(password);
        entity = dao.queryByEmailAndPassword(entity);
        if (entity != null) {
            PrintWriter out = response.getWriter();
            String json = gson.toJson(entity);
            out.write(json);
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.write("null");
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
