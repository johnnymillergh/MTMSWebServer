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
    List<MovieEntity> movies = movieDao.getAllMovie();
    List<MovieEntity> commonMovies = movieDao.getCommonMovie(targetUser);

    List<UserEntity> commonUsers = userReviewDao.getCommonReviewsUser(targetUser);

    Recommender recommender = new Recommender();
    recommender.setTargetUser(targetUser);
    recommender.setMovies(movies);
    recommender.setUserScoreMatrix(userScoreMatrix);
    recommender.setCommonMovies(commonMovies);
    recommender.setCommonUsers(commonUsers);

//    recommender.calculateSimilarity();
//
//    recommender.calculatePredictedScore();
//
//    recommender.removeTargetUserRatedMovie();
//
//    List<Map.Entry<Integer, Double>> sortedNearestNeighbors = recommender.getSortedNearestNeighbors();
//    List<Map.Entry<Integer, Double>> predictedScores = recommender.getSortedPredictedScores();

//    System.out.println("userScoreMatrix.size() = " + userScoreMatrix.size());
//    System.out.println("Runtime: " + (endTime - startTime) + " ms");
//    System.out.println("sortedNearestNeighbors = " + sortedNearestNeighbors.toString());
//    System.out.println("predictedScores = " + predictedScores.toString());
//    System.out.println("predictedScores.size() = " + predictedScores.size());
//
//    PrintWriter printWriter = response.getWriter();
//    printWriter.write("userScoreMatrix.size() = " + userScoreMatrix.size() +
//            ", Runtime: " + (endTime - startTime) + " ms "
//            + "sortedNearestNeighbors = " + sortedNearestNeighbors.toString()
//            + "predictedScores = " + predictedScores.toString());
//    printWriter.flush();
//    printWriter.close();


    List<MovieEntity> recommendations = recommender.getRecommendation();

    long endTime = System.currentTimeMillis();

    System.err.println("Runtime (getRecommendation.jsp): " + (endTime - startTime) + " ms");
    System.out.println(recommendations.toString());
    System.out.println(recommendations.size());

    response.setContentType("text/json");
    String json = new GsonBuilder().disableHtmlEscaping().create().toJson(recommendations);
    PrintWriter printWriter = response.getWriter();
    printWriter.write(json);
    printWriter.flush();
    printWriter.close();
%>
