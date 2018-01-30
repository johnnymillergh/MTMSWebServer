<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/25/2018
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.AuditoriumDao" %>
<%@ page import="entity.AuditoriumEntity" %>
<%@ page import="java.util.List" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Auditorium List</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">Auditorium List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>theater_id</td>
            <td>name</td>
            <td>capacity</td>
        </tr>
        <%
            AuditoriumDao dao = new AuditoriumDao();
            List<AuditoriumEntity> auditoria = dao.getAll();
            for (AuditoriumEntity auditorium : auditoria) {%>
        <tr>
            <td><%=auditorium.getId() %>
            </td>
            <td><%=auditorium.getTheaterId() %>
            </td>
            <td><%=auditorium.getName() %>
            </td>
            <td><%=auditorium.getCapacity() %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
