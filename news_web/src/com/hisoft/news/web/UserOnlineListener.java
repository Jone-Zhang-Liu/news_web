package com.hisoft.news.web;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener()
public class UserOnlineListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
private static final Logger LOGGER=Logger.getLogger(UserOnlineListener.class);
    public UserOnlineListener() {
    }



    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
      if(sbe.getName().equals("user")){
          LOGGER.info("用户"+sbe.getName()+"上线了");
      }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        if(sbe.getName().equals("user")){
            LOGGER.info("用户"+sbe.getName()+"下线了");
        }
    }
    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        if(sbe.getName().equals("user")){
            LOGGER.info("用户"+sbe.getName()+"重复登录");
        }
    }
}
