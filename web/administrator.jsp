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
            bottom: 100px;
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
                <div align="center"><b style="color:#c32916; font-size:30px">For Administrator</b></div>
            </td>
        </tr>
    </table>
</div>

<%
    session.setAttribute("currentPage", 1);
%>
<div id="bd">
    <div id="side">
        <div style="font-size: 24px;">Navigation</div>
        <div style="font-size: 18px;"><a href="home.jsp">》》Back to home《《</a></div>
        <div style="font-size: 18px;"><a href="#userManagement">1. User Management</a><br></div>
        <div style="font-size: 18px;"><a href="#movieManagement">2. Movie Management</a><br></div>
        <div style="font-size: 18px;"><a href="#movieScheduleManagement">3. Movie Schedule Management</a><br></div>
        <div style="font-size: 18px;"><a href="#topMovieManagement">4. Top Movie Management</a><br></div>
        <div style="font-size: 18px;"><a href="#userReviewManagement">5. User Review Management</a><br></div>
        <div style="font-size: 18px;"><a href="#customerOrderManagement">6. Customer Order Management</a><br></div>
        <div style="font-size: 18px;"><a href="#satManagement">7. Seat, Auditorium, Theater Management</a><br></div>
    </div>
    <div id="main">
        <div id="content">
            <div id="feature" class="feature">
                <div align="center" id="userManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>1. User Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.UserManagement"
                              method="post"
                              enctype="multipart/form-data" onsubmit="return onCheckUserManagementForm()">
                            <table>
                                <tr>
                                    <td align="right">Email</td>
                                    <td align="center"><input type="email" id="emailUserManagement" name="email"></td>
                                    <td rowspan="6">
                                        <img src="" id="avatarImage"
                                             style="height: 150px;width: auto; background:#CCCCCC;"
                                             border="3"
                                             alt="Avatar Preview"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Username</td>
                                    <td align="center"><input type="text" id="usernameUserManagement" name="username">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Password</td>
                                    <td align="center"><input type="text" id="passwordUserManagement" name="password">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Gender</td>
                                    <td align="center">
                                        <select name="gender" id="genderUserManagement">
                                            <option value="Male" selected>Male</option>
                                            <option value="Female">Female</option>
                                            <option value="Secret">Secret</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Home Location</td>
                                    <td align="center"><input type="text" id="homeLocationUserManagement"
                                                              name="homeLocation"></td>
                                </tr>
                                <tr>
                                    <td align="right">Avatar</td>
                                    <td align="center"><input type="file" id="avatarUserManagement" name="avatar"></td>
                                </tr>
                                <tr>
                                    <td colspan="3">
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
                            <form action="${pageContext.request.contextPath}/servlet.administrator.PushMessage"
                                  method="post">
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
                    document.getElementById('avatarUserManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('avatarImage').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };

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
                        var genderUserManagement = document.getElementById('genderUserManagement');
                        var homeLocationUserManagement = document.getElementById('homeLocationUserManagement');
                        var avatarUserManagement = document.getElementById('avatarUserManagement');
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
                                if (genderUserManagement.value.length === 0) {
                                    alert('Enter gender');
                                    genderUserManagement.focus();
                                    return false;
                                }
                                if (homeLocationUserManagement.value.length === 0) {
                                    alert('Enter home location');
                                    homeLocationUserManagement.focus();
                                    return false;
                                }
                                if (avatarUserManagement.value.length === 0) {
                                    alert('Upload avatar');
                                    avatarUserManagement.focus();
                                    return false;
                                }
                                break;
                            case 'update':
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
                                if (genderUserManagement.value.length === 0) {
                                    alert('Enter gender');
                                    genderUserManagement.focus();
                                    return false;
                                }
                                if (homeLocationUserManagement.value.length === 0) {
                                    alert('Enter home location');
                                    homeLocationUserManagement.focus();
                                    return false;
                                }
                                if (avatarUserManagement.value.length === 0) {
                                    alert('Upload avatar');
                                    avatarUserManagement.focus();
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

                <div align="center" id="movieManagement" class="MovieManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>2. Movie Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieManagement"
                              method="post"
                              enctype="multipart/form-data" onsubmit="return onCheckMovieManagementForm()">
                            <table align="center">
                                <tr>
                                    <td align="right">Title</td>
                                    <td align="center"><input type="text" name="title" id="titleMovieManagement"></td>
                                    <td rowspan="13">
                                        <img src="" id="posterImage"
                                             style="height: auto;width: 200px; background:#CCCCCC;"
                                             border="3"
                                             alt="Poster Preview"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Duration</td>
                                    <td align="center"><input type="text" name="duration" id="durationMovieManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Genre</td>
                                    <td align="center"><input type="text" name="genre" id="genreMovieManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Director</td>
                                    <td align="center"><input type="text" name="director" id="directorMovieManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Stars</td>
                                    <td align="center"><input type="text" name="stars" id="starsMovieManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Country</td>
                                    <td align="center"><input type="text" name="country" id="countryMovieManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Language</td>
                                    <td align="center"><input type="text" name="language" id="languageMovieManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Release Date</td>
                                    <td align="center"><input type="text" name="releaseDate"
                                                              id="releaseDateMovieManagement"/></td>
                                </tr>
                                <tr>
                                    <td align="right">Filming Location</td>
                                    <td align="center"><input type="text" name="filmingLocation"
                                                              id="filmingLocationMovieManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Runtime</td>
                                    <td align="center"><input type="text" name="runtime" id="runtimeMovieManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Aspect Ratio</td>
                                    <td align="center"><input type="text" name="aspectRatio"
                                                              id="aspectRatioMovieManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Description</td>
                                    <td align="center"><input type="text" name="description"
                                                              id="descriptionMovieManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Poster</td>
                                    <td align="center"><input type="file" name="poster" id="posterMovieManagement"></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center">
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
                    document.getElementById('posterMovieManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('posterImage').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };

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

                <div align="center" id="movieScheduleManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>3. Movie Schedule Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.MovieScheduleManagement"
                              method="post"
                              onsubmit="return onCheckMovieScheduleManagementForm()">
                            <table align="center">
                                <tr>
                                    <td align="right">Movie ID</td>
                                    <td align="center"><input type="number" name="movieId"
                                                              id="movieIdMovieScheduleManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Theater ID</td>
                                    <td align="center"><input type="number" name="theaterId"
                                                              id="theaterIdMovieScheduleManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Auditorium ID</td>
                                    <td align="center"><input type="number" name="auditoriumId"
                                                              id="auditoriumIdMovieScheduleManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Price</td>
                                    <td align="center"><input type="number" step="0.01" name="price"
                                                              id="priceMovieScheduleManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Date of Show</td>
                                    <td align="center"><input type="date" name="dateOfShow"
                                                              id="dateOfShowMovieScheduleManagement"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Time of Show</td>
                                    <td align="center"><input type="text" id="timePicker" value="10:25:00"
                                                              name="timeOfShow"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input type="radio" name="movieScheduleOperation" value="add"
                                               checked="checked"/>Add
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
                </script>

                <div align="center" id="topMovieManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>4. Top Movie Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.TopMovieManagement"
                              method="post" enctype="multipart/form-data"
                              onsubmit="return onCheckTopMovieManagementForm()">
                            <table align="center">
                                <tr>
                                    <td align="right">Movie Title 1</td>
                                    <td align="center"><input type="text" name="title1" id="title1TopMovieManagement">
                                    </td>
                                    <td align="right">Poster 1</td>
                                    <td align="center"><input type="file" id="poster1TopMovieManagement" name="poster1">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Title 2</td>
                                    <td align="center"><input type="text" name="title2" id="title2TopMovieManagement">
                                    </td>
                                    <td align="right">Poster 2</td>
                                    <td align="center"><input type="file" id="poster2TopMovieManagement" name="poster2">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Title 3</td>
                                    <td align="center"><input type="text" name="title3" id="title3TopMovieManagement">
                                    </td>
                                    <td align="right">Poster 3</td>
                                    <td align="center"><input type="file" id="poster3TopMovieManagement" name="poster3">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Title 4</td>
                                    <td align="center"><input type="text" name="title4" id="title4TopMovieManagement">
                                    </td>
                                    <td align="right">Poster 4</td>
                                    <td align="center"><input type="file" id="poster4TopMovieManagement" name="poster4">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Title 5</td>
                                    <td align="center"><input type="text" name="title5" id="title5TopMovieManagement">
                                    </td>
                                    <td align="right">Poster 5</td>
                                    <td align="center"><input type="file" id="poster5TopMovieManagement" name="poster5">
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <img src="" id="poster1Image"
                                             style="height: 85px;width: 150px; background:#CCCCCC;"
                                             border="3"
                                             alt="Poster 1 Preview"/>
                                    </td>
                                    <td colspan="2">
                                        <img src="" id="poster2Image"
                                             style="height: 85px;width: 150px; background:#CCCCCC;"
                                             border="3"
                                             alt="Poster 2 Preview"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <img src="" id="poster3Image"
                                             style="height: 85px;width: 150px; background:#CCCCCC;"
                                             border="3"
                                             alt="Poster 3 Preview"/>
                                    </td>
                                    <td colspan="2">
                                        <img src="" id="poster4Image"
                                             style="height: 85px;width: 150px; background:#CCCCCC;"
                                             border="3"
                                             alt="Poster 4 Preview"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4">
                                        <img src="" id="poster5Image"
                                             style="height: 85px;width: 150px; background:#CCCCCC;"
                                             border="3"
                                             alt="Poster 5 Preview"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" align="center">
                                        <input type="radio" name="topMovieOperation" value="update"
                                               checked="checked"/>Update
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
                    <br>
                </div>
                <script type="text/javascript">
                    document.getElementById('poster1TopMovieManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('poster1Image').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };
                    document.getElementById('poster2TopMovieManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('poster2Image').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };
                    document.getElementById('poster3TopMovieManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('poster3Image').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };
                    document.getElementById('poster4TopMovieManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('poster4Image').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };
                    document.getElementById('poster5TopMovieManagement').onchange = function () {
                        var imgFile = this.files[0];
                        var fr = new FileReader();
                        fr.onload = function () {
                            document.getElementById('poster5Image').src = fr.result;
                        };
                        fr.readAsDataURL(imgFile);
                    };

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
                        var poster1TopMovieManagement = document.getElementById('poster1TopMovieManagement');
                        var poster2TopMovieManagement = document.getElementById('poster2TopMovieManagement');
                        var poster3TopMovieManagement = document.getElementById('poster3TopMovieManagement');
                        var poster4TopMovieManagement = document.getElementById('poster4TopMovieManagement');
                        var poster5TopMovieManagement = document.getElementById('poster5TopMovieManagement');

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
                                if (poster1TopMovieManagement.value.length === 0) {
                                    poster1TopMovieManagement.focus();
                                    alert('Upload poster 1');
                                    return false;
                                }
                                if (poster2TopMovieManagement.value.length === 0) {
                                    poster2TopMovieManagement.focus();
                                    alert('Upload poster 2');
                                    return false;
                                }
                                if (poster3TopMovieManagement.value.length === 0) {
                                    poster3TopMovieManagement.focus();
                                    alert('Upload poster 3');
                                    return false;
                                }
                                if (poster4TopMovieManagement.value.length === 0) {
                                    poster4TopMovieManagement.focus();
                                    alert('Upload poster 4');
                                    return false;
                                }
                                if (poster5TopMovieManagement.value.length === 0) {
                                    poster5TopMovieManagement.focus();
                                    alert('Upload poster 5');
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
                                if (poster1TopMovieManagement.value.length === 0) {
                                    poster1TopMovieManagement.focus();
                                    alert('Upload poster 1');
                                    return false;
                                }
                                if (poster2TopMovieManagement.value.length === 0) {
                                    poster2TopMovieManagement.focus();
                                    alert('Upload poster 2');
                                    return false;
                                }
                                if (poster3TopMovieManagement.value.length === 0) {
                                    poster3TopMovieManagement.focus();
                                    alert('Upload poster 3');
                                    return false;
                                }
                                if (poster4TopMovieManagement.value.length === 0) {
                                    poster4TopMovieManagement.focus();
                                    alert('Upload poster 4');
                                    return false;
                                }
                                if (poster5TopMovieManagement.value.length === 0) {
                                    poster5TopMovieManagement.focus();
                                    alert('Upload poster 5');
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
                <div align="center" id="userReviewManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>5. User Review Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.UserReviewManagement"
                              method="post"
                              onsubmit="return onCheckUserReviewManagementForm()">
                            <table align="center">
                                <tr>
                                    <td align="right">User Email</td>
                                    <td align="left"><input type="email" name="email" id="emailUserReviewManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Title</td>
                                    <td align="left"><input type="text" name="movieTitle"
                                                            id="movieTitleUserReviewManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Score</td>
                                    <td align="left"><input type="number" name="score" id="scoreUserReviewManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Title</td>
                                    <td align="left"><input type="text" name="userReviewTitle"
                                                            id="userReviewTitleUserReviewManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Text</td>
                                    <td align="left"><textarea name="text"
                                                               style="resize: none; width: 450px; height: 100px"
                                                               id="textUserReviewManagement"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Contain spoilers?</td>
                                    <td align="left">
                                        <input type="radio" name="isSpoilers" value="true" checked="checked"/>Yes&nbsp;&nbsp;
                                        <input type="radio" name="isSpoilers" value="false"/>No
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Date of Review</td>
                                    <td align="left"><input type="date" name="date" id="dateUserReviewManagement"/></td>
                                </tr>
                                <tr>
                                    <td align="right">Time of Review</td>
                                    <td align="left"><input type="text" id="timePicker2" value="10:25:00" name="time"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center">
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
                <script type="text/javascript">
                    function onCheckUserReviewManagementForm() {
                        var radios = document.getElementsByName('userReviewOperation');
                        var radioChecked;
                        for (var i = 0; i < radios.length; i++) {
                            if (radios[i].checked) {
                                radioChecked = radios[i];
                                break;
                            }
                        }
                        var emailUserReviewManagement = document.getElementById('emailUserReviewManagement');
                        var movieTitleUserReviewManagement = document.getElementById('movieTitleUserReviewManagement');
                        var scoreUserReviewManagement = document.getElementById('scoreUserReviewManagement');
                        var userReviewTitleUserReviewManagement = document.getElementById('userReviewTitleUserReviewManagement');
                        var textUserReviewManagement = document.getElementById('textUserReviewManagement');
                        var dateUserReviewManagement = document.getElementById('dateUserReviewManagement');
                        var timePicker2 = document.getElementById('timePicker2');

                        switch (radioChecked.value) {
                            case 'add':
                                if (emailUserReviewManagement.value.length === 0) {
                                    emailUserReviewManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (movieTitleUserReviewManagement.value.length === 0) {
                                    movieTitleUserReviewManagement.focus();
                                    alert('Enter movie title');
                                    return false;
                                }
                                if (scoreUserReviewManagement.value.length === 0) {
                                    scoreUserReviewManagement.focus();
                                    alert('Enter score');
                                    return false;
                                }
                                if (userReviewTitleUserReviewManagement.value.length === 0) {
                                    userReviewTitleUserReviewManagement.focus();
                                    alert('Enter review title');
                                    return false;
                                }
                                if (textUserReviewManagement.value.length === 0) {
                                    textUserReviewManagement.focus();
                                    alert('Enter text');
                                    return false;
                                }
                                if (dateUserReviewManagement.value.length === 0) {
                                    dateUserReviewManagement.focus();
                                    alert('Enter date');
                                    return false;
                                }
                                if (timePicker2.value.length === 0) {
                                    timePicker2.focus();
                                    alert('Enter time');
                                    return false;
                                }
                                break;
                            case 'update':
                                if (emailUserReviewManagement.value.length === 0) {
                                    emailUserReviewManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (movieTitleUserReviewManagement.value.length === 0) {
                                    movieTitleUserReviewManagement.focus();
                                    alert('Enter movie title');
                                    return false;
                                }
                                if (scoreUserReviewManagement.value.length === 0) {
                                    scoreUserReviewManagement.focus();
                                    alert('Enter score');
                                    return false;
                                }
                                if (userReviewTitleUserReviewManagement.value.length === 0) {
                                    userReviewTitleUserReviewManagement.focus();
                                    alert('Enter review title');
                                    return false;
                                }
                                if (textUserReviewManagement.value.length === 0) {
                                    textUserReviewManagement.focus();
                                    alert('Enter text');
                                    return false;
                                }
                                if (dateUserReviewManagement.value.length === 0) {
                                    dateUserReviewManagement.focus();
                                    alert('Enter date');
                                    return false;
                                }
                                if (timePicker2.value.length === 0) {
                                    timePicker2.focus();
                                    alert('Enter time');
                                    return false;
                                }
                                break;
                            case 'delete':
                                if (emailUserReviewManagement.value.length === 0) {
                                    emailUserReviewManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (movieTitleUserReviewManagement.value.length === 0) {
                                    movieTitleUserReviewManagement.focus();
                                    alert('Enter movie title');
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

                <div align="center" id="customerOrderManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>6. Customer Order Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.CustomerOrderManagement"
                              method="post"
                              onsubmit="return onCheckCustomerOrderManagementForm()">
                            <table align="center">
                                <tr>
                                    <td align="right">User ID (Optional)</td>
                                    <td align="center"><input type="number" name="userId"
                                                              id="userIdCustomerOrderManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Email</td>
                                    <td align="center"><input type="email" name="email"
                                                              id="emailCustomerOrderManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Movie Schedule ID</td>
                                    <td align="center"><input type="number" name="movieScheduleId"
                                                              id="movieScheduleIdCustomerOrderManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Ticket Amount</td>
                                    <td align="center"><input type="number" name="ticketAmount"
                                                              id="ticketAmountCustomerOrderManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Date of Order</td>
                                    <td align="center"><input type="date" name="date" id="dateCustomerOrderManagement"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Time of Order</td>
                                    <td align="center"><input type="text" name="time" id="timeCustomerOrderManagement"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" align="center">
                                        <input type="radio" name="orderOperation" value="takeOrder" checked="checked"/>Take
                                        Order
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
                    function onCheckCustomerOrderManagementForm() {
                        var radios = document.getElementsByName('orderOperation');
                        var radioChecked;
                        for (var i = 0; i < radios.length; i++) {
                            if (radios[i].checked) {
                                radioChecked = radios[i];
                                break;
                            }
                        }
                        // var userIdCustomerOrderManagement = document.getElementById('userIdCustomerOrderManagement');
                        var emailCustomerOrderManagement = document.getElementById('emailCustomerOrderManagement');
                        var movieScheduleIdCustomerOrderManagement = document.getElementById('movieScheduleIdCustomerOrderManagement');
                        var ticketAmountCustomerOrderManagement = document.getElementById('ticketAmountCustomerOrderManagement');
                        var dateCustomerOrderManagement = document.getElementById('dateCustomerOrderManagement');
                        var timeCustomerOrderManagement = document.getElementById('timeCustomerOrderManagement');

                        switch (radioChecked.value) {
                            case 'takeOrder':
                                if (emailCustomerOrderManagement.value.length === 0) {
                                    emailCustomerOrderManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (movieScheduleIdCustomerOrderManagement.value.length === 0) {
                                    movieScheduleIdCustomerOrderManagement.focus();
                                    alert('Enter movie schedule id');
                                    return false;
                                }
                                if (ticketAmountCustomerOrderManagement.value.length === 0) {
                                    ticketAmountCustomerOrderManagement.focus();
                                    alert('Enter ticket amount');
                                    return false;
                                }
                                break;
                            case 'pay':
                                if (emailCustomerOrderManagement.value.length === 0) {
                                    emailCustomerOrderManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (dateCustomerOrderManagement.value.length === 0) {
                                    dateCustomerOrderManagement.focus();
                                    alert('Enter date');
                                    return false;
                                }
                                if (timeCustomerOrderManagement.value.length === 0) {
                                    timeCustomerOrderManagement.focus();
                                    alert('Enter time');
                                    return false;
                                }
                                break;
                            case 'use':
                                if (emailCustomerOrderManagement.value.length === 0) {
                                    emailCustomerOrderManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (dateCustomerOrderManagement.value.length === 0) {
                                    dateCustomerOrderManagement.focus();
                                    alert('Enter date');
                                    return false;
                                }
                                if (timeCustomerOrderManagement.value.length === 0) {
                                    timeCustomerOrderManagement.focus();
                                    alert('Enter time');
                                    return false;
                                }
                                break;
                            case 'delete':
                                if (emailCustomerOrderManagement.value.length === 0) {
                                    emailCustomerOrderManagement.focus();
                                    alert('Enter email');
                                    return false;
                                }
                                if (dateCustomerOrderManagement.value.length === 0) {
                                    dateCustomerOrderManagement.focus();
                                    alert('Enter date');
                                    return false;
                                }
                                if (timeCustomerOrderManagement.value.length === 0) {
                                    timeCustomerOrderManagement.focus();
                                    alert('Enter time');
                                    return false;
                                }
                                break;
                            case 'getAll':
                                break;
                            case 'getJson':
                                break;
                            default:
                        }
                    }
                </script>

                <div align="center" id="satManagement">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>7. Seat, Auditorium, Theater Management</h2>
                        </legend>
                        <form action="${pageContext.request.contextPath}/servlet.administrator.SATManagement"
                              method="post"
                              onsubmit="return onCheckSATManagementForm()">
                            <table align="center">
                                <tr>
                                    <td align="right">Theater ID</td>
                                    <td align="center"><input type="number" name="theaterId"
                                                              id="theaterIdSATManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Theater Name</td>
                                    <td align="center"><input type="email" name="theaterName"
                                                              id="theaterNameSATManagement">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Auditorium ID</td>
                                    <td align="center"><input type="number" name="auditoriumId"
                                                              id="auditoriumIdSATManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Seat ID</td>
                                    <td align="center"><input type="number" name="seatId" id="seatIdSATManagement"></td>
                                </tr>
                                <tr>
                                    <td align="right">Seat Availability</td>
                                    <td align="center">
                                        <select name="seatAvailability" id="seatAvailabilitySATManagement">
                                            <option value="NotSelected" selected>Not selected</option>
                                            <option value="Selected">Selected</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Get All Theaters<input type="radio" name="satOperation"
                                                                             value="getAllTheater" checked="checked"/>
                                    </td>
                                    <td rowspan="5" align="center">
                                        <input type="submit" value="Submit">
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Get All Auditorium<input type="radio" name="satOperation"
                                                                               value="getAllAuditorium"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Get All Seat of Auditorium<input type="radio" name="satOperation"
                                                                                       value="getAllSeatOfAuditorium"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Get Seat of Auditorium Json<input type="radio" name="satOperation"
                                                                                        value="getSeatOfAuditoriumJson"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right">Set Seat Availability<input type="radio" name="satOperation"
                                                                                  value="setSeatAvailability"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </fieldset>
                </div>
                <br>
                <script type="text/javascript">
                    function onCheckSATManagementForm() {
                        var radios = document.getElementsByName('satOperation');
                        var radioChecked;
                        for (var i = 0; i < radios.length; i++) {
                            if (radios[i].checked) {
                                radioChecked = radios[i];
                                break;
                            }
                        }

                        var theaterIdSATManagement = document.getElementById('theaterIdSATManagement');
                        var theaterNameSATManagement = document.getElementById('theaterNameSATManagement');
                        var auditoriumIdSATManagement = document.getElementById('auditoriumIdSATManagement');
                        var seatIdSATManagement = document.getElementById('seatIdSATManagement');

                        switch (radioChecked.value) {
                            case 'getAllTheater':
                                break;
                            case 'getAllAuditorium':
                                break;
                            case 'getAllSeatOfAuditorium':
                                if (auditoriumIdSATManagement.value.length === 0) {
                                    auditoriumIdSATManagement.focus();
                                    alert('Enter auditorium id');
                                    return false;
                                }
                                break;
                            case 'getSeatOfAuditoriumJson':
                                if (auditoriumIdSATManagement.value.length === 0) {
                                    auditoriumIdSATManagement.focus();
                                    alert('Enter auditorium id');
                                    return false;
                                }
                                break;
                            case 'setSeatAvailability':
                                if (auditoriumIdSATManagement.value.length === 0) {
                                    auditoriumIdSATManagement.focus();
                                    alert('Enter auditorium id');
                                    return false;
                                }
                                break;
                            default:
                        }
                    }
                </script>

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
