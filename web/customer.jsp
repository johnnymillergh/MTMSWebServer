<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/24/2017
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="NoGray1.2.2/ng_all.js"></script>
<script type="text/javascript" src="NoGray1.2.2/ng_ui.js"></script>
<script type="text/javascript" src="NoGray1.2.2/components/timepicker.js"></script>

<html>
<head>
    <title>MTMS: Customer</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            bottom: 110px;
            left: 0;
            overflow: hidden;
            width: 100%;
            _height: 100%;
        }

        #side {
            position: absolute;
            top: 0;
            left: 0;
            bottom: 0;
            overflow: scroll;
            width: 220px;
            _height: 100%;
            background: #eeeeee;
            padding-left: 10px;
        }

        #main {
            position: absolute;
            _position: static;
            top: 0;
            right: 0;
            bottom: 0;
            left: 230px;
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
                <div align="center"><b style="color:#ffffff; font-size:35px">Movie Ticket Management System</b></div>
            </td>
        </tr>
        <tr>
            <td>
                <div align="center"><b style="color:#c32916; font-size:30px">For Customer</b></div>
            </td>
        </tr>
    </table>
</div>

<div id="bd">
    <div id="side">
        <div style="font-size: 24px;">Navigation</div>
        <div style="font-size: 18px;"><a href="home.jsp">》》Back to home《《</a></div>
        <div style="font-size: 18px;"><a href="#signUp">1. Sign up</a><br></div>
        <div style="font-size: 18px;"><a href="#logIn">2. Log in</a><br></div>
        <div style="font-size: 18px;"><a href="#getMovie">3. Get Movie (JSON Object)</a><br></div>
        <div style="font-size: 18px;"><a href="#getTopMovie">4. Get Top Movie (JSON Object)</a><br></div>
        <div style="font-size: 18px;"><a href="#order">5. Order</a><br></div>
        <div style="font-size: 18px;"><a href="#userReviewManagement">6. User Review Management</a><br></div>
    </div>
    <div id="main">
        <div id="content">
            <div id="feature" class="feature">
                <div align="center" id="signUp">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>1. Sign up</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.customer.SignUp" method="post">
                            <table align="center">
                                <tr>
                                    <td align="right">Email</td>
                                    <td align="center"><input type="email" name="email"></td>
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

                <div align="center" id="logIn">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>2. Log in</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.customer.LogIn" method="post">
                            <table align="center">
                                <tr>
                                    <td align="right">Email</td>
                                    <td align="center"><input type="email" name="email"></td>
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

                <div align="center" id="getMovie">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>3. Get Movie (JSON Object)</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.customer.GetMovie" method="post">
                            <table align="center">
                                <tr>
                                    <td align="left"><input type="radio" name="movieOperation" value="getAll"
                                                            checked="checked"/>Get all the movies w/o poster picture
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><input type="radio" name="movieOperation" value="getPoster"
                                                            checked="checked"
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

                <div align="center" id="getTopMovie">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>4. Get Top Movie (JSON Object)</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.customer.GetTopMovie"
                              method="post">
                            <table align="center">
                                <tr>
                                    <td align="left"><input type="radio" name="topMovieOperation" value="getAll"
                                                            checked="checked"/>Get all top movies w/o poster picture
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <input type="radio" name="topMovieOperation" value="getPoster"
                                               checked="checked"/>Get the poster picture of top movie
                                    </td>
                                    <td align="center">
                                        <input type="number" name="id">
                                    </td>
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

                <div align="center" id="order">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>5. Order</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.customer.Order" method="post">
                            <table align="center">
                                <tr>
                                    <td align="right">User ID</td>
                                    <td align="center"><input type="number" name="userId"></td>
                                </tr>
                                <tr>
                                    <td align="right">Email</td>
                                    <td align="center"><input type="email" name="email"></td>
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
                                    <td align="center"><input type="date" name="dateOfShow"/></td>
                                </tr>
                                <tr>
                                    <td align="right">Time</td>
                                    <td align="center"><input type="text" id="timePicker" value="10:25 am"
                                                              name="timeOfShow"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input type="radio" name="orderOperation" value="takeOrder" checked="checked"/>Take
                                        Order
                                        <input type="radio" name="orderOperation" value="pay"/>Pay
                                        <input type="radio" name="orderOperation" value="use"/>Use
                                        <input type="radio" name="orderOperation" value="getJson"/>Get Json
                                        <input type="radio" name="orderOperation" value="query"/>Query
                                        <input type="submit" value="Submit">
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </fieldset>
                </div>
                <br>

                <div align="center" id="userReviewManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>6. User Review Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.customer.UserReviewManagement"
                              method="post">
                            <table align="center">
                                <tr>
                                    <td align="right">User Email</td>
                                    <td align="left"><input type="email" name="email"></td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Title</td>
                                    <td align="left"><input type="text" name="movieTitle"></td>
                                </tr>
                                <tr>
                                    <td align="right">Score</td>
                                    <td align="left"><input type="number" name="score"></td>
                                </tr>
                                <tr>
                                    <td align="right">Title</td>
                                    <td align="left"><input type="text" name="userReviewTitle"></td>
                                </tr>
                                <tr>
                                    <td align="right">Text</td>
                                    <td align="left">
                                        <textarea name="text"
                                                  style="resize: none; width: 450px; height: 100px"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Date of Review</td>
                                    <td align="left"><input type="date" name="date"/></td>
                                </tr>
                                <tr>
                                    <td align="right">Time of Review</td>
                                    <td align="left"><input type="text" id="timePicker2" value="10:25:00" name="time"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="center">
                                        <input type="radio" name="userReviewOperation" value="add" checked="checked"/>Add
                                        <input type="radio" name="userReviewOperation" value="update"/>Update
                                        <input type="radio" name="userReviewOperation" value="delete"/>Delete
                                        <input type="radio" name="userReviewOperation" value="query"/>Query
                                        <input type="radio" name="userReviewOperation" value="getJson"/>Get Json
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
                            server_format: 'H:i:s',
                            use24: true
                        });
                        var tp2 = new ng.TimePicker({
                            input: 'timePicker2',  // the input field id
                            format: 'H:i:s',
                            server_format: 'H:i:s',
                            use24: true
                        });
                    });
                </script>
            </div>
        </div>
    </div>
</div>

<div id="ft">
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
