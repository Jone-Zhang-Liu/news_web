package com.hisoft.news.web;

import com.hisoft.news.entity.Comments;
import com.hisoft.news.service.CommentsService;
import com.hisoft.news.service.impl.CommentsServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cts")
public class CommentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
//    CommentsDao dao = new CommentsDaoImpl();
        CommentsService commentsService = new CommentsServiceImpl();
        if ("add".equals(action)) {
            int cnid = Integer.parseInt(request.getParameter("cnid"));
            String cauthor = request.getParameter("cauthor");
            String cip = request.getParameter("cip");
            String ccontent = request.getParameter("ccontent");
            Date cdate = new Date();
            Comments comments = new Comments(cnid, ccontent, cdate, cip, cauthor);
            //调用业务层实现评论发布
            int result = commentsService.saveComments(comments);
            if (result > 0) {
                //格式化时间
                DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //{"code":"success","cdate":"格式化时间"}
                out.print("{\"code\":\"success\",\"cdate\":\""+format.format(cdate)+"\"}");

            } else {
             //{"code":"error"}
            out.print("{\"code\":\"error\"}");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
