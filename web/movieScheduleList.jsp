<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/15/2018
  Time: 1:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MovieDao,entity.MovieScheduleEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.MovieScheduleDao" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Movie Schedule List</title>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Movie Schedule List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<table border="1">
    <tr>
        <td>id</td>
        <td>movie_id</td>
        <td>auditorium_id</td>
        <td>auditorium_theater_id</td>
        <td>price</td>
        <td>showtime</td>
        <td>date_of_show</td>
        <td>time_of_show</td>
    </tr>
    <%
        MovieScheduleDao dao = new MovieScheduleDao();
        List<MovieScheduleEntity> entities = dao.getAll();
        for (MovieScheduleEntity entity : entities) {%>
    <tr>
        <td><%=entity.getId() %>
        </td>
        <td><%=entity.getMovieId() %>
        </td>
        <td><%=entity.getAuditoriumId() %>
        </td>
        <td><%=entity.getAuditoriumTheaterId() %>
        </td>
        <td><%=entity.getPrice() %>
        </td>
        <td><%=entity.getShowtime() %>
        </td>
        <td><%=entity.getDateOfShow() %>
        </td>
        <td><%=entity.getTimeOfShow() %>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>