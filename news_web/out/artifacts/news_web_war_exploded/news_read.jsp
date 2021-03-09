<%@ page language="java" import="com.hisoft.news.entity.News" pageEncoding="utf-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hisoft.news.entity.Comments" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="css/read.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
    <script type="text/javascript">
        function check() {
            var cauthor = document.getElementById("cauthor");
            var content = document.getElementById("ccontent");
            if (cauthor.value == "") {
                alert("用户名不能为空！！");
                return false;
            } else if (content.value == "") {
                alert("评论内容不能为空！！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div id="header">
    <div id="top_login">
      <form action="lgs" method="post">
        <label> 登录名 </label>
        <input type="text" id="uname" value="" class="login_input"/>
        <label> 密&#160;&#160;码 </label>
        <input type="password" id="upwd" value="" class="login_input"/>
        <input type="submit" class="login_sub" value="登录" />
        <label id="error"> </label>
      </form>
        <a href="index.jsp" class="login_link">返回首页</a> <img src="images/friend_logo.gif" alt="Google"
                                                                id="friend_logo"/></div>
    <div id="nav">
        <div id="logo"><img src="images/logo.jpg" alt="新闻中国"/></div>
        <div id="a_b01"><img src="images/a_b01.gif" alt=""/></div>
        <!--mainnav end-->
    </div>
</div>

<%--<%--%>
<%--    News n = (News) request.getAttribute("news");--%>
<%--    List<News> list1 = (List<News>) session.getAttribute("list1");--%>
<%--    List<News> list2 = (List<News>) session.getAttribute("list2");--%>
<%--    List<News> list3 = (List<News>) session.getAttribute("list3");--%>
<%--%>--%>
<div id="container">
    <div class="sidebar">
        <h1><img src="images/title_1.gif" alt="国内新闻"/></h1>
        <div class="side_list">
            <ul>
                <c:forEach items="${list1}" var="news">
                    <li>
                        <a href='nls?action=view_news&nid=${news.nid}'>
                            <b>${news.ntitle}</b>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <h1><img src="images/title_2.gif" alt="国际新闻"/></h1>
        <div class="side_list">
            <ul>
                <c:forEach items="${list2}" var="news">
                    <li>
                        <a href='nls?action=view_news&nid=${news.nid}'>
                            <b>${news.ntitle}</b>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <h1><img src="images/title_3.gif" alt="娱乐新闻"/></h1>
        <div class="side_list">
            <ul>
                <c:forEach items="${list3}" var="news">
                    <li>
                        <a href='nls?action=view_news&nid=${news.nid}'>
                            <b>${news.ntitle}</b>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="main">
        <div class="class_type"><img src="images/class_type.gif" alt="新闻中心"/></div>
        <div class="content">
            <ul class="classlist">
                <table width="100%" align="center">
                    <tr width="100%">
                        <td colspan="2" align="center">${news.ntitle}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr/>
                        </td>
                    </tr>
                    <tr>
                        <td align="center">${news.ncreateDate}
                        </td>
                        <td align="left">${news.nauthor}</td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"></td>
                    </tr>
                    <tr>
                        <td colspan="2"> 　
                            ${news.ncontent}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr/>
                        </td>
                    </tr>
                </table>
            </ul>
            <ul class="classlist">
                <table width="100%" id="topictable" align="center">
                   <c:if test="${empty news.commentsList}">

                    <td colspan="6"> 暂无评论！</td>
                    <tr>
                        <td colspan="6">
                            <hr/>
                        </td>
                    </tr>
                   </c:if>
                  <c:if test="${not empty news.commentsList}">
                      <c:forEach items="${news.commentsList}" var="comments">
                          <tr>
                              <td>留言人：</td>
                              <td>${comments.cauthor}</td>
                              <td>ip:</td>
                              <td>${comments.cip}</td>
                              <td>留言时间：</td>
                              <td colspan="6">
                                      ${comments.cdate}
                              </td>
                          </tr>
                          <tr>
                              <td colspan="6">
                                      ${comments.ccontent}
                              </td>
                          </tr>
                          <tr>
                              <td colspan="12">
                                  <hr/>
                              </td>
                          </tr>
                      </c:forEach>
                  </c:if>
                </table>
            </ul>
            <ul class="classlist">
                <form action="cts" method="post" onsubmit="return check()">
                    <input type="hidden" name="cnid" value="${news.nid}"/>
                    <input type="hidden" name="action" value="add"/>
                    <table width="80%" align="center">
                        <tr>
                            <td> 评 论</td>
                        </tr>
                        <tr>
                            <td> 用户名：</td>
                            <td><input id="cauthor" name="cauthor"
                                       value="<c:out value="${user}" default="这家伙很懒什么都没留下"/> "/>
                                IP：
                                <input name="cip" value="${pageContext.request.remoteAddr}"
                                       readonly="readonly"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                              <textarea name="ccontent" cols="70" rows="10"></textarea>
                            </td>
                        </tr>
<%--                        <td><input name="submit" value="发  表" type="submit"/>--%>
                        <td><input  value="发  表" type="button" id="ajaxbutton"/>
                        </td>
                    </table>
                </form>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
    <%--    <iframe src="index-elements/index_bottom.html" scrolling="no" frameborder="0" width="947px" height="190px"></iframe>--%>
    <%@include file="index-elements/index_bottom.html" %>
</div>
<script type="text/javascript">
$(function (){
    $("#ajaxbutton").click(function () {
        let array=$(".classlist form").serializeArray();
        // alert(array);
        let param=$.param(array);
        // alert(param);
        $.post("cts",param,function (data) {
           // alert(data.code);
            //通过dom操作把最新评论显示到评论的最上方
            if (data.code == "success"){
                    alert("评论发表成功！")
                let $newstopic=$("<tr><td>留言人：</td><td>cauthor</td><td>ip:</td><td> cip </td><td>留言时间：</td>\n"+
                    "<td> "+data.cdate+" </td></tr><tr><td colspan='6'> ccontent </td></tr>\n"+
                    "<tr><td colspan='12'><hr/></td></tr>");

                $(array).each(function () {
                $newstopic.find("td:contains('"+this.name+"')").text(this.value);
                })
                $("#topictable").prepend($newstopic)

            }else{
                alert("评论添加失败！")
            }
        },"json");
    })
})
</script>
</body>
</html>
