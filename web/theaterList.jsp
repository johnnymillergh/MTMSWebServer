<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/24/2018
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.TheaterDao" %>
<%@ page import="entity.TheaterEntity" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Theater List</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Theater List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>name</td>
            <td>location</td>
        </tr>
        <%
            TheaterDao dao = new TheaterDao();
            List<TheaterEntity> theaters = dao.getAll();
            for (TheaterEntity entity : theaters) {%>
        <tr>
            <td><%=entity.getId() %>
            </td>
            <td><%=entity.getName() %>
            </td>
            <td><%=entity.getLocation() %>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
