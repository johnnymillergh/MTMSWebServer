<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/19/2017
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
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
<div align="center"><b style="color:#c32916; font-size:30px">For Administrator</b></div>
<hr size="2" noshade="noshade">

<div align="center" style="font-size:20px"><a href="home.jsp">Back to home</a></div>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>1. User Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.UserManagement" method="post"
              onsubmit="return onCheckUserManagementForm()">
            <table>
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="email" id="emailUserManagement" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Username</td>
                    <td align="center"><input type="text" id="usernameUserManagement" name="username"></td>
                </tr>
                <tr>
                    <td align="right">Password</td>
                    <td align="center"><input type="text" id="passwordUserManagement" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="radio" name="userOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="userOperation" value="update"/>Update
                        <input type="radio" name="userOperation" value="delete"/>Delete
                        <input type="radio" name="userOperation" value="query"/>Query
                        <input type="radio" name="userOperation" value="getAll"/>Get all
                        <input type="radio" name="userOperation" value="getOnlineUser"/>Get online user
                        <input type="radio" name="userOperation" value="getJson"/>Get Json
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>

        <fieldset style="width:fit-content; height:fit-content;">
            <legend>
                <h3>Push Message</h3>
            </legend>
            <form action="${pageContext.request.contextPath}/servlet.administrator.PushMessage" method="post">
                <table align="center">
                    <tr>
                        <td align="right">Email</td>
                        <td align="center"><input type="email" name="email"></td>
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
    </fieldset>
</div>
<br>
<script type="text/javascript">
    function onCheckUserManagementForm() {
        var radios = document.getElementsByName('userOperation');
        var radioChecked;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                radioChecked = radios[i];
                break;
            }
        }
        var emailUserManagement = document.getElementById('emailUserManagement');
        var passwordUserManagement = document.getElementById('passwordUserManagement');
        var usernameUserManagement = document.getElementById('usernameUserManagement');
        switch (radioChecked.value) {
            case 'add':
                if (emailUserManagement.value.length === 0) {
                    emailUserManagement.focus();
                    alert('Enter email');
                    return false;
                }
                if (passwordUserManagement.value.length === 0) {
                    alert('Enter password');
                    passwordUserManagement.focus();
                    return false;
                }
                if (usernameUserManagement.value.length === 0) {
                    alert('Enter username');
                    usernameUserManagement.focus();
                    return false;
                }
                break;
            case 'update':
                if (passwordUserManagement.value.length === 0) {
                    alert('Enter email');
                    return false;
                }
                break;
            case 'delete':
                if (emailUserManagement.value.length === 0) {
                    alert('Enter email');
                    return false;
                }
                break;
            case 'query':
                if (emailUserManagement.value.length === 0) {
                    alert('Enter email');
                    return false;
                }
                break;
            default:
                break;
        }
    }
</script>

<div align="center" class="MovieManagement">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>2. Movie Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieManagement" method="post"
              enctype="multipart/form-data" onsubmit="return onCheckMovieManagementForm()">
            <table align="center">
                <tr>
                    <td align="right">Title</td>
                    <td align="center"><input type="text" name="title" id="titleMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Duration</td>
                    <td align="center"><input type="text" name="duration" id="durationMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Genre</td>
                    <td align="center"><input type="text" name="genre" id="genreMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Director</td>
                    <td align="center"><input type="text" name="director" id="directorMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Stars</td>
                    <td align="center"><input type="text" name="stars" id="starsMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Country</td>
                    <td align="center"><input type="text" name="country" id="countryMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Language</td>
                    <td align="center"><input type="text" name="language" id="languageMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Release Date</td>
                    <td align="center"><input type="text" name="releaseDate" id="releaseDateMovieManagement"/></td>
                </tr>
                <tr>
                    <td align="right">Filming Location</td>
                    <td align="center"><input type="text" name="filmingLocation" id="filmingLocationMovieManagement">
                    </td>
                </tr>
                <tr>
                    <td align="right">Runtime</td>
                    <td align="center"><input type="text" name="runtime" id="runtimeMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Aspect Ratio</td>
                    <td align="center"><input type="text" name="aspectRatio" id="aspectRatioMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Description</td>
                    <td align="center"><input type="text" name="description" id="descriptionMovieManagement"></td>
                </tr>
                <tr>
                    <td align="right">Poster</td>
                    <td align="center"><input type="file" name="poster" id="posterMovieManagement"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="movieOperation" value="add" checked="checked"
                               id="addRadioMovieManagement"/>Add
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
<script type="text/javascript">
    function onCheckMovieManagementForm() {
        var radios = document.getElementsByName('movieOperation');
        var radioChecked;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                radioChecked = radios[i];
                break;
            }
        }
        var titleMovieManagement = document.getElementById('titleMovieManagement');
        var durationMovieManagement = document.getElementById('durationMovieManagement');
        var genreMovieManagement = document.getElementById('genreMovieManagement');
        var directorMovieManagement = document.getElementById('directorMovieManagement');
        var starsMovieManagement = document.getElementById('starsMovieManagement');
        var countryMovieManagement = document.getElementById('countryMovieManagement');
        var languageMovieManagement = document.getElementById('languageMovieManagement');
        var releaseDateMovieManagement = document.getElementById('releaseDateMovieManagement');
        var filmingLocationMovieManagement = document.getElementById('filmingLocationMovieManagement');
        var runtimeMovieManagement = document.getElementById('runtimeMovieManagement');
        var aspectRatioMovieManagement = document.getElementById('aspectRatioMovieManagement');
        var descriptionMovieManagement = document.getElementById('descriptionMovieManagement');
        var posterMovieManagement = document.getElementById('posterMovieManagement');

        switch (radioChecked.value) {
            case 'add':
                if (titleMovieManagement.value.length === 0) {
                    titleMovieManagement.focus();
                    alert('Enter title');
                    return false;
                }
                if (durationMovieManagement.value.length === 0) {
                    titleMovieManagement.focus();
                    alert('Enter duration');
                    return false;
                }
                if (genreMovieManagement.value.length === 0) {
                    genreMovieManagement.focus();
                    alert('Enter genre');
                    return false;
                }
                if (directorMovieManagement.value.length === 0) {
                    directorMovieManagement.focus();
                    alert('Enter genre');
                    return false;
                }
                if (starsMovieManagement.value.length === 0) {
                    starsMovieManagement.focus();
                    alert('Enter stars');
                    return false;
                }
                if (countryMovieManagement.value.length === 0) {
                    countryMovieManagement.focus();
                    alert('Enter country');
                    return false;
                }
                if (languageMovieManagement.value.length === 0) {
                    languageMovieManagement.focus();
                    alert('Enter language');
                    return false;
                }
                if (releaseDateMovieManagement.value.length === 0) {
                    releaseDateMovieManagement.focus();
                    alert('Enter release date');
                    return false;
                }
                if (filmingLocationMovieManagement.value.length === 0) {
                    filmingLocationMovieManagement.focus();
                    alert('Enter filming location');
                    return false;
                }
                if (runtimeMovieManagement.value.length === 0) {
                    runtimeMovieManagement.focus();
                    alert('Enter runtime');
                    return false;
                }
                if (aspectRatioMovieManagement.value.length === 0) {
                    aspectRatioMovieManagement.focus();
                    alert('Enter aspect ratio');
                    return false;
                }
                if (descriptionMovieManagement.value.length === 0) {
                    descriptionMovieManagement.focus();
                    alert('Enter description');
                    return false;
                }
                if (posterMovieManagement.value.length === 0) {
                    posterMovieManagement.focus();
                    alert('Upload poster');
                    return false;
                }
                break;
            case 'update':
                if (titleMovieManagement.value.length === 0) {
                    titleMovieManagement.focus();
                    alert('Enter title');
                    return false;
                }
                if (durationMovieManagement.value.length === 0) {
                    titleMovieManagement.focus();
                    alert('Enter duration');
                    return false;
                }
                if (genreMovieManagement.value.length === 0) {
                    genreMovieManagement.focus();
                    alert('Enter genre');
                    return false;
                }
                if (directorMovieManagement.value.length === 0) {
                    directorMovieManagement.focus();
                    alert('Enter genre');
                    return false;
                }
                if (starsMovieManagement.value.length === 0) {
                    starsMovieManagement.focus();
                    alert('Enter stars');
                    return false;
                }
                if (countryMovieManagement.value.length === 0) {
                    countryMovieManagement.focus();
                    alert('Enter country');
                    return false;
                }
                if (languageMovieManagement.value.length === 0) {
                    languageMovieManagement.focus();
                    alert('Enter language');
                    return false;
                }
                if (releaseDateMovieManagement.value.length === 0) {
                    releaseDateMovieManagement.focus();
                    alert('Enter release date');
                    return false;
                }
                if (filmingLocationMovieManagement.value.length === 0) {
                    filmingLocationMovieManagement.focus();
                    alert('Enter filming location');
                    return false;
                }
                if (runtimeMovieManagement.value.length === 0) {
                    runtimeMovieManagement.focus();
                    alert('Enter runtime');
                    return false;
                }
                if (aspectRatioMovieManagement.value.length === 0) {
                    aspectRatioMovieManagement.focus();
                    alert('Enter aspect ratio');
                    return false;
                }
                if (descriptionMovieManagement.value.length === 0) {
                    descriptionMovieManagement.focus();
                    alert('Enter description');
                    return false;
                }
                if (posterMovieManagement.value.length === 0) {
                    posterMovieManagement.focus();
                    alert('Upload poster');
                    return false;
                }
                break;
            case 'delete':
                if (titleMovieManagement.value.length === 0) {
                    titleMovieManagement.focus();
                    alert('Enter title');
                    return false;
                }
                if (window.confirm('Delete confirmation') === true) {
                    return true;
                } else {
                    return false;
                }
                break;
            case 'query':
                if (titleMovieManagement.value.length === 0) {
                    titleMovieManagement.focus();
                    alert('Enter title');
                    return false;
                }
                break;
            default:
        }
    }
</script>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>3. Movie Schedule Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieScheduleManagement" method="post"
              onsubmit="return onCheckMovieScheduleManagementForm()">
            <table align="center">
                <tr>
                    <td align="right">Movie ID</td>
                    <td align="center"><input type="number" name="movieId" id="movieIdMovieScheduleManagement"></td>
                </tr>
                <tr>
                    <td align="right">Theater ID</td>
                    <td align="center"><input type="number" name="theaterId" id="theaterIdMovieScheduleManagement"></td>
                </tr>
                <tr>
                    <td align="right">Auditorium ID</td>
                    <td align="center"><input type="number" name="auditoriumId"
                                              id="auditoriumIdMovieScheduleManagement"></td>
                </tr>
                <tr>
                    <td align="right">Price</td>
                    <td align="center"><input type="number" step="0.01" name="price" id="priceMovieScheduleManagement">
                    </td>
                </tr>
                <tr>
                    <td align="right">Date of Show</td>
                    <td align="center"><input type="date" name="dateOfShow" id="dateOfShowMovieScheduleManagement"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">Time of Show</td>
                    <td align="center"><input type="text" id="timePicker" value="10:25:00" name="timeOfShow"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="movieScheduleOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="movieScheduleOperation" value="update"/>Update
                        <input type="number" name="id" size="12" placeholder="movieScheduleId"
                               id="movieScheduleIdMovieScheduleManagement"/>
                        <input type="radio" name="movieScheduleOperation" value="delete"/>Delete
                        <input type="radio" name="movieScheduleOperation" value="query"/>Query
                        <input type="radio" name="movieScheduleOperation" value="getAll"/>Get all
                        <input type="radio" name="movieScheduleOperation" value="getJson"/>Get Json
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form>
        <fieldset style="width:fit-content; height:fit-content;">
            <legend>
                <h3>Top Movie Management</h3>
            </legend>
            <form action="${pageContext.request.contextPath}/servlet.administrator.TopMovieManagement" method="post"
                  onsubmit="return onCheckTopMovieManagementForm()">
                <table align="center">
                    <tr>
                        <td align="right">Movie Title 1</td>
                        <td align="center"><input type="text" name="title1" id="title1TopMovieManagement"></td>
                    </tr>
                    <tr>
                        <td align="right">Movie Title 2</td>
                        <td align="center"><input type="text" name="title2" id="title2TopMovieManagement"></td>
                    </tr>
                    <tr>
                        <td align="right">Movie Title 3</td>
                        <td align="center"><input type="text" name="title3" id="title3TopMovieManagement"></td>
                    </tr>
                    <tr>
                        <td align="right">Movie Title 4</td>
                        <td align="center"><input type="text" name="title4" id="title4TopMovieManagement"></td>
                    </tr>
                    <tr>
                        <td align="right">Movie Title 5</td>
                        <td align="center"><input type="text" name="title5" id="totle5TopMovieManagement"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="radio" name="topMovieOperation" value="update" checked="checked"/>Update
                            <input type="radio" name="topMovieOperation" value="add"/>Add
                            <input type="radio" name="topMovieOperation" value="delete"/>Delete All
                            <input type="radio" name="topMovieOperation" value="query"/>Query
                            <input type="radio" name="topMovieOperation" value="getAll"/>Get all
                            <input type="radio" name="topMovieOperation" value="getJson"/>Get Json
                            <input type="submit" value="Submit">
                        </td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </fieldset>
</div>
<br>
<script type="text/javascript">
    function onCheckMovieScheduleManagementForm() {
        var radios = document.getElementsByName('movieScheduleOperation');
        var radioChecked;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                radioChecked = radios[i];
                break;
            }
        }
        var movieIdMovieScheduleManagement = document.getElementById('movieIdMovieScheduleManagement');
        var theaterIdMovieScheduleManagement = document.getElementById('theaterIdMovieScheduleManagement');
        var auditoriumIdMovieScheduleManagement = document.getElementById('auditoriumIdMovieScheduleManagement');
        var priceMovieScheduleManagement = document.getElementById('priceMovieScheduleManagement');
        var dateOfShowMovieScheduleManagement = document.getElementById('dateOfShowMovieScheduleManagement');
        var timePicker = document.getElementById('timePicker');

        var movieScheduleIdMovieScheduleManagement = document.getElementById('movieScheduleIdMovieScheduleManagement');

        switch (radioChecked.value) {
            case 'add':
                if (movieIdMovieScheduleManagement.value.length === 0) {
                    movieIdMovieScheduleManagement.focus();
                    alert('Enter movie id');
                    return false;
                }
                if (theaterIdMovieScheduleManagement.value.length === 0) {
                    theaterIdMovieScheduleManagement.focus();
                    alert('Enter theater id');
                    return false;
                }
                if (auditoriumIdMovieScheduleManagement.value.length === 0) {
                    auditoriumIdMovieScheduleManagement.focus();
                    alert('Enter auditorium id');
                    return false;
                }
                if (priceMovieScheduleManagement.value.length === 0) {
                    priceMovieScheduleManagement.focus();
                    alert('Enter price');
                    return false;
                }
                if (dateOfShowMovieScheduleManagement.value.length === 0) {
                    dateOfShowMovieScheduleManagement.focus();
                    alert('Enter date of show');
                    return false;
                }
                if (timePicker.value.length === 0) {
                    timePicker.focus();
                    alert('Enter time of show');
                    return false;
                }
                break;
            case 'update':
                if (movieScheduleIdMovieScheduleManagement.value.length === 0) {
                    movieScheduleIdMovieScheduleManagement.focus();
                    alert('Enter movie schedule id');
                    return false;
                }
                if (movieIdMovieScheduleManagement.value.length === 0) {
                    movieIdMovieScheduleManagement.focus();
                    alert('Enter movie id');
                    return false;
                }
                if (theaterIdMovieScheduleManagement.value.length === 0) {
                    theaterIdMovieScheduleManagement.focus();
                    alert('Enter theater id');
                    return false;
                }
                if (auditoriumIdMovieScheduleManagement.value.length === 0) {
                    auditoriumIdMovieScheduleManagement.focus();
                    alert('Enter auditorium id');
                    return false;
                }
                if (priceMovieScheduleManagement.value.length === 0) {
                    priceMovieScheduleManagement.focus();
                    alert('Enter price');
                    return false;
                }
                if (dateOfShowMovieScheduleManagement.value.length === 0) {
                    dateOfShowMovieScheduleManagement.focus();
                    alert('Enter date of show');
                    return false;
                }
                if (timePicker.value.length === 0) {
                    timePicker.focus();
                    alert('Enter time of show');
                    return false;
                }
                break;
            case 'delete':
                if (movieScheduleIdMovieScheduleManagement.value.length === 0) {
                    movieScheduleIdMovieScheduleManagement.focus();
                    alert('Enter movie schedule id');
                    return false;
                }
                if (window.confirm('Delete confirmation') === true) {
                    return true;
                } else {
                    return false;
                }
                break;
            case 'query':
                if (movieScheduleIdMovieScheduleManagement.value.length === 0) {
                    movieScheduleIdMovieScheduleManagement.focus();
                    alert('Enter movie schedule id');
                    return false;
                }
                break;
            default:
        }
    }

    function onCheckTopMovieManagementForm() {
        var radios = document.getElementsByName('topMovieOperation');
        var radioChecked;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                radioChecked = radios[i];
                break;
            }
        }
        var title1TopMovieManagement = document.getElementById('title1TopMovieManagement');
        var title2TopMovieManagement = document.getElementById('title2TopMovieManagement');
        var title3TopMovieManagement = document.getElementById('title3TopMovieManagement');
        var title4TopMovieManagement = document.getElementById('title4TopMovieManagement');
        var title5TopMovieManagement = document.getElementById('title5TopMovieManagement');

        switch (radioChecked.value) {
            case 'add':
                if (title1TopMovieManagement.value.length === 0) {
                    title1TopMovieManagement.focus();
                    alert('Enter title 1');
                    return false;
                }
                if (title2TopMovieManagement.value.length === 0) {
                    title2TopMovieManagement.focus();
                    alert('Enter title 2');
                    return false;
                }
                if (title3TopMovieManagement.value.length === 0) {
                    title3TopMovieManagement.focus();
                    alert('Enter title 3');
                    return false;
                }
                if (title4TopMovieManagement.value.length === 0) {
                    title4TopMovieManagement.focus();
                    alert('Enter title 4');
                    return false;
                }
                if (title5TopMovieManagement.value.length === 0) {
                    title5TopMovieManagement.focus();
                    alert('Enter title 5');
                    return false;
                }
                break;
            case 'update':
                if (title1TopMovieManagement.value.length === 0) {
                    title1TopMovieManagement.focus();
                    alert('Enter title 1');
                    return false;
                }
                if (title2TopMovieManagement.value.length === 0) {
                    title2TopMovieManagement.focus();
                    alert('Enter title 2');
                    return false;
                }
                if (title3TopMovieManagement.value.length === 0) {
                    title3TopMovieManagement.focus();
                    alert('Enter title 3');
                    return false;
                }
                if (title4TopMovieManagement.value.length === 0) {
                    title4TopMovieManagement.focus();
                    alert('Enter title 4');
                    return false;
                }
                if (title5TopMovieManagement.value.length === 0) {
                    title5TopMovieManagement.focus();
                    alert('Enter title 5');
                    return false;
                }
                break;
            case 'delete':
                if (window.confirm('Delete confirmation') === true) {
                    return true;
                } else {
                    return false;
                }
                break;
            case 'query':
                break;
            default:
        }
    }
</script>

<div align="center">
    <fieldset style="width:fit-content; height:fit-content;">
        <legend>
            <h2>4. User Review Management</h2>
        </legend>
        <form action="${pageContext.request.contextPath}/servlet.administrator.UserReviewManagement" method="post">
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
                        <textarea name="text" style="resize: none; width: 450px; height: 100px"></textarea>
                    </td>
                </tr>
                <tr>
                    <td align="right">Date of Review</td>
                    <td align="left"><input type="date" name="date"/></td>
                </tr>
                <tr>
                    <td align="right">Time of Review</td>
                    <td align="left"><input type="text" id="timePicker2" value="10:25:00" name="time"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="radio" name="userReviewOperation" value="add" checked="checked"/>Add
                        <input type="radio" name="userReviewOperation" value="update"/>Update
                        <input type="radio" name="userReviewOperation" value="delete"/>Delete
                        <input type="radio" name="userReviewOperation" value="query"/>Query
                        <input type="radio" name="userReviewOperation" value="getAll"/>Get all
                        <input type="radio" name="userReviewOperation" value="getJson"/>Get Json
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
                    <td align="right">User ID (Optional)</td>
                    <td align="center"><input type="number" name="userId"></td>
                </tr>
                <tr>
                    <td align="right">Email</td>
                    <td align="center"><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td align="right">Movie Schedule ID</td>
                    <td align="center"><input type="number" name="movieScheduleId"></td>
                </tr>
                <tr>
                    <td align="right">Ticket Amount</td>
                    <td align="center"><input type="number" name="ticketAmount"></td>
                </tr>
                <tr>
                    <td align="right">Date of Order</td>
                    <td align="center"><input type="date" name="date"/></td>
                </tr>
                <tr>
                    <td align="right">Time of Order</td>
                    <td align="center"><input type="text" name="time"/></td>
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

        // Init date picket
        // var my_cal = new ng.Calendar({
        //     input: 'dateOfShow',
        //     date_format: 'Y-n-j'
        // });
    });
</script>

<%@include file="footer.jsp" %>

</body>
</html>
