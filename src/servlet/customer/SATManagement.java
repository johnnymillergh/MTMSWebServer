package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.SeatDao;
import dao.UserDao;
import entity.SeatEntity;
import entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "SATManagement")
public class SATManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");

        String radio = request.getParameter("satOperation");
        System.out.println("satOperation: " + radio);
        switch (radio) {
            case "getSeatOfAuditorium":
                try {
                    getSeatOfAuditorium(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "selectSeat":
                try {
                    selectSeat(request, response);
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

    private void getSeatOfAuditorium(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SeatDao seatDao = new SeatDao();
        SeatEntity seatEntity = new SeatEntity();

        // Get parameters
        int auditoriumId = Integer.parseInt(request.getParameter("auditoriumId"));

        seatEntity.setAuditoriumId(auditoriumId);
        List<SeatEntity> seats = seatDao.getSeatOfAuditorium(seatEntity);

        PrintWriter out = response.getWriter();
        if (seats != null) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String json = gson.toJson(seats);
            out.write(json);
            out.flush();
            out.close();
        } else {
            out.write("null");
            out.flush();
            out.close();
        }
    }

    private void selectSeat(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SeatDao seatDao = new SeatDao();
        SeatEntity seatEntity = new SeatEntity();
        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        // Get parameters
        int seatId = Integer.parseInt(request.getParameter("seatId"));
        String email = request.getParameter("email");

        seatEntity.setId(seatId);
        userEntity.setEmail(email);

        seatEntity = seatDao.queryById(seatEntity);
        userEntity = userDao.queryByEmail(userEntity);

        PrintWriter out = response.getWriter();
        if (seatEntity != null && userEntity != null) {
            if (!seatEntity.getIsSelected()) {
                System.out.println(userEntity.toString() + " IS CHOOSING " + seatEntity.toString());

                seatEntity.setIsSelected(true);
                seatEntity.setUserId(userEntity.getId());
                seatEntity.setUserEmail(userEntity.getEmail());
                seatEntity.setOrderDatetime(new Timestamp(System.currentTimeMillis()));

                int status = seatDao.updateById(seatEntity);

                if (status > 0) {
                    out.write("Seat Operation: success");
                    out.flush();
                    out.close();
                } else {
                    out.write("Seat Operation: failure");
                    out.flush();
                    out.close();
                }
            } else {
                out.write("Seat Operation: failure");
                out.flush();
                out.close();
            }
        } else {
            out.write("Seat Operation: failure");
            out.flush();
            out.close();
        }
    }
}
