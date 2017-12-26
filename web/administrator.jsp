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
<html>
<head>
    <title>Web Server: MTMS</title>
</head>
<body>

<div align="center"><b style="color:#000000; font-size:40px">Movie Ticket Management System</b></div>
<div align="center"><b style="color:#000000; font-size:30px">For Administrator</b></div>
<hr size="2" noshade="noshade">

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>1. User Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.User" method="post">
            <table align="center">
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="text" name="emailText"></td>
                </tr>
                <tr>
                    <td align="right">Username</td>
                    <td align="center"><input type="text" name="usernameText"></td>
                </tr>
                <tr>
                    <td align="right">Password</td>
                    <td align="center"><input type="text" name="passwordText"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="userOperation" value="add" checked="checked" align=""/>Add
                        <input type="radio" name="userOperation" value="update"/>Update
                        <input type="radio" name="userOperation" value="delete"/>Delete
                        <input type="radio" name="userOperation" value="query"/>Query
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
        <form action="${pageContext.request.contextPath}/servlet.administrator.Movie" method="post"
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
                    <td align="center"><input type="text" name="release_date"></td>
                </tr>
                <tr>
                    <td align="right">Filming Location</td>
                    <td align="center"><input type="text" name="filming_location"></td>
                </tr>
                <tr>
                    <td align="right">Runtime</td>
                    <td align="center"><input type="text" name="runtime"></td>
                </tr>
                <tr>
                    <td align="right">Aspect Ratio</td>
                    <td align="center"><input type="text" name="aspect_ratio"></td>
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
        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieSchedule" method="post">
            <table align="center">
                <tr>
                    <td align="right">Movie Title</td>
                    <td align="center"><input type="text" name="movieTitle"></td>
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
                    <td align="right">Date of show</td>
                    <td align="center"><input type="text" name="dateOfShow" onfocus="WdatePicker()"/></td>
                </tr>
                <tr>
                    <td align="right">Time of show</td>
                    <td align="center"><input type="text" id="timePicker" value="10:25 am" name="timeOfShow"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="movieScheduleOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="movieScheduleOperation" value="update"/>Update
                        <input type="radio" name="movieScheduleOperation" value="delete"/>Delete
                        <input type="radio" name="movieScheduleOperation" value="query"/>Query
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
        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieSchedule" method="post">
            <table align="center">
                <tr>
                    <td align="right">Movie Title</td>
                    <td align="center"></td>
                </tr>
                <tr>
                    <td align="right">Theater ID</td>
                    <td align="center"></td>
                </tr>
                <tr>
                    <td align="right">Auditorium ID</td>
                    <td align="center"></td>
                </tr>
                <tr>
                    <td align="right">Date of show</td>
                    <td align="center"></td>
                </tr>
                <tr>
                    <td align="right">Time of show</td>
                    <td align="center"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="ratingOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="ratingOperation" value="update"/>Update
                        <input type="radio" name="ratingOperation" value="delete"/>Delete
                        <input type="radio" name="ratingOperation" value="query"/>Query
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
    </fieldset>
</div>

<script type="text/javascript">
    ng.ready(function () {
        var tp = new ng.TimePicker({
            input: 'timePicker',  // the input field id
            start: '0:00 am',           // what's the first available hour
            end: '11:55 pm',            // what's the last avaliable hour
            top_hour: 12                // what's the top hour (in the clock face, 0 = midnight)
        });
    });
</script>

<%@include file="footer.jsp" %>
<%--<fieldset>--%>
<%--<legend>--%>
<%--Upload single file--%>
<%--</legend>--%>
<%--<!-- 文件上传时必须要设置表单的enctype="multipart/form-data"-->--%>
<%--<form action="${pageContext.request.contextPath}/servlet.UploadPictureServlet"--%>
<%--15 method="post" enctype="multipart/form-data">--%>
<%--Choose file:--%>
<%--<input type="file" name="file">--%>
<%--<br>--%>
<%--<input type="submit" value="Upload">--%>
<%--</form>--%>
<%--</fieldset>--%>
<%--<fieldset>--%>
<%--<legend>--%>
<%--Upload 2 files--%>
<%--</legend>--%>
<%--<!-- 文件上传时必须要设置表单的enctype="multipart/form-data"-->--%>
<%--<form action="${pageContext.request.contextPath}/servlet.UploadPictureServlet"--%>
<%--29 method="post" enctype="multipart/form-data">--%>
<%--Choose file:--%>
<%--<input type="file" name="file1">--%>
<%--<br>--%>
<%--Choose file:--%>
<%--<input type="file" name="file2">--%>
<%--<br>--%>
<%--<input type="submit" value="Upload">--%>
<%--</form>--%>
<%--</fieldset>--%>
<%--<hr/>--%>
</body>
</html>
