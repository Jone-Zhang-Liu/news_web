<%
    String uname = (String)session.getAttribute("user");
    if (uname == null) {
        response.sendRedirect("../index.jsp");
        return;
    }
%>