<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/25/2018
  Time: 1:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.SeatDao" %>
<%@ page import="entity.SeatEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Seat List</title>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Seat List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<%
    String auditoriumId = request.getParameter("auditoriumId");
    SeatDao dao = new SeatDao();
    SeatEntity seatEntity = new SeatEntity();
    seatEntity.setAuditoriumId(Integer.parseInt(auditoriumId));
    List<SeatEntity> seats = new ArrayList<>();
    seats = dao.getSeatOfAuditorium(seatEntity);
%>

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>auditorium_id</td>
            <td>row_number</td>
            <td>col_number</td>
            <td>is_selected</td>
            <td>user_id</td>
            <td>user_email</td>
            <td>order_datetime</td>
        </tr>
        <% for (SeatEntity entity : seats) {%>
        <tr>
            <td><%=entity.getId() %>
            </td>
            <td><%=entity.getAuditoriumId() %>
            </td>
            <td><%=entity.getRowNumber() %>
            </td>
            <td><%=entity.getColNumber() %>
            </td>
            <td><%=entity.getIsSelected() %>
            </td>
            <td><%=entity.getUserId() %>
            </td>
            <td><%=entity.getUserEmail() %>
            </td>
            <td><%=entity.getOrderDatetime() %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
