<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/29/2017
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.MovieDao" %>
<%@ page import="entity.MovieEntity" %>
<%@ page import="java.io.OutputStream" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String title = request.getParameter("title");
    MovieDao movieDao = new MovieDao();
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setTitle(title);
    movieEntity = movieDao.getPosterBytes(movieEntity);
    response.setContentType("image/jpeg");
    OutputStream outs = response.getOutputStream();
    outs.write(movieEntity.getPoster());
    outs.flush();
    outs.close();
%>