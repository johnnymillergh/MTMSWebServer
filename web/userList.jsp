<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/24/2018
  Time: 9:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.UserDao" %>
<%@ page import="entity.UserEntity" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>User List</title>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:30px">User List</b></div>
<div align="center" style="font-size:20px"><a href="administrator.jsp">Back</a></div>
<hr size="2" noshade="noshade">

<div align="center">
    <table border="1">
        <tr>
            <td>id</td>
            <td>avatar</td>
            <td>username</td>
            <td>email</td>
            <td>password</td>
            <td>gender</td>
            <td>home_location</td>
        </tr>
        <%
            UserDao dao = new UserDao();
            List<UserEntity> users = dao.getAll();
            for (UserEntity entity : users) {%>
        <tr>
            <td><%=entity.getId() %>
            </td>
            <td><img src="showAvatar.jsp?email=<%=entity.getEmail()%>" style="height: 150px;width: auto"/>
            </td>
            <td><%=entity.getUsername() %>
            </td>
            <td><%=entity.getEmail() %>
            </td>
            <td><%=entity.getPassword() %>
            </td>
            <td><%=entity.getGender() %>
            </td>
            <td><%=entity.getHomeLocation() %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
