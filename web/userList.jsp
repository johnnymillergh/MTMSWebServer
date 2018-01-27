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
<%@ page import="entity.PageEntity" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

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
            PageEntity<UserEntity> pageEntity = new PageEntity<>();
            try {
                int currentPage = (int) session.getAttribute("currentPage");
                dao.getAll(pageEntity);
            } catch (Exception e) {
                e.printStackTrace();
                pageEntity.setCurrentPage(1);
                dao.getAll(pageEntity);
            }
            try {
                int currentPage2 = Integer.parseInt(request.getParameter("currentPage"));
                pageEntity.setCurrentPage(currentPage2);
                dao.getAll(pageEntity);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                pageEntity.setCurrentPage(1);
                dao.getAll(pageEntity);
            }
            for (UserEntity entity : pageEntity.getPageData()) {%>
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
        <tr align="center">
            <td colspan="14">
                <div style="color: blue">
                    Current Page <%=pageEntity.getCurrentPage()%>; Total Page(s) <%=pageEntity.getTotalPage()%><br>
                </div>
                <a href="<%=basePath%>userList.jsp?currentPage=1">
                    <input type="button" value="Start Page"></a>
                <a href="<%=basePath%>userList.jsp?currentPage=<%=pageEntity.getCurrentPage()-1%>">
                    <input type="button" value="<<Prev"></a>
                <a href="<%=basePath%>userList.jsp?currentPage=<%=pageEntity.getCurrentPage()+1%>">
                    <input type="button" value="Next>>"></a>
                <a href="<%=basePath%>userList.jsp?currentPage=<%=pageEntity.getTotalPage()%>">
                    <input type="button" value="End Page"></a>
                <form action="<%=basePath%>userList.jsp" method="get" onsubmit="return onCheckCurrentPageMovieList()">
                    <input type="number" name="currentPage" id="currentPageMovieList">
                    <input type="submit">
                </form>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
