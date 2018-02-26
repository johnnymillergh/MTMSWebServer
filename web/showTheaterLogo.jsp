<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/26/2018
  Time: 4:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.TheaterDao" %>
<%@ page import="entity.TheaterEntity" %>
<%@ page import="java.io.OutputStream" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String id = request.getParameter("id");
    TheaterDao theaterDao = new TheaterDao();
    TheaterEntity entity = new TheaterEntity();
    entity.setId(Integer.parseInt(id));
    entity = theaterDao.getLogoBytesById(entity);
    response.setContentType("image/jpeg");
    OutputStream outs = response.getOutputStream();
    outs.write(entity.getLogo());
    outs.flush();
    outs.close();
%>
