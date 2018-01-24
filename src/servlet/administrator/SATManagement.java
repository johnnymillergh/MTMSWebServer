package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.AuditoriumDao;
import dao.SeatDao;
import entity.AuditoriumEntity;
import entity.SeatEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "SATManagement")
public class SATManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("satOperation");
        System.out.println("satOperation: " + radio);
        switch (radio) {
            case "getAllTheater":
                try {
                    getAllTheater(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getAllAuditorium":
                try {
                    getAllAuditorium(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getAllSeatOfAuditorium":
                try {
                    getAllSeatOfAuditorium(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getSeatOfAuditoriumJson":
                try {
                    getSeatOfAuditoriumJson(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "setSeatAvailability":
                try {
                    setSeatAvailability(request, response);
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

    private void getAllTheater(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<script>window.open('/theaterList.jsp');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getAllAuditorium(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<script>window.open('/auditoriumList.jsp');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getAllSeatOfAuditorium(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String auditoriumId = request.getParameter("auditoriumId");
        out.write("<script>window.open('/seatList.jsp?auditoriumId=" + auditoriumId + "');window.history.go(-1);</script>");
        out.flush();
        out.close();
    }

    private void getSeatOfAuditoriumJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");
        String auditoriumId = request.getParameter("auditoriumId");

        SeatDao dao = new SeatDao();
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setAuditoriumId(Integer.parseInt(auditoriumId));

        List<SeatEntity> seats = dao.getSeatOfAuditorium(seatEntity);

        if (seats != null) {
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();

            PrintWriter out = response.getWriter();
            String json = gson.toJson(seats);
            out.println(json);
            out.flush();
            System.out.println("getJson: " + getClass());
            out.close();
        }
    }

    private void setSeatAvailability(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String auditoriumId = request.getParameter("auditoriumId");
        String seatAvailability = request.getParameter("seatAvailability");

        if (seatAvailability.compareTo("NotSelected") == 0) {
            SeatDao dao = new SeatDao();
            SeatEntity seatEntity = new SeatEntity();
            seatEntity.setAuditoriumId(Integer.parseInt(auditoriumId));
            seatEntity.setIsSelected(false);
            seatEntity.setUserId(101);
            seatEntity.setUserEmail("NULL");
            int status = dao.updateByAuditoriumId(seatEntity);
            if (status > 0) {
                PrintWriter out = response.getWriter();
                out.write("<script>alert('Manipulated row: " + status + "');window.history.go(-1);</script>");
                out.flush();
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.write("<script>alert('Manipulated row: " + status + "');window.history.go(-1);</script>");
                out.flush();
                out.close();
            }
        } else if (seatAvailability.compareTo("Selected") == 0) {

        }
    }
}
