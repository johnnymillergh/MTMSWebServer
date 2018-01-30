<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/23/2018
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.TopMovieDao" %>
<%@ page import="entity.TopMovieEntity" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Top Movie List</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Top Movie List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>movie_id</td>
            <td>movie_title</td>
            <td>poster</td>
        </tr>
        <%
            TopMovieDao dao = new TopMovieDao();
            List<TopMovieEntity> topMovies = dao.getAll();
            for (TopMovieEntity topMovieEntity : topMovies) {%>
        <tr>
            <td><%=topMovieEntity.getId() %>
            </td>
            <td><%=topMovieEntity.getMovieId() %>
            </td>
            <td><%=topMovieEntity.getMovieTitle() %>
            </td>
            <td><img src="showTopMoviePoster.jsp?id=<%=topMovieEntity.getId()%>" style="height: 150px;width: 267px"/>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
