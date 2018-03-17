<%--suppress MismatchedQueryAndUpdateOfCollection --%>
<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/28/2018
  Time: 10:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.MovieScheduleEntity" %>
<%@ page import="dao.MovieScheduleDao" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.MovieEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.MovieDao" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    MovieScheduleDao movieScheduleDao = new MovieScheduleDao();
    List<MovieScheduleEntity> movieSchedules = movieScheduleDao.getAll();

    MovieDao movieDao = new MovieDao();
    List<MovieEntity> moviesInTheaters = new ArrayList<>();
    List<Integer> movieIds = new ArrayList<>();

    for (MovieScheduleEntity e : movieSchedules) {
        MovieEntity movieEntity = new MovieEntity();
        if (!movieIds.contains(e.getMovieId())) {
            movieEntity.setId(e.getMovieId());
            movieEntity = movieDao.queryById(movieEntity);
            moviesInTheaters.add(movieEntity);
            movieIds.add(e.getMovieId());
        }
    }

    response.setContentType("text/json");
    PrintWriter printWriter = response.getWriter();
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    String json = gson.toJson(moviesInTheaters);
    printWriter.write(json);
    printWriter.flush();
    printWriter.close();
%>
