<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/15/2018
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.UserReviewDao,entity.UserReviewEntity" %>
<%@ page import="java.util.List" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>User Review List</title>
</head>

<body>
<div align="center"><b style="color:#000000; font-size:30px">User Review List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>user_id</td>
            <td>movie_id</td>
            <td>score</td>
            <td>text</td>
            <td>date_time</td>
        </tr>
        <%
            UserReviewDao dao = new UserReviewDao();
            List<UserReviewEntity> entities = dao.getAll();
            for (UserReviewEntity entity : entities) {%>
        <tr>
            <td><%=entity.getId() %>
            </td>
            <td><%=entity.getUserId() %>
            </td>
            <td><%=entity.getMovieId() %>
            </td>
            <td><%=entity.getScore() %>
            </td>
            <td>
                <div><b style="color:#000000; font-size:20px"><%=entity.getTitle()%>
                </b></div>
                <textarea style="resize: none" cols="100" rows="6" readonly><%=entity.getText() %></textarea>
            </td>
            <td><%=entity.getDateTime() %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
