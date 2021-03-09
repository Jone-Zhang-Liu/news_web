package com.hisoft.news.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
//import java.util.logging.Logger;

@WebFilter(urlPatterns = "/*",initParams = @WebInitParam(name="charset",value="utf-8"))
public class CharacterEncodingFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(CharacterEncodingFilter.class);
    private String charset;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOGGER.debug("发送请求经过过滤器："+req.getServletContext().getContextPath());
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);
        chain.doFilter(req, resp); //放行
        LOGGER.debug("响应请求经过过滤器");
    }

    public void init(FilterConfig config) throws ServletException {
        charset=config.getInitParameter("charset");
    }
}

