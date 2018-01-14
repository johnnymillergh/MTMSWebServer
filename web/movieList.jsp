<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/29/2017
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MovieDao,entity.MovieEntity" %>
<%@ page import="java.util.List" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Movie List</title>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Movie List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>title</td>
            <td>duration</td>
            <td>genre</td>
            <td>director</td>
            <td>stars</td>
            <td>country</td>
            <td>language</td>
            <td>releaseDate</td>
            <td>filmingLocation</td>
            <td>runtime</td>
            <td>aspectRatio</td>
            <td>description</td>
            <td>poster</td>
            <%--<td>poster</td>--%>
        </tr>
        <%
            MovieDao dao = new MovieDao();
            List<MovieEntity> movies = dao.getAll();
            for (MovieEntity entity : movies) {%>
        <tr>
            <td><%=entity.getId() %>
            </td>
            <td><%=entity.getTitle() %>
            </td>
            <td><%=entity.getDuration() %>
            </td>
            <td><%=entity.getGenre() %>
            </td>
            <td><%=entity.getDirector() %>
            </td>
            <td><%=entity.getStars() %>
            </td>
            <td><%=entity.getCountry() %>
            </td>
            <td><%=entity.getLanguage() %>
            </td>
            <td><%=entity.getReleaseDate() %>
            </td>
            <td><%=entity.getFilmingLocation() %>
            </td>
            <td><%=entity.getRuntime() %>
            </td>
            <td><%=entity.getAspectRatio() %>
            </td>
            <td><%=entity.getDescription() %>
            </td>
            <td><img src="showPoster.jsp?title=<%=entity.getTitle()%>" style="height: 200px;width: auto"></td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>