package com.hisoft.news.web;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
//   @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//     req.setCharacterEncoding("utf-8");
//     resp.setCharacterEncoding("utf-8");
//     resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter out= resp.getWriter();
//
//        out.println("<html>");
//        out.println("  <head><title>Servlet</title></head>");
//        out.println("  <body>");
////        out.println("你好，欢迎来到Servlet世界");
//        out.println("<form action='welcome.jsp' method='post'>");
//        out.println("用户名： <input type='text' id='uname' name='user' >");
//        out.println("密码： <input type='password' name='pwd'>");
//        out.println("<input type='submit' value='登录'>");
//        out.println("</form>");
//        out.println("  </body>");
//        out.println("</html>");
////        String uname = req.getParameter("uname");
////        String pwd = req.getParameter("pwd");
////        if(uname == "admin"){
////            if(pwd==""){
////                out.println("请输入密码");
////            }else if(pwd=="admin"){
////                req.getRequestDispatcher("welcome.jsp").forward(req,resp);
////            }else {
////                out.println("密码错误");
////            }
////        }else{
////            out.println("账号密码错误");
////        }
//        out.close();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doPost(req, resp);
//        doGet(req, resp);
//    }

//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
////        System.out.println("接收请求:servlet----service()");
//        String hello = getInitParameter("hello");
//        String hello2 = this.getServletContext().getInitParameter("hello2");
//        System.out.println("Servlet初始化参数" + hello);
//        System.out.println("系统初始化参数" + hello2);
//
//        super.service(req, res);
//
//    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
        String uname = req.getParameter("uname");
        String city = req.getParameter("city");
        String[] languages = req.getParameterValues("language");
        req.setAttribute("uname",uname);
        req.setAttribute("city",city);
        req.setAttribute("languages",languages);
//        req.getRequestDispatcher("welcome.jsp").forward(req,resp);
        resp.sendRedirect("welcome.jsp");
//        super.service(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("servlet实例销毁,调用destroy方法");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("第一次请求,调用init初始化servlet");
    }
}
