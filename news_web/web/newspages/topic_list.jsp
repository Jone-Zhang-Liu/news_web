<%@ page import="com.hisoft.news.entity.Topic" %>
<%@ page import="java.util.List" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>编辑主题--管理后台</title>
    <script type="text/javascript">
        function check() {
            var tname = document.getElementById("tname");

            if (tname.value == "") {
                alert("请输入主题名称！！");
                tname.focus();
                return false;
            }
            return true;
        }
    </script>
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
</head>
<body>
<div id="main">
    <div>
        <%--	    <iframe src="console_element/top.html" scrolling="no" frameborder="0" width="947px" height="180px"></iframe>--%>
        <%@ include file="console_element/top.html" %>
    </div>
    <div id="opt_list">
        <%--      	<iframe src="console_element/left.html" scrolling="no" frameborder="0" width="130px"></iframe>--%>
        <%@ include file="console_element/left.html" %>
    </div>
    <div id="opt_area">
        <ul class="classlist">

            <%-- <c:if test="${not empty applicationScope.topicList}">
                 <c:forEach items="${applicationScope.topicList}" var="topic">
                     <li>${topic.tname}
                         &nbsp;<a href="newspages/topic_modify.jsp?tname=${topic.tname}&tid=${topic.tid}">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;
                         <a href="javascript:;" onclick="del(${topic.tid})">删除</a>
                     </li>
                 </c:forEach>
             </c:if>--%>
            <%-- <c:if test="${empty applicationScope.topicList}">
                 <c:redirect url="../topic?action=list"/>
             </c:if>--%>

        </ul>
    </div>
    <%--	  <iframe src="console_element/bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
    <%@ include file="console_element/bottom.html" %>
</div>
<script type="text/javascript">
    function del(tid) {
        if (confirm("确定要删除吗？")) {
            location.href = "topic?tid=" + tid + "&action=del";
        }
    }


    $(function () {

        $.getJSON("topic?action=list",function (data) {
            $(data).each(function () {
                $("#opt_area>ul").append("<li>"+this.tname+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<a href=\"newspages/topic_modify.jsp?tname="+this.tname+"&tid="+this.tid+"\">修改</a> &nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "<a href=\"javascript:;\" onclick=\"del("+this.tid+")\">删除</a>\n" +
                    "</li>");
            })
        });
    })
</script>
</body>
</html>	