<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/18/2018
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.UserDao" %>
<%@ page import="entity.UserEntity" %>
<%@ page import="java.io.OutputStream" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
    String email = request.getParameter("id");
    UserDao dao = new UserDao();
    UserEntity userEntity = new UserEntity();
    userEntity.setId(Integer.parseInt(email));
    userEntity = dao.getAvatarBytesById(userEntity);
    response.setContentType("image/jpeg");
    OutputStream outs = response.getOutputStream();
    outs.write(userEntity.getAvatar());
    outs.flush();
    outs.close();
%>
