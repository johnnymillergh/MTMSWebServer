<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/28/2018
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.MovieScheduleEntity" %>
<%@ page import="dao.MovieScheduleDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

    try {
        String movieTitle = request.getParameter("movieTitle");
        String movieId = request.getParameter("movieId");

        MovieScheduleEntity entity = new MovieScheduleEntity();
        MovieScheduleDao dao = new MovieScheduleDao();

        response.setContentType("text/json");
        PrintWriter printWriter = response.getWriter();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        if (movieTitle != null) {
            entity.setMovieTitle(movieTitle);
            List<MovieScheduleEntity> resultList = dao.getAllByMovieTitle(entity);

            if (resultList.size() != 0) {
                MovieScheduleEntity lowestPriceEntity = new MovieScheduleEntity();
                lowestPriceEntity.setPrice(Float.MAX_VALUE);
                for (MovieScheduleEntity mse : resultList) {
                    if (mse.getPrice() < lowestPriceEntity.getPrice()) {
                        lowestPriceEntity = mse;
                    }
                }
                String json = gson.toJson(lowestPriceEntity);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            }
        } else if (movieId != null) {
            entity.setMovieId(Integer.parseInt(movieId));
            List<MovieScheduleEntity> resultList = dao.getAllByMovieId(entity);

            if (resultList.size() != 0) {
                MovieScheduleEntity lowestPriceEntity = new MovieScheduleEntity();
                lowestPriceEntity.setPrice(Float.MAX_VALUE);
                for (MovieScheduleEntity mse : resultList) {
                    if (mse.getPrice() < lowestPriceEntity.getPrice()) {
                        lowestPriceEntity = mse;
                    }
                }
                String json = gson.toJson(lowestPriceEntity);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

