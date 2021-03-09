package com.hisoft.news.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/newspages/*")
public class LoginControllFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LoginControllFilter.class);
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
      HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp=(HttpServletResponse) response;
        LOGGER.info("请求经过登录控制过滤器");
        HttpSession session = req.getSession(false);

        if(session != null && session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        }else{
         resp.sendRedirect("../index.jsp");
            System.out.println("转发到主页");
        }
        LOGGER.info("响应经过登录控制过滤器");
    }
}
