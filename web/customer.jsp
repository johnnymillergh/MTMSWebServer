<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/24/2017
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MTMS: Customer</title>
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
</body>
</html>
