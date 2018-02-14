<%@ page import="util.MobileTerminalUtil" %><%--
  Created by IntelliJ IDEA.
  User: Johnny
  Date: 2/14/2018
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String email = request.getParameter("email");
    String ip = MobileTerminalUtil.userEmail2Ip.get(email);

    MobileTerminalUtil.userEmail2Ip.remove(email);

    int port = MobileTerminalUtil.ip2RemotePort.get(ip);
    MobileTerminalUtil.ip2RemotePort.remove(ip);

    System.err.println("Delete online user: " + email + ", [" + ip + ":" + port + "]");
%>
<script type="text/javascript">
    if (window.confirm('Go back to page [Online User List]?')) {
        window.history.go(-1);
    }
</script>
