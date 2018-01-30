<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/24/2017
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<html>
<head>
    <title>Home | Movie Ticket Management System</title>
    <link rel="shortcut icon" href="res/drawable/tab_icon_16x16.ico" type="image/x-icon"/>
    <head>
        <title>MTMS: Administrator</title>
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
                <div align="center"><b style="color:#115dff; font-size:30px">Navigation</b></div>
            </td>
        </tr>
    </table>
</div>

<div id="bd">
    <div id="main">
        <div id="content">
            <div id="feature" class="feature">
                <div align="center">
                    <fieldset style="width:fit-content; height:fit-content;">
                        <legend>
                            <h2>Navigation Panel</h2>
                        </legend>
                        <table align="center">
                            <tr>
                                <td align="left"><a href="administrator.jsp"><b style="font-size:20px">1. Administrator
                                    click here</b></a></td>
                            </tr>
                            <tr>
                                <td align="left"><a href="customer.jsp"><b style="font-size:20px">2. Customer click
                                    here</b></a></td>
                            </tr>
                            <tr>
                                <td align="left"><a href="seller.jsp"><b style="font-size:20px">3. Seller click here</b></a>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>

<div id="ft">
    <%@include file="footer.jsp" %>
</div>
</body>
</html>
