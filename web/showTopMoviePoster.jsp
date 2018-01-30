<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/30/2018
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.TopMovieDao" %>
<%@ page import="entity.TopMovieEntity" %>
<%@ page import="java.io.OutputStream" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String id = request.getParameter("id");
    TopMovieDao dao = new TopMovieDao();
    TopMovieEntity entity = new TopMovieEntity();
    entity.setId(Integer.parseInt(id));
    entity = dao.getPosterBytes(entity);
    response.setContentType("image/jpeg");
    OutputStream outs = response.getOutputStream();
    outs.write(entity.getPoster());
    outs.flush();
    outs.close();
%>
