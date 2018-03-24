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
<%@ page import="entity.MovieScheduleEntity" %>
<%@ page import="dao.MovieScheduleDao" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    response.setContentType("text/json");
    PrintWriter printWriter = response.getWriter();
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String json;

    List<MovieScheduleEntity> movieSchedules;
    MovieScheduleDao dao = new MovieScheduleDao();
    MovieScheduleEntity entity = new MovieScheduleEntity();

    // Get parameter
    String movieTitle = request.getParameter("movieTitle");
    String auditoriumTheaterId = request.getParameter("auditoriumTheaterId");

    if (movieTitle != null && auditoriumTheaterId == null) {
        entity.setMovieTitle(movieTitle);
        movieSchedules = dao.getAllMovieScheduleByMovieTitle(entity);

        json = gson.toJson(movieSchedules);
        printWriter.write(json);
        printWriter.flush();
        printWriter.close();
        return;
    } else if (movieTitle != null && auditoriumTheaterId != null) {
        entity.setAuditoriumTheaterId(Integer.parseInt(auditoriumTheaterId));
        entity.setMovieTitle(movieTitle);
        movieSchedules = dao.getAllMovieScheduleByTheaterIdAndMovieTitle(entity);

        json = gson.toJson(movieSchedules);
        printWriter.write(json);
        printWriter.flush();
        printWriter.close();
        return;
    }
%>

