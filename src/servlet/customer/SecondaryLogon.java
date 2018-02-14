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
@WebServlet(name = "SecondaryLogon")
public class SecondaryLogon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
