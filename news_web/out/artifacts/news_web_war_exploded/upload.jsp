<%--
  Created by IntelliJ IDEA.
  User: wuligang
  Date: 2021/2/22
  Time: 10:43
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
    <title>文件上传的表单</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="doupload.jsp">
    up主：<input type="text" name="uploader"/><br/>
    请选择文件：<input type="file" name="upfile"/><br/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
