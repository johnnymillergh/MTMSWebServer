<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/19/2017
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="NoGray1.2.2/ng_all.js"></script>
<script type="text/javascript" src="NoGray1.2.2/ng_ui.js"></script>
<script type="text/javascript" src="NoGray1.2.2/components/timepicker.js"></script>
<script type="text/javascript" src="NoGray1.2.2/components/calendar.js"></script>

<html>
<head>
    <title>MTMS: Administrator</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div align="center"><b style="color:#000000; font-size:40px">Movie Ticket Management System</b></div>
<div align="center"><b style="color:#000000; font-size:30px">For Administrator</b></div>
<hr size="2" noshade="noshade">

<div align="center" style="font-size:20px"><a href="home.jsp">Back to home</a></div>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>1. User Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.UserManagement" method="post">
            <table align="center">
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Username</td>
                    <td align="center"><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td align="right">Password</td>
                    <td align="center"><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="userOperation" value="add" checked="checked" align=""/>Add
                        <input type="radio" name="userOperation" value="update"/>Update
                        <input type="radio" name="userOperation" value="delete"/>Delete
                        <input type="radio" name="userOperation" value="query"/>Query
                        <input type="radio" name="userOperation" value="queryOnline"/>Query online user
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
            <h2>2. Movie Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieManagement" method="post"
              enctype="multipart/form-data">
            <table align="center">
                <tr>
                    <td align="right">Title</td>
                    <td align="center"><input type="text" name="title"></td>
                </tr>
                <tr>
                    <td align="right">Duration</td>
                    <td align="center"><input type="text" name="duration"></td>
                </tr>
                <tr>
                    <td align="right">Genre</td>
                    <td align="center"><input type="text" name="genre"></td>
                </tr>
                <tr>
                    <td align="right">Director</td>
                    <td align="center"><input type="text" name="director"></td>
                </tr>
                <tr>
                    <td align="right">Stars</td>
                    <td align="center"><input type="text" name="stars"></td>
                </tr>
                <tr>
                    <td align="right">Country</td>
                    <td align="center"><input type="text" name="country"></td>
                </tr>
                <tr>
                    <td align="right">Language</td>
                    <td align="center"><input type="text" name="language"></td>
                </tr>
                <tr>
                    <td align="right">Release Date</td>
                    <td align="center"><input type="text" name="releaseDate"/></td>
                </tr>
                <tr>
                    <td align="right">Filming Location</td>
                    <td align="center"><input type="text" name="filmingLocation"></td>
                </tr>
                <tr>
                    <td align="right">Runtime</td>
                    <td align="center"><input type="text" name="runtime"></td>
                </tr>
                <tr>
                    <td align="right">Aspect Ratio</td>
                    <td align="center"><input type="text" name="aspectRatio"></td>
                </tr>
                <tr>
                    <td align="right">Description</td>
                    <td align="center"><input type="text" name="description"></td>
                </tr>
                <tr>
                    <td align="right">Poster</td>
                    <td align="center"><input type="file" name="poster"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="movieOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="movieOperation" value="update"/>Update
                        <input type="radio" name="movieOperation" value="delete"/>Delete
                        <input type="radio" name="movieOperation" value="query"/>Query
                        <input type="radio" name="movieOperation" value="getAll"/>Get all
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
            <h2>3. Movie Schedule Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieScheduleManagement" method="post">
            <table align="center">
                <tr>
                    <td align="right">Movie Title (Optional)</td>
                    <td align="center"><input type="text" name="movieTitle"></td>
                </tr>
                <tr>
                    <td align="right">Movie ID</td>
                    <td align="center"><input type="text" name="movieId"></td>
                </tr>
                <tr>
                    <td align="right">Theater ID</td>
                    <td align="center"><input type="text" name="theaterId"></td>
                </tr>
                <tr>
                    <td align="right">Auditorium ID</td>
                    <td align="center"><input type="text" name="auditoriumId"></td>
                </tr>
                <tr>
                    <td align="right">Price</td>
                    <td align="center"><input type="text" name="price"></td>
                </tr>
                <tr>
                    <td align="right">Date of show</td>
                    <td align="center"><input type="text" id="dateOfShow" name="dateOfShow"/></td>
                </tr>
                <tr>
                    <td align="right">Time of show</td>
                    <td align="center"><input type="text" id="timePicker" value="10:25 am" name="timeOfShow"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="movieScheduleOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="movieScheduleOperation" value="update"/>Update
                        <input type="text" name="id" size="12" placeholder="movieScheduleId"/>
                        <input type="radio" name="movieScheduleOperation" value="delete"/>Delete
                        <input type="radio" name="movieScheduleOperation" value="query"/>Query
                        <input type="radio" name="movieScheduleOperation" value="getAll"/>Get all
                        <input type="radio" name="movieScheduleOperation" value="getJson"/>Get Json
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
            <h2>4. Rating Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.UserReview" method="post">
            <table align="center">
                <tr>
                    <td align="right">User Email</td>
                    <td align="center"><input type="text" name="userEmail"></td>
                </tr>
                <tr>
                    <td align="right">Movie Title</td>
                    <td align="center"><input type="text" name="movieTitle"></td>
                </tr>
                <tr>
                    <td align="right">Score</td>
                    <td align="center"><input type="text" name="score"></td>
                </tr>
                <tr>
                    <td align="right">Text</td>
                    <td align="center"><input type="text" name="text"></td>
                </tr>
                <tr>
                    <td align="right">Date</td>
                    <td align="center"><input type="text" name="dateOfShow" onfocus="WdatePicker()"/></td>
                </tr>
                <tr>
                    <td align="right">Time</td>
                    <td align="center"><input type="text" id="timePicker2" value="10:25 am" name="timeOfShow"/></td>
                </tr>
                <tr>
                    <td colspan="4" align="center">
                        <input type="radio" name="ratingOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="ratingOperation" value="update"/>Update
                        <input type="radio" name="ratingOperation" value="delete"/>Delete
                        <input type="radio" name="ratingOperation" value="query"/>Query
                        <input type="radio" name="ratingOperation" value="getAll"/>Get all
                        <input type="radio" name="ratingOperation" value="getJson"/>Get Json
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
            <h2>5. Order Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.OrderManagement" method="post">
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
                    <td align="center"><input type="text" id="timePicker3" value="10:25 am" name="timeOfShow"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="orderOperation" value="takeOrder" checked="checked"/>Take Order
                        <input type="radio" name="orderOperation" value="pay"/>Pay
                        <input type="radio" name="orderOperation" value="use"/>Use
                        <input type="radio" name="orderOperation" value="delete"/>Delete
                        <input type="radio" name="orderOperation" value="getAll"/>Get all
                        <input type="radio" name="orderOperation" value="getJson"/>Get Json
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
            <h2>6. Push Message</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.PushMessage" method="post">
            <table align="center">
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Message</td>
                    <td align="center"><input type="text" name="message"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Push">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>
<br>

<script type="text/javascript">
    ng.ready(function () {
        // Init time picker
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
        var tp3 = new ng.TimePicker({
            input: 'timePicker3',  // the input field id
            format: 'H:i:s',
            server_format: 'H:i:s',
            use24: true
        });

        // Init date picket
        var my_cal = new ng.Calendar({
            input:'dateOfShow',
            date_format:'Y-n-j'
        });
    });
</script>

<%@include file="footer.jsp" %>

</body>
</html>
