<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/18/2018
  Time: 5:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="dao.TopMovieDao" %>
<%@ page import="entity.TopMovieEntity" %>
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
