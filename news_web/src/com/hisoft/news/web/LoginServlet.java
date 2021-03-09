package com.hisoft.news.web;

import com.hisoft.news.dao.NewsUserDao;
import com.hisoft.news.dao.impl.NewsUserDaoImpl;
import com.hisoft.news.entity.NewsUser;
import com.hisoft.news.service.NewsUserService;
import com.hisoft.news.service.impl.NewsUserServiceImpl;
import com.hisoft.news.util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/lgs")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        if("login".equals(action)){
            String uname = request.getParameter("uname");
            String upwd = request.getParameter("upwd");
            if("".equals(uname)){
                request.setAttribute("error","用户名不能为空");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }else if("".equals(upwd)){
                request.setAttribute("error","密码不能为空");
                request.getRequestDispatcher("index.jsp").forward(request,response);
            }else{
                NewsUserService userService = new NewsUserServiceImpl();
                if(userService.login(uname,upwd)){
                    request.getSession().setAttribute("user",uname);
                    if(uname.equals("admin")){//管理员
//                        request.getRequestDispatcher("nls?action=list").forward(request,response);
                        response.sendRedirect("newspages/admin.jsp");
                    }else{
                        request.getRequestDispatcher("index.jsp").forward(request,response);
                    }
                }else{
                    request.setAttribute("error","登录失败，用户名或密码错误");
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                }
            }
        }else if("logout".equals(action)){
            //session失效
//            System.out.println("退出");
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        }else if("register".equals(action)){
            String uname = request.getParameter("uname");
            String upwd = request.getParameter("upwd");
            String reupwd = request.getParameter("reupwd");
            if("".equals(uname)){
                request.setAttribute("error","用户名不能为空");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }else if("".equals(upwd)){
                request.setAttribute("error","密码不能为空");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }else if(!upwd.equals(reupwd)){
                request.setAttribute("error","两次密码输入不一致");
                request.getRequestDispatcher("register.jsp").forward(request,response);
            }else{
                //使用jdbc把注册信息添加到数据库表中，根据返回结果，如果>0,注册成功，重定向到登录页，否则还回到register.jsp
                NewsUser user = new NewsUser(uname,upwd);
                NewsUserDao dao = new NewsUserDaoImpl(JdbcUtil.getConnection());
                int result = dao.insert(user);
                if(result > 0){
                    response.sendRedirect("index.jsp");
                }else{
                    request.setAttribute("error","注册失败，请联系管理员");
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                }
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
