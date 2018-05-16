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
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    long startTime = System.currentTimeMillis();

    UserReviewDao userReviewDao = new UserReviewDao();
    HashMap<Integer, List<UserReviewEntity>> userScoreMatrix = userReviewDao.queryUserScoreMatrix();

    long endTime = System.currentTimeMillis();
    PrintWriter printWriter = response.getWriter();
    printWriter.write("userScoreMatrix.size() = " + userScoreMatrix.size() +
            ", Runtime: " + (endTime - startTime) + " ms");
    printWriter.flush();
    printWriter.close();
%>
