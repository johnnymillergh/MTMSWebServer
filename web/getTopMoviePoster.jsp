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
    try {
        String id = request.getParameter("id");
        String movieTitle = request.getParameter("movieTitle");

        TopMovieDao dao = new TopMovieDao();
        TopMovieEntity entity = new TopMovieEntity();

        response.setContentType("image/jpeg");
        OutputStream outs = response.getOutputStream();
        if (id != null) {
            entity.setId(Integer.parseInt(id));
            entity = dao.getPosterBytes(entity);
            outs.write(entity.getPoster());
            outs.flush();
            outs.close();
        } else if (movieTitle != null) {
            entity.setMovieTitle(movieTitle);
            entity = dao.getPosterBytesByMovieTitle(entity);
            outs.write(entity.getPoster());
            outs.flush();
            outs.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
