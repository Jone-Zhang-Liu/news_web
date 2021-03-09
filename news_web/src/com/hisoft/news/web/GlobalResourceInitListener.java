package com.hisoft.news.web;

import com.hisoft.news.entity.Topic;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.service.impl.TopicServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.List;

@WebListener()
public class GlobalResourceInitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
private static final Logger LOGGER=Logger.getLogger(GlobalResourceInitListener.class);
    public GlobalResourceInitListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        TopicService topicService=new TopicServiceImpl();
        List<Topic> topicList = topicService.getAllTopics();
        sce.getServletContext().setAttribute("topicList",topicList);
        LOGGER.info("初始化加载"+topicList.size()+"条主题");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
