<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/23/2018
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.CustomerOrderDao" %>
<%@ page import="entity.CustomerOrderEntity" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Customer Order List</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Customer Order List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>user_id</td>
            <td>order_datetime</td>
            <td>movie_schedule_id</td>
            <td>is_paid</td>
            <td>is_used</td>
            <td>ticket_amount</td>
            <td>total_price</td>
        </tr>
        <%
            CustomerOrderDao dao = new CustomerOrderDao();
            List<CustomerOrderEntity> orders = dao.getAll();
            for (CustomerOrderEntity order : orders) {%>
        <tr>
            <td><%=order.getId() %>
            </td>
            <td><%=order.getUserId() %>
            </td>
            <td><%=order.getOrderDatetime() %>
            </td>
            <td><%=order.getMovieScheduleId() %>
            </td>
            <td><%=order.getIsPaid() %>
            </td>
            <td><%=order.getIsUsed() %>
            </td>
            <td><%=order.getTicketAmount() %>
            </td>
            <td><%=order.getTotalPrice() %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
