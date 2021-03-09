<%@ page language="java" import="com.hisoft.news.entity.News" pageEncoding="utf-8" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加主题--管理后台</title>
    <link href="css/admin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
</head>
<%--include指令是在jsp翻译阶段引入：被引入的页面改变，不会立即更新，静态引入--%>
<%--<%@ include file="loginControll.jsp"%>--%>
<%--include动作是在请求发送的时候引入：被引入的页面改变，会立即更新，动态引入--%>
<jsp:include page="loginControll.jsp"></jsp:include>
<body>
<div id="main">
    <div>
        <%--	    <iframe src="newspages/console_element/top.html" scrolling="no" frameborder="0" width="947px" height="180px"></iframe>--%>
        <%@ include file="console_element/top.html" %>
    </div>
    <div id="opt_list">
        <%--	 	<iframe src="newspages/console_element/left.html" scrolling="no" frameborder="0" width="130px"></iframe>--%>
        <%@ include file="console_element/left.html" %>
    </div>
    <div id="opt_area">
        <ul class="classlist">

            <%-- <c:if test="${not empty newsList}">
                 <c:forEach items="${newsList}" var="news" varStatus="v">
                     <li>${news.ntitle}
                         <span> 作者：${news.nauthor} &#160;&#160;&#160;&#160;
                               <a href='news_modify.jsp'>修改</a>&#160;&#160;&#160;&#160;
                               <a href='javascript:;' onclick="del(${news.nid});">删除</a>
                         </span>
                     </li>
                     <c:if test="${v.count%5==0}">
                         <li class='space'></li>
                     </c:if>
                 </c:forEach>
             </c:if>--%>
            <%-- <c:if test="${empty newsList}">
                 <c:redirect url="../nls?action=list"/>
             </c:if>--%>
        </ul>
        <p align="right"> 当前页数:[1/3]&nbsp; <a href="#">下一页</a> <a href="#">末页</a></p>
    </div>
    <%@ include file="console_element/bottom.html" %>
</div>
<script type="text/javascript">

    $(function () {
        // alert(111);
        /*$.ajax({
            "url":"nls?action=list",
            "type":"get",
            "dataType":"json",
            "success":function (data) {
                // alert(data);
                $(data).each(function (i) {//索引
                    $("#opt_area>ul").append("<li>"+this.ntitle+"<span> 作者："+this.nauthor+" &#160;&#160;&#160;&#160;\n" +
                        "<a href='news_modify.jsp'>修改</a>&#160;&#160;&#160;&#160;\n" +
                        "<a href='javascript:;' onclick=\"del("+this.nid+");\">删除</a>\n" +
                        "\t\t\t</span>\n" +
                        "</li>");
                    if((i+1)%5==0){
                        $("#opt_area>ul").append("<li class='space'></li>");
                    }
                })
            }
        });*/

        // $.get("news","action=list",function (data) {
        //     $("#opt_area>ul").append(data);
        // },"html");

        // $("#opt_area>ul").load("news","action=list");

        function initNewsList() {
            $.getJSON("nls","action=list",function (data) {
                // alert(data);
                $(data).each(function (i) {//索引
                    $("#opt_area>ul").append("<li><a href='nls?action=view_news&nid="+this.nid+"'>"+this.ntitle+"</a>"+"<span> 作者："+this.nauthor+" &#160;&#160;&#160;&#160;\n" +
                        "<a href='newspages/news_modify.jsp'>修改</a>&#160;&#160;&#160;&#160;\n" +
                        "<a href='javascript:;' onclick=\"del("+this.nid+");\">删除</a>\n" +
                        "\t\t\t</span>\n" +
                        "</li>");
                    if((i+1)%5==0){
                        $("#opt_area>ul").append("<li class='space'></li>");
                    }
                })
            });
        }
        initNewsList();//页面加载即执行

        function initTopicList() {
            $.getJSON("topic?action=list",function (data) {
                $("#opt_area").html("").append("<ul></ul>");
                $(data).each(function () {
                    $("#opt_area>ul").append("<li>"+this.tname+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<a href=\"newspages/topic_modify.jsp\" class='tomodifylink' id='"+this.tid+":"+this.tname+"'>修改</a> &nbsp;&nbsp;&nbsp;&nbsp;\n" +
                        "<a href=\"admin.jsp\" class='deletelink' tid=\""+this.tid+"\">删除</a>\n" +
                        "</li>");
                })
            });
        }
        $("#opt_list").on("click","a:eq(3)",function () {//单击编辑主题显示主题列表
            initTopicList();
        });

        $("#opt_list").on("click","a:eq(2)",function () {//单击添加主题显示添加主题页面
            $("#opt_area").load("newspages/topic_add.jsp #opt_area>*");//加载topic_add.jsp中id为opt_area的元素下的所有内容
        });
        $("#opt_area").on("click","#topicsubmit",function () {//添加主题
            $.post("topic",$("#opt_area form").serialize(),function (data) {
                //[{\"code\":\"success\"},{\"topics\":"+JSON.toJSONString(topicList)+"}]
                if(data.code=="exists"){
                    alert("主题已存在");
                }else if(data.code == "error"){
                    alert("主题添加失败");
                }else{
                    alert("主题添加成功");
                    initTopicList();
                }
            },"json");
        });

        $("#opt_area").on("click","ul>li .tomodifylink",function () {//显示修改主题页面
            var arr = this.id.split(":");//包含tid和tname值
            $("#opt_area").load("newspages/topic_modify.jsp #opt_area","tid="+arr[0]+"&tname="+arr[1]);
        });

        $("#opt_area").on("onclick","#updateTopic",function () {//修改主题
            $.post("topic",$("#opt_area form").serialize(),function(data) {
                if(data.code == "success"){
                    alert("修改成功！！");
                    initTopicList();
                }else if(data.code == "error"){
                    alert("修改失败");
                }else{
                    alert("主题已存在");
                }
            },"json");

        });


        $("#opt_area").on("onclick","ul>li .deletelink",function () {//删除主题
            if (confirm("你确定要删除吗？")) {
                var $p = $(this).parent();
                $.getJSON("topic","action=del&tid="+$(this).attr("tid"),function (data) {
                    if(data.code == "cannot"){
                        alert("主题下有新闻，不能删除");
                    }else if(data.code == "error"){
                        alert("删除失败");
                    }else{
                        alert("删除成功");
                        $p.remove();
                    }
                });
            }
        });

    })

    function del(nid) {
        if(confirm("你确定要删除吗？")){
            location.href="nls?action=del&nid="+nid;
        }
    }
</script>
</body>
</html>

	