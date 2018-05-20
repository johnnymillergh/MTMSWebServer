package servlet.customer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.*;
import entity.*;
import util.SeatIdUtil;
import util.SeatLocationUtil;
import util.TimestampUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
@WebServlet(name = "Order")
public class Order extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String radio = request.getParameter("orderOperation");
        System.out.println("orderOperation: " + radio);
        switch (radio) {
            case "takeOrder":
                try {
                    takeOrder(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "pay":
                try {
                    pay(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "use":
                try {
                    use(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getJson":
                try {
                    getJson(request, response);
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

    private void takeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        MovieScheduleDao movieScheduleDao = new MovieScheduleDao();
        MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();

        MovieDao movieDao = new MovieDao();
        MovieEntity movieEntity = new MovieEntity();

        SeatDao seatDao = new SeatDao();
        SeatEntity seatEntity = new SeatEntity();

        AuditoriumDao auditoriumDao = new AuditoriumDao();
        AuditoriumEntity auditoriumEntity = new AuditoriumEntity();

        TheaterDao theaterDao = new TheaterDao();
        TheaterEntity theaterEntity = new TheaterEntity();

        // Get Parameters:
        // email, movie schedule id, ticket amount, theater id, auditorium id, seat's row #, sear's col #
        String email = request.getParameter("email");
        int movieScheduleId = Integer.parseInt(request.getParameter("movieScheduleId"));
        int ticketAmount = Integer.parseInt(request.getParameter("ticketAmount"));
        int seatId = Integer.parseInt(request.getParameter("seatId"));

        Timestamp currentTimestamp = TimestampUtil.getTimestampStringAbortMillisecond();

        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);

        movieScheduleEntity.setId(movieScheduleId);
        movieScheduleEntity = movieScheduleDao.queryById(movieScheduleEntity);

        movieEntity.setId(movieScheduleEntity.getMovieId());
        movieEntity = movieDao.queryById(movieEntity);

        seatEntity.setId(seatId);
        seatEntity = seatDao.queryById(seatEntity);
        // TODO: Update table seat: is_selected, user_id, user_email, order_datetime
        seatEntity.setIsSelected(true);
        seatEntity.setUserId(userEntity.getId());
        seatEntity.setUserEmail(userEntity.getEmail());
        seatEntity.setOrderDatetime(currentTimestamp);
        int status1 = seatDao.updateById(seatEntity);

        auditoriumEntity.setId(movieScheduleEntity.getAuditoriumId());
        auditoriumEntity = auditoriumDao.queryById(auditoriumEntity);

        theaterEntity.setId(auditoriumEntity.getTheaterId());
        theaterEntity = theaterDao.queryById(theaterEntity);

        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
        customerOrderEntity.setUserId(userEntity.getId());
        customerOrderEntity.setOrderDatetime(currentTimestamp);

        customerOrderEntity.setMovieScheduleId(movieScheduleEntity.getId());
        customerOrderEntity.setMovieTitle(movieEntity.getTitle());
        customerOrderEntity.setShowtime(movieScheduleEntity.getShowtime());
        // TODO: SAT info should be set
        // customer can only order one ticket
        ArrayList<Integer> seatIds = new ArrayList<>();
        seatIds.add(seatEntity.getId());
        ArrayList<SeatLocationUtil.SeatLocation> seatLocations = new ArrayList<>();
        seatLocations.add(new SeatLocationUtil.SeatLocation(seatEntity.getRowNumber(), seatEntity.getColNumber()));
        customerOrderEntity.setSeatId(SeatIdUtil.generate(seatIds));
        customerOrderEntity.setSeatLocation(SeatLocationUtil.generate(seatLocations));
        customerOrderEntity.setAuditoriumName(auditoriumEntity.getName());
        customerOrderEntity.setTheaterName(theaterEntity.getName());
        customerOrderEntity.setTheaterLocation(theaterEntity.getLocation());
        customerOrderEntity.setIsPaid(false);
        customerOrderEntity.setIsUsed(false);
        customerOrderEntity.setTicketAmount(ticketAmount);
        customerOrderEntity.setTotalPrice(ticketAmount * movieScheduleEntity.getPrice());
        int status2 = customerOrderDao.save(customerOrderEntity);
        if (status2 > 0) {
            customerOrderEntity = customerOrderDao.queryByUserIdAndOrderDatetime(customerOrderEntity);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String json = gson.toJson(customerOrderEntity);
            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
        } else {
            response.sendError(404, "Fail to take order");
        }
    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();

        // Get parameters
        String email = request.getParameter("email");
        String orderDatetime = request.getParameter("orderDatetime");
        String paymentPassword = request.getParameter("paymentPassword");

        System.out.println(email + ", " + orderDatetime + ", " + paymentPassword);

        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);

        customerOrderEntity.setUserId(userEntity.getId());
        customerOrderEntity.setOrderDatetime(Timestamp.valueOf(orderDatetime));
        customerOrderEntity.setIsPaid(true);
        customerOrderEntity.setPaymentDatetime(new Timestamp(System.currentTimeMillis()));

        if (!paymentPassword.equals(userEntity.getPaymentPassword())) {
            response.sendError(700, "Payment password error");
            return;
        }

        int status = customerOrderDao.updatePayment(customerOrderEntity);
        if (status > 0) {
            customerOrderEntity = customerOrderDao.queryByUserIdAndOrderDatetime(customerOrderEntity);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            String json = gson.toJson(customerOrderEntity);
            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
        } else {
            response.sendError(701, "Fail to pay");
        }
    }

    private void use(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();

        // Get parameters
        String id = request.getParameter("userId");
        String orderDatetime = request.getParameter("orderDatetime");

        userEntity.setId(Integer.parseInt(id));
        userEntity = userDao.queryById(userEntity);

        customerOrderEntity.setUserId(userEntity.getId());
        customerOrderEntity.setOrderDatetime(Timestamp.valueOf(orderDatetime));
        customerOrderEntity.setIsUsed(true);
        customerOrderEntity.setUsedDatetime(new Timestamp(System.currentTimeMillis()));

        int status = customerOrderDao.updateUsed(customerOrderEntity);
        if (status > 0) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: success." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: failure." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();

        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        String email = request.getParameter("email");

        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);

        customerOrderEntity.setUserId(userEntity.getId());

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<CustomerOrderEntity> orders = customerOrderDao.getAllByUserId(customerOrderEntity);

        PrintWriter out = response.getWriter();
        String json = gson.toJson(orders);
        out.println(json);
        out.flush();
        out.close();
        System.out.println("getJson: " + getClass());
    }
}
