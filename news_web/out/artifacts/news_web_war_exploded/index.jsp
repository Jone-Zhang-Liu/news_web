<%@ page language="java" import="com.hisoft.news.entity.News" pageEncoding="utf-8" %>
<%@ page import="com.hisoft.news.entity.Page" %>
<%@ page import="com.hisoft.news.entity.Topic" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>新闻中国</title>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
</head>
<%--<%--%>
<%--    List<News> list1 = (List<News>) session.getAttribute("list1");--%>
<%--    List<News> list2 = (List<News>) session.getAttribute("list2");--%>
<%--    List<News> list3 = (List<News>) session.getAttribute("list3");--%>
<%--    List<Topic> list4 = (List<Topic>) session.getAttribute("list4");--%>
<%--//    List<News> list5 = (List<News>) session.getAttribute("list5");--%>

<%--    if (list1 == null || list2 == null || list3 == null) {--%>
<%--        //url重写，可以在不使用cookie实现session共享--%>
<%--        response.sendRedirect(response.encodeURL("nls?action=index"));--%>
<%--    } else {--%>
<%--        Page newsPage = (Page) session.getAttribute("newsPage");--%>
<%--        List<News> newsList = newsPage.getNewsList();//分页查询到的新闻--%>
<%--        int currPageNo = newsPage.getCurrPageNo();--%>
<%--        int totalPageCount = newsPage.getTotalPageCount();--%>

<%--%>--%>
<c:if test="${empty sessionScope.list1 or empty sessionScope.list2 or empty sessionScope.list3}" var="list">
    <c:redirect url="nls?action=index" />
</c:if>
<c:if test="${not empty sessionScope.list1 or not empty sessionScope.list2 or not empty sessionScope.list3}" var="list2">
    <c:set scope="session" value="${newsPage}" var="newsPage"></c:set>
    <c:set scope="request" value="${newsPage.newsList}" var="newsList"></c:set>
    <c:set scope="request" value="${request.CurrPageNo}" var="currPageNo"></c:set>
    <c:set scope="request" value="${request.totalPageCount}" var="totalPageCount"></c:set>

<body>
<div id="header">
    <div id="top_login">
        <form action="lgs?action=login" method="post">
            <label> 登录名 </label>
            <input type="text" id="uname" name="uname"
<%--                   value="<%=request.getParameter("uname")==null?"":request.getParameter("uname")%>"--%>
                   value="${param.uname}"
                   class="login_input"/>
            <label> 密&#160;&#160;码 </label>
            <input type="password" id="upwd" name="upwd" class="login_input"/>
            <input type="submit" class="login_sub" value="登录"/>
            <label id="error" style="color: red">${error}

<%--                <%=request.getAttribute("error") == null ? "" : request.getAttribute("error")%>--%>
            </label>
        </form>
        <img src="images/friend_logo.gif" alt="Google" id="friend_logo"/></div>
    <div id="nav">
        <div id="logo"><img src="images/logo.jpg" alt="新闻中国"/></div>
        <div id="a_b01"><img src="images/a_b01.gif" alt=""/></div>
        <!--mainnav end-->
    </div>
</div>
<div id="container">
    <div class="sidebar">
        <h1><img src="images/title_1.gif" alt="国内新闻"/></h1>
        <div class="side_list">
            <ul>
<%--                <%--%>
<%--                    for (News news : list1) {--%>
<%--                %>--%>
                <c:forEach items="${sessionScope.list1}" var="news">
                <li><a href='nls?action=view_news&nid=${news.nid}'><b>${news.ntitle}
                </b></a></li>
                </c:forEach>
<%--                <%--%>
<%--                    }--%>
<%--                %>--%>
            </ul>
        </div>
        <h1><img src="images/title_2.gif" alt="国际新闻"/></h1>
        <div class="side_list">
            <ul>
<%--                <%--%>
<%--                    for (News news : list2) {--%>
<%--                %>--%>
    <c:forEach items="${sessionScope.list2}" var="news">
                <li><a href='nls?action=view_news&nid=${news.nid}'><b>${news.ntitle}
                </b></a></li>
    </c:forEach>
<%--                <%--%>
<%--                    }--%>
<%--                %>--%>
            </ul>
        </div>
        <h1><img src="images/title_3.gif" alt="娱乐新闻"/></h1>
        <div class="side_list">
            <ul>
<%--                <%--%>
<%--                    for (News news : list3) {--%>
<%--                %>--%>
    <c:forEach items="${sessionScope.list3}" var="news">
                <li><a href='nls?action=view_news&nid=${news.nid}'><b>${news.ntitle}
                </b></a></li>
    </c:forEach>
<%--                <%--%>
<%--                    }--%>
<%--                %>--%>
            </ul>
        </div>
    </div>
    <div class="main">
        <div class="class_type"><img src="images/class_type.gif" alt="新闻中心"/></div>
        <div class="content">
            <ul class="class_date">
                <li id='class_month'>
                    <c:forEach items="${applicationScope.topicList}" var="topic">
                        <%--                            <a href='nls?action=topic_news&tid=${topic.tid}'>--%>
                        <a href='javascript:;' id="${topic.tid}">
                            <b>${topic.tname}</b>
                        </a>
                    </c:forEach>
                </li>
            </ul>
            <ul class="classlist">

                    <%--<c:forEach items="${newsPage.newsList}" var="news" varStatus="v">
                        <li>
                            <a href='nls?action=view_news&nid=${news.nid}'>${news.ntitle}</a>
                            <span>
                                <fmt:formatDate value="${news.ncreateDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
                            </span>
                        </li>
                        <c:if test="${v.count%5==0}">
                            <li class='space'></li>
                        </c:if>
                    </c:forEach>

                    <p align="right">
                        当前页数:[${newsPage.currPageNo}/${newsPage.totalPageCount}]&nbsp;
                        <a href="nls?action=topic_news&pageNo=1&tid=${sessionScope.tid}">首页</a>
                        <c:if test="${newsPage.currPageNo gt 1}">
                            <a href="nls?action=topic_news&pageNo=${newsPage.currPageNo - 1}&tid=${sessionScope.tid}">上一页</a>
                        </c:if>

                        <c:if test="${newsPage.currPageNo lt newsPage.totalPageCount}">
                            <a href="nls?action=topic_news&pageNo=${newsPage.currPageNo + 1}&tid=${sessionScope.tid}">下一页</a>
                        </c:if>
                        <a href="nls?action=topic_news&pageNo=${newsPage.totalPageCount}&tid=${sessionScope.tid}">末页</a>
                    </p>--%>
            </ul>
        </div>
        <div class="picnews">
            <ul>
                <li><a href="#"><img src="images/Picture1.jpg" width="249" alt=""/> </a><a href="#">幻想中穿越时空</a></li>
                <li><a href="#"><img src="images/Picture2.jpg" width="249" alt=""/> </a><a href="#">国庆多变的发型</a></li>
                <li><a href="#"><img src="images/Picture3.jpg" width="249" alt=""/> </a><a href="#">新技术照亮都市</a></li>
                <li><a href="#"><img src="images/Picture4.jpg" width="249" alt=""/> </a><a href="#">群星闪耀红地毯</a></li>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
    <%--    <iframe src="index-elements/index_bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
    <%@include file="index-elements/index_bottom.html" %>
</div>
<script type="text/javascript">
    $(function () {
        function getNewsPage(tid, pageNo) {
            $.getJSON("nls", "action=indexAjax&tid=" + tid + "&pageNo=" + pageNo, function (data) {
                tid = data[0].tid;
                var newspage = data[1];
                var newsList = newspage.newsList;
                $(".main .classlist").html("");
                $(newsList).each(function (i) {
                    $(".main .classlist").append("<li><a href='nls?action=view_news&nid=" + this.nid + "'>" + this.ntitle + "</a>\n" +
                        "<span>" + this.ncreateDate + "</span></li>");
                    if ((i + 1) % 5 == 0) {
                        $(".main .classlist").append("<li class='space'></li>");
                    }
                });
                let $pagebar = $("<p align=\"right\">当前页数:[" + newspage.currPageNo + "/" + newspage.totalPageCount + "]&nbsp;</p>");
                $pagebar.append($("<a href=\"javascript:;\">首页</a>").click(function () {
                    getNewsPage(tid ,1);
                }))
                if (newspage.currPageNo > 1) {
                    $pagebar.append($("<a href=\" javascript:;\"> 上一页 </a>").click(function () {
                        getNewsPage(tid,newspage.currPageNo-1);
                    }));
                }
                if (newspage.currPageNo < newspage.totalPageCount) {
                    $pagebar.append($("<a href=\" javascript:;\"> 下一页 </a>").click(function () {
                        getNewsPage(tid,newspage.currPageNo+1);
                    }));
                }
                $pagebar.append($("<a href=\"javascript:;\">末页</a>").click(function () {
                    getNewsPage(tid ,newspage.totalPageCount);
                }))
                $(".main .classlist").append($pagebar);
            });
        }
        getNewsPage(0,1);
        $("#class_month").on("click","a",function () {
            getNewsPage($(this).attr("id") ,1);
        });
    });
</script>
</body>
</c:if>


</html>
	