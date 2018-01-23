package servlet.administrator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.MovieScheduleDao;
import dao.CustomerOrderDao;
import dao.UserDao;
import entity.MovieScheduleEntity;
import entity.CustomerOrderEntity;
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

@SuppressWarnings("Duplicates")
@WebServlet(name = "OrderManagement")
public class OrderManagement extends HttpServlet {
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
            case "delete":
                try {
                    delete(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "getAll":
                try {
                    getAll(request, response);
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

    private void takeOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();

        MovieScheduleDao movieScheduleDao = new MovieScheduleDao();
        MovieScheduleEntity movieScheduleEntity = new MovieScheduleEntity();

        // Get parameters
        String email = request.getParameter("email");
        int movieScheduleId = Integer.parseInt(request.getParameter("movieScheduleId"));
        int ticketAmount = Integer.parseInt(request.getParameter("ticketAmount"));

        if (email.compareTo("") == 0) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " takeOrder: Parameter ERROR." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);
        movieScheduleEntity.setId(movieScheduleId);
        movieScheduleEntity = movieScheduleDao.queryById(movieScheduleEntity);
        if (userEntity == null || movieScheduleEntity == null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " takeOrder: User or movie schedule not found." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
        customerOrderEntity.setUserId(userEntity.getId());
        customerOrderEntity.setOrderDatetime(new Timestamp(System.currentTimeMillis()));
        customerOrderEntity.setMovieScheduleId(movieScheduleEntity.getId());
        customerOrderEntity.setIsPaid(false);
        customerOrderEntity.setIsUsed(false);
        customerOrderEntity.setTicketAmount(ticketAmount);
        customerOrderEntity.setTotalPrice(ticketAmount * movieScheduleEntity.getPrice());
        int status = customerOrderDao.save(customerOrderEntity);
        if (status > 0) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " takeOrder: Success." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " takeOrder: Failure." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Get parameters
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        if (email.compareTo("") == 0 || date.compareTo("") == 0 || time.compareTo("") == 00) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Parameter ERROR." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);
        if (userEntity == null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: User not found." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
        customerOrderEntity.setUserId(userEntity.getId());
        customerOrderEntity.setOrderDatetime(Timestamp.valueOf(date + " " + time));
        customerOrderEntity = customerOrderDao.queryByUserIdAndOrderDatetime(customerOrderEntity);
        if (customerOrderEntity == null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Order not found." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        if (customerOrderEntity.getIsPaid() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Paid already." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        customerOrderEntity.setIsPaid(true);
        int status = customerOrderDao.update(customerOrderEntity);
        if (status > 0) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Success." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Failure." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void use(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Get parameters
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        if (email.compareTo("") == 0 || date.compareTo("") == 0 || time.compareTo("") == 00) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Parameter ERROR." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity = userDao.queryByEmail(userEntity);
        if (userEntity == null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: User not found." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        CustomerOrderDao customerOrderDao = new CustomerOrderDao();
        CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
        customerOrderEntity.setUserId(userEntity.getId());
        customerOrderEntity.setOrderDatetime(Timestamp.valueOf(date + " " + time));
        customerOrderEntity = customerOrderDao.queryByUserIdAndOrderDatetime(customerOrderEntity);
        if (customerOrderEntity == null) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Order not found." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        if (customerOrderEntity.getIsPaid() != true) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: To pay first." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        if (customerOrderEntity.getIsUsed() == true) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Used, invalid ticket." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
            return;
        }

        // Set isUsed flag to true
        customerOrderEntity.setIsUsed(true);
        int status = customerOrderDao.update(customerOrderEntity);
        if (status > 0) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Success." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        } else {
            PrintWriter out = response.getWriter();
            out.println("<script>alert('" + getClass() + " pay: Failure." + "');window.location.href='/administrator.jsp'</script>");
            out.flush();
            out.close();
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) {
    }

    private void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/json");

        CustomerOrderDao dao = new CustomerOrderDao();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        List<CustomerOrderEntity> orders = dao.getAll();

        PrintWriter out = response.getWriter();
        String json = gson.toJson(orders);
        out.println(json);
        out.flush();
        out.close();
        System.out.println("getJson: " + getClass());
    }
}
