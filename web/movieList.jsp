<%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 12/29/2017
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="dao.MovieDao,entity.MovieEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.PageEntity" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <title>Movie List</title>
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
                <div align="center"><b style="color:#c32916; font-size:35px">Movie List</b></div>
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
                            <td>title</td>
                            <td>duration</td>
                            <td>genre</td>
                            <td>director</td>
                            <td>stars</td>
                            <td>country</td>
                            <td>language</td>
                            <td>release_date</td>
                            <td>filming_location</td>
                            <td>runtime</td>
                            <td>aspect_ratio</td>
                            <td>description</td>
                            <td>poster</td>
                        </tr>
                        <%
                            MovieDao dao = new MovieDao();
                            PageEntity<MovieEntity> pageEntity = new PageEntity<>();
                            try {
                                int currentPage = (int) session.getAttribute("currentPage");
                                dao.getAll(pageEntity);
                            } catch (Exception e) {
                                e.printStackTrace();
                                pageEntity.setCurrentPage(1);
                                dao.getAll(pageEntity);
                            }
                            try {
                                int currentPage2 = Integer.parseInt(request.getParameter("currentPage"));
                                pageEntity.setCurrentPage(currentPage2);
                                dao.getAll(pageEntity);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                pageEntity.setCurrentPage(1);
                                dao.getAll(pageEntity);
                            }
                            for (MovieEntity entity : pageEntity.getPageData()) {%>
                        <tr>
                            <td><%=entity.getId() %>
                            </td>
                            <td><%=entity.getTitle() %>
                            </td>
                            <td><%=entity.getDuration() %>
                            </td>
                            <td><%=entity.getGenre() %>
                            </td>
                            <td><%=entity.getDirector() %>
                            </td>
                            <td><%=entity.getStars() %>
                            </td>
                            <td><%=entity.getCountry() %>
                            </td>
                            <td><%=entity.getLanguage() %>
                            </td>
                            <td><%=entity.getReleaseDate() %>
                            </td>
                            <td><%=entity.getFilmingLocation() %>
                            </td>
                            <td><%=entity.getRuntime() %>
                            </td>
                            <td><%=entity.getAspectRatio() %>
                            </td>
                            <td><%=entity.getDescription() %>
                            </td>
                            <td><img src="showPoster.jsp?title=<%=entity.getTitle()%>"
                                     style="height: 200px;width: auto"></td>
                        </tr>
                        <%
                            }
                        %>
                        <tr align="center">
                            <td colspan="14">
                                <div style="color: blue">
                                    Current Page <%=pageEntity.getCurrentPage()%>; Total
                                    Page(s) <%=pageEntity.getTotalPage()%><br>
                                </div>
                                <a href="<%=basePath%>movieList.jsp?currentPage=1">
                                    <input type="button" value="Start Page"></a>
                                <a href="<%=basePath%>movieList.jsp?currentPage=<%=pageEntity.getCurrentPage()-1%>">
                                    <input type="button" value="<<Prev"></a>
                                <a href="<%=basePath%>movieList.jsp?currentPage=<%=pageEntity.getCurrentPage()+1%>">
                                    <input type="button" value="Next>>"></a>
                                <a href="<%=basePath%>movieList.jsp?currentPage=<%=pageEntity.getTotalPage()%>">
                                    <input type="button" value="End Page"></a>
                                <form action="<%=basePath%>movieList.jsp" method="get"
                                      onsubmit="return onCheckCurrentPageMovieList()">
                                    <input type="number" name="currentPage" id="currentPageMovieList">
                                    <input type="submit">
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="ft">
    <%@include file="footer.jsp" %>
</div>

<script type="text/javascript">
    function onCheckCurrentPageMovieList() {
        var input = document.getElementById('currentPageMovieList');
        if (input.value.length === 0) {
            alert("Enter page to jump");
            input.focus();
            return false;
        } else {
            return true;
        }
    }
</script>
</body>
</html>