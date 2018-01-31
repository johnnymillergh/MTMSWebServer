<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/24/2018
  Time: 9:46 PM
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
    String email = request.getParameter("email");
    UserDao dao = new UserDao();
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(email);
    userEntity = dao.getAvatarBytes(userEntity);
    response.setContentType("image/jpeg");
    OutputStream outs = response.getOutputStream();
    outs.write(userEntity.getAvatar());
    outs.flush();
    outs.close();
%>
