<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/18/2018
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="dao.MovieDao" %>
<%@ page import="entity.MovieEntity" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String title = request.getParameter("title");
    MovieDao dao = new MovieDao();
    MovieEntity entity = new MovieEntity();
    entity.setTitle(title);
    entity = dao.getPosterBytes(entity);
    response.setContentType("image/jpeg");
    OutputStream outs = response.getOutputStream();
    outs.write(entity.getPoster());
    outs.flush();
    outs.close();
%>
