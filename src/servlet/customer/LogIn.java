package servlet.customer;

import dao.UserDao;
import entity.UserEntity;
import util.MobileTerminalUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        UserDao dao = new UserDao();
        UserEntity entityInput = new UserEntity();
        entityInput.setEmail(request.getParameter("email"));
        entityInput.setPassword(request.getParameter("password"));
        UserEntity entityFromQuery = dao.queryByEmail(entityInput);

        System.out.println("Remote client is trying to log in: [" + request.getRemoteAddr() + ":" + request.getRemotePort() + "]");

        if (entityFromQuery != null) {
            if (entityFromQuery.getPassword().compareTo(entityInput.getPassword()) == 0) {
                PrintWriter out = response.getWriter();
                out.println("{\"loginStatus\":\"succeed\",\"serverPushPort\":\"" +
                        MobileTerminalUtils.serverSocket.getLocalPort() + "\"}");
                out.flush();
                out.close();
                MobileTerminalUtils.userEmail2Ip.put(entityFromQuery.getEmail(), request.getRemoteAddr());
            } else {
                PrintWriter out = response.getWriter();
                out.println("{\"loginStatus\":\"succeed\",\"serverPushPort\":\"\"}");
                out.flush();
                out.close();
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("{\"loginStatus\":\"succeed\",\"serverPushPort\":\"\"}");
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
