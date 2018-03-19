<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/28/2018
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="dao.MovieDao" %>
<%@ page import="entity.MovieRatingEntity" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    List<MovieRatingEntity> movies;
    MovieDao dao = new MovieDao();
    movies = dao.getTopRated();

    response.setContentType("text/json");
    PrintWriter printWriter = response.getWriter();
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    String json = gson.toJson(movies);
    printWriter.write(json);
    printWriter.flush();
    printWriter.close();
%>

