<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 1/23/2018
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.CustomerOrderDao" %>
<%@ page import="entity.CustomerOrderEntity" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Customer Order List</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
    <style type="text/css">
        html, body {
            overflow: hidden;
            height: 100%;
            margin: 0;
            padding: 0;
            font: 14px/1.8 Georgia, Arial, Simsun;
        }

        html {
            _padding: 110px 0;
        }

        #hd {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 105px;
            background: #434343;
        }

        #bd {
            position: absolute;
            top: 105px;
            right: 0;
            bottom: 100px;
            left: 0;
            overflow: hidden;
            width: 100%;
            _height: 100%;
        }

        #main {
            position: absolute;
            _position: static;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            overflow: scroll;
            _overflow: hidden;
            _height: 100%;
            _margin-left: 210px;
            background: #ffffff;
        }

        #content {
            _overflow: scroll;
            width: 50%;
            height: 29%;
        }

        #ft {
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 100px;
            background: #434343;
        }

        .tit-layout {
            margin: 30px 0 0;
            font-size: 32px;
            text-align: center;
        }

        .copyright {
            font-weight: bold;
            text-align: center;
        }

        #feature {
            width: 200%;
            line-height: 4;
        }

        #feature .hd {
            padding: 20px 15px;
        }

        #feature .hd h2 {
            margin: 0;
            font-size: 30px;
        }

        #feature .bd ol {
            margin-top: 0;
        }

        #feature .bd h3 {
            margin: 0;
            padding: 0 15px;
            font-size: 24px;
        }

        #feature .ft {
            padding: 10px 15px 30px;
        }

        a {
            color: #115dff
        }

        a:hover {
            color: #c32916
        }
    </style>
</head>
<body>

<div id="hd">
    <table align="center">
        <tr>
            <td rowspan="2">
                <img src="res/drawable/new_vista_icon_128x128.ico" width="100px" height="100px">
            </td>
            <td>
                <div align="center"><b style="color:#c32916; font-size:35px">Customer Order List</b></div>
            </td>
        </tr>
        <tr>
            <td>
                <div align="center" style="font-size:30px"><a href="administrator.jsp">Back</a></div>
            </td>
        </tr>
    </table>
</div>

<div id="bd">
    <div id="main">
        <div id="content">
            <div id="feature" class="feature">
                <div align="center">
                    <table border="1">
                        <tr>
                            <td>id</td>
                            <td>user_id</td>
                            <td>order_datetime</td>
                            <td>movie_schedule_id</td>
                            <td>movie_title</td>
                            <td>showtime</td>
                            <td>seat_id</td>
                            <td>seat_location</td>
                            <td>auditorium_name</td>
                            <td>theater_name</td>
                            <td>theater_location</td>
                            <td>is_paid</td>
                            <td>payment_datetime</td>
                            <td>is_used</td>
                            <td>used_datetime</td>
                            <td>ticket_amount</td>
                            <td>total_price</td>
                        </tr>
                        <%
                            CustomerOrderDao dao = new CustomerOrderDao();
                            List<CustomerOrderEntity> orders = dao.getAll();
                            for (CustomerOrderEntity order : orders) {%>
                        <tr>
                            <td><%=order.getId() %>
                            </td>
                            <td><%=order.getUserId() %>
                            </td>
                            <td><%=order.getOrderDatetime() %>
                            </td>
                            <td><%=order.getMovieScheduleId() %>
                            </td>
                            <td><%=order.getMovieTitle() %>
                            </td>
                            <td><%=order.getShowtime() %>
                            </td>
                            <td><%=order.getSeatId() %>
                            </td>
                            <td><%=order.getSeatLocation() %>
                            </td>
                            <td><%=order.getAuditoriumName() %>
                            </td>
                            <td><%=order.getTheaterName() %>
                            </td>
                            <td><%=order.getTheaterLocation() %>
                            </td>
                            <td><%=order.getIsPaid() %>
                            </td>
                            <td><%=order.getPaymentDatetime() %>
                            </td>
                            <td><%=order.getIsUsed() %>
                            </td>
                            <td><%=order.getUsedDatetime() %>
                            </td>
                            <td><%=order.getTicketAmount() %>
                            </td>
                            <td><%=order.getTotalPrice() %>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="ft">
    <%@include file="footer.jsp" %>
</div>

</body>
</html>
