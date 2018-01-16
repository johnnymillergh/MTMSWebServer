<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/24/2017
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="NoGray1.2.2/ng_all.js"></script>
<script type="text/javascript" src="NoGray1.2.2/ng_ui.js"></script>
<script type="text/javascript" src="NoGray1.2.2/components/timepicker.js"></script>
<html>
<head>
    <title>MTMS: Customer</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div align="center"><b style="color:#000000; font-size:40px">Movie Ticket Management System</b></div>
<div align="center"><b style="color:#000000; font-size:30px">For Customer</b></div>
<hr size="2" noshade="noshade">

<div align="center" style="font-size:20px"><a href="home.jsp">Back to home</a></div>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>1. Sign up</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.customer.SignUp" method="post">
            <table align="center">
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Password</td>
                    <td align="center"><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td align="right">Username</td>
                    <td align="center"><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Sign Up">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<br>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>2. Log in</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.customer.LogIn" method="post">
            <table align="center">
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Password</td>
                    <td align="center"><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Log In">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<br>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>3. Get Movie (JSON Object)</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.customer.GetMovie" method="post">
            <table align="center">
                <tr>
                    <td align="left"><input type="radio" name="movieOperation" value="getAll" checked="checked"
                                            align=""/>Get all the movies w/o poster picture
                    </td>
                </tr>
                <tr>
                    <td align="left"><input type="radio" name="movieOperation" value="getPoster" checked="checked"
                                            align=""/>Get the poster picture of movie
                    </td>
                    <td align="center"><input type="text" name="title"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<br>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>4. Get User Review</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.customer.GetUserReview" method="post">
            <table align="center">
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Movie Title</td>
                    <td align="center"><input type="text" name="movieTitle"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<br>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>5. Order</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.customer.Order" method="post">
            <table align="center">
                <tr>
                    <td align="right">User ID</td>
                    <td align="center"><input type="text" name="userId"></td>
                </tr>
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Current datetime</td>
                    <td align="center"><input type="text" name="orderDatetime"></td>
                </tr>
                <tr>
                    <td align="right">Movie Schedule ID</td>
                    <td align="center"><input type="text" name="movieScheduleId"></td>
                </tr>
                <tr>
                    <td align="right">Ticket amount</td>
                    <td align="center"><input type="text" name="movieScheduleId"></td>
                </tr>
                <tr>
                    <td align="right">Date</td>
                    <td align="center"><input type="text" name="dateOfShow" onfocus="WdatePicker()"/></td>
                </tr>
                <tr>
                    <td align="right">Time</td>
                    <td align="center"><input type="text" id="timePicker" value="10:25 am" name="timeOfShow"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="orderOperation" value="takeOrder" checked="checked"/>Take Order
                        <input type="radio" name="orderOperation" value="pay"/>Pay
                        <input type="radio" name="orderOperation" value="use"/>Use
                        <input type="radio" name="orderOperation" value="query"/>Query
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<br>

<script type="text/javascript">
    ng.ready(function () {
        var tp = new ng.TimePicker({
            input: 'timePicker',  // the input field id
            format: 'H:i:s',
            use24: true
        });
    });
</script>

<%@include file="footer.jsp" %>

</body>
</html>
