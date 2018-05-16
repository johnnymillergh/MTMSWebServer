<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 5/16/2018
  Time: 7:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="dao.UserReviewDao" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.UserReviewEntity" %>
<%@ page import="dao.UserDao" %>
<%@ page import="entity.UserEntity" %>
<%@ page import="entity.MovieEntity" %>
<%@ page import="dao.MovieDao" %>
<%@ page import="recommendation.Recommender" %>
<%@ page import="java.util.Map" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    long startTime = System.currentTimeMillis();

    // Get target user
    String email = request.getParameter("email");
    UserDao userDao = new UserDao();
    UserEntity targetUser = new UserEntity();
    targetUser.setEmail(email);
    targetUser = userDao.queryByEmail(targetUser);

    UserReviewDao userReviewDao = new UserReviewDao();
    HashMap<Integer, List<UserReviewEntity>> userScoreMatrix = userReviewDao.queryUserScoreMatrix();

    MovieDao movieDao = new MovieDao();
    List<MovieEntity> commonMovies = movieDao.getCommonMovie(targetUser);

    List<UserEntity> commonUsers = userReviewDao.getCommonReviewsUser(targetUser);

    Recommender recommender = new Recommender();
    recommender.setTargetUser(targetUser);
    recommender.setUserScoreMatrix(userScoreMatrix);
    recommender.setCommonMovies(commonMovies);
    recommender.setCommonUsers(commonUsers);

    recommender.calculateSimilarity();

    List<Map.Entry<Integer, Double>> sortedNearestNeighbors = recommender.getSortedNearestNeighbors();

    long endTime = System.currentTimeMillis();
    PrintWriter printWriter = response.getWriter();
    printWriter.write("userScoreMatrix.size() = " + userScoreMatrix.size() +
            ", Runtime: " + (endTime - startTime) + " ms " + sortedNearestNeighbors.toString());
    printWriter.flush();
    printWriter.close();
%>
