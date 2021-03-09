d<%--
  Created by IntelliJ IDEA.
  User: wuligang
  Date: 2021/2/2
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户注册</title>
</head>
<body>
<p style="color: red"><%=request.getAttribute("error") == null?"":request.getAttribute("error")%></p>
<form action="lgs?action=register" method="post">
    用户名：<input type="text" name="uname" value="<%=request.getParameter("uname") == null?"":request.getParameter("uname")%>"/><br/>
    密码：<input type="password" name="upwd" value="<%=request.getParameter("upwd") == null?"":request.getParameter("upwd")%>"/><br/>
    再次输入密码：<input type="password" name="reupwd" value="<%=request.getParameter("reupwd") == null?"":request.getParameter("reupwd")%>"/><br/>
    <input type="submit" value="注册"/><br/>
</form>
</body>
</html>
