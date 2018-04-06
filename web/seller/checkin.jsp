<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 4/6/2018
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="dao.UserDao" %>
<%@ page import="entity.UserEntity" %>
<%@ page import="dao.CustomerOrderDao" %>
<%@ page import="entity.CustomerOrderEntity" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="entity.CheckinEntity" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%
    int userId = Integer.parseInt(request.getParameter("userId"));
    Timestamp orderDatetime = Timestamp.valueOf(request.getParameter("orderDatetime"));

    UserDao userDao = new UserDao();
    UserEntity userEntity = new UserEntity();
    userEntity.setId(userId);
    userEntity = userDao.queryById(userEntity);

    CustomerOrderDao customerOrderDao = new CustomerOrderDao();
    CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
    customerOrderEntity.setUserId(userEntity.getId());
    customerOrderEntity.setOrderDatetime(orderDatetime);
    customerOrderEntity.setIsUsed(true);
    customerOrderEntity.setUsedDatetime(new Timestamp(System.currentTimeMillis()));

    customerOrderEntity = customerOrderDao.queryByUserIdAndOrderDatetime(customerOrderEntity);
    if (customerOrderEntity.getIsUsed()) {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("Error: Invalid ticket");
        printWriter.flush();
        printWriter.close();
        return;
    }

    customerOrderEntity.setUserId(userEntity.getId());
    customerOrderEntity.setOrderDatetime(orderDatetime);
    customerOrderEntity.setIsUsed(true);
    customerOrderEntity.setUsedDatetime(new Timestamp(System.currentTimeMillis()));
    int status = customerOrderDao.updateUsed(customerOrderEntity);
    if (status > 0) {
        customerOrderEntity = customerOrderDao.queryByUserIdAndOrderDatetime(customerOrderEntity);
        CheckinEntity checkinEntity = new CheckinEntity();
        checkinEntity.setCustomer(userEntity);
        checkinEntity.setOrder(customerOrderEntity);
        String json = new GsonBuilder().disableHtmlEscaping().create().toJson(checkinEntity, CheckinEntity.class);

        PrintWriter printWriter = response.getWriter();
        printWriter.println(json);
        printWriter.flush();
        printWriter.close();
    } else {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("Error: Fail to checkin");
        printWriter.flush();
        printWriter.close();
    }
%>
