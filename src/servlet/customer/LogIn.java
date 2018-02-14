package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.UserDao;
import entity.MessageEntity;
import entity.UserEntity;
import util.MobileTerminalUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("Duplicates")
@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        UserDao dao = new UserDao();
        UserEntity entityInput = new UserEntity();

        // Get parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null) {
            email = "";
        }
        if (password == null) {
            password = "";
        }

        entityInput.setEmail(email);
        entityInput.setPassword(password);
        UserEntity entityFromQuery = dao.queryByEmail(entityInput);

        System.out.println(getClass() + " Remote client is trying to log in: [" + request.getRemoteAddr() + ":" + request.getRemotePort() + "]");

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        if (entityFromQuery != null) {
            if (entityFromQuery.getPassword().compareTo(entityInput.getPassword()) == 0) {
                MessageEntity messageEntity = new MessageEntity();
                messageEntity.setSourcePort(MobileTerminalUtil.serverIpPortInfo.getSourcePort());
                messageEntity.setMessage("loginStatus:success");

                PrintWriter out = response.getWriter();
                out.println(gson.toJson(messageEntity));
                out.flush();
                out.close();
                MobileTerminalUtil.userEmail2Ip.put(entityFromQuery.getEmail(), request.getRemoteAddr());
            } else {
                MessageEntity messageEntity = new MessageEntity();
                messageEntity.setMessage("loginStatus:failure");

                PrintWriter out = response.getWriter();
                out.println(gson.toJson(messageEntity));
                out.flush();
                out.close();
            }
        } else {
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setMessage("loginStatus:failure");

            PrintWriter out = response.getWriter();
            out.println(gson.toJson(messageEntity));
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
