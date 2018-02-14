package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.MessageEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

@WebServlet(name = "PushMessage")
public class PushMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String ip = request.getParameter("ip");
        int port = Integer.parseInt(request.getParameter("port"));
        String message = request.getParameter("message");
        System.out.println("Push message to [" + ip + ":" + port + "], message: " + message);

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String json = gson.toJson(messageEntity);

        Socket socket = new Socket(ip, port);
        OutputStream os = socket.getOutputStream();
        PrintWriter out = new PrintWriter(os);
        out.write(json);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
