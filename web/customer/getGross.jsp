<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 5/7/2018
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.MovieEntity" %>
<%@ page import="dao.MovieDao" %>
<%@ page import="entity.MovieRankingEntity" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="java.io.PrintWriter" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String movieTitle = request.getParameter("movieTitle");
    MovieEntity movieEntity = new MovieEntity();
    movieEntity.setTitle(movieTitle);

    MovieDao movieDao = new MovieDao();
    MovieRankingEntity movieRankingEntity = movieDao.getGross(movieEntity);
    String json = new GsonBuilder().disableHtmlEscaping().create().toJson(movieRankingEntity);

    response.setContentType("text/json");
    PrintWriter printWriter = response.getWriter();
    printWriter.write(json);
    printWriter.flush();
    printWriter.close();
%>
