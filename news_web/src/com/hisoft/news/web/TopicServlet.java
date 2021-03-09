package com.hisoft.news.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hisoft.news.entity.Topic;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.service.impl.TopicServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        TopicService topicService = new TopicServiceImpl();
        if ("list".equals(action)) {
            //查所有主题，存入作用域，跳转到topic_list.jsp
            List<Topic> topicList = topicService.getAllTopics();
//            request.setAttribute("topicList", topicList);
//            request.getRequestDispatcher("newspages/topic_list.jsp").forward(request, response);
           /* StringBuffer sb = new StringBuffer("[");
            for (int i = 0; i < topicList.size(); i++) {
                Topic topic = topicList.get(i);
                sb.append("{\"tid\":\""+topic.getTid()+"\",\"tname\":\""+topic.getTname()+"\"},");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
            out.print(sb.toString());*/
            out.print(JSON.toJSONString(topicList));
        } else if ("add".equals(action)) {
            String tname = request.getParameter("tname");
            int result = topicService.saveTopic(tname);
            if (result == -1) {
//                out.println("<script>alert(\"该主题已经存在，请换个名字吧\");location.href = \"newspages/topic_add.jsp\";</script>");
                out.print("{\"code\":\"exists\"}");//{"code":"exists"}
            } else if (result > 0) {
                //更新初始化的主题列表
//                List<Topic> topicList = topicService.getAllTopics();
//                request.getServletContext().setAttribute("topicList",topicList);
                out.println("<script>alert(\"添加成功！点击确定回到主题列表\");location.href = \"newspages/topic_list.jsp\";</script>");
//                out.print("{\"code\":\"success\"}");
            } else {
                out.println("<script>alert(\"添加失败！点击确定重新添加主题\");location.href = \"newspages/topic_add.jsp\";</script>");
//                out.print("{\"code\":\"error\"}");
            }
        } else if ("update".equals(action)) {
            int tid = Integer.parseInt(request.getParameter("tid"));
            String tname = request.getParameter("tname");
            //在修改主题之前，要先判断该主题是否已经存在
            int result = topicService.updateTopic(new Topic(tid, tname));
            if (result == -1) {
                out.println("<script>alert(\"该主题已经存在，请换个名字吧\");location.href = \"newspages/topic_modify.jsp?tname="+tname+"&tid="+tid+"\";</script >");
//                out.print("{\"code\":\"exists\"}");
            } else if (result > 0) {
                //更新初始化的主题列表
//                List<Topic> topicList = topicService.getAllTopics();
//                request.getServletContext().setAttribute("topicList",topicList);
                out.println("<script>alert(\"修改成功！点击确定回到主题列表\");location.href = \"newspages/topic_list.jsp\";</script >");
//                out.print("{\"code\":\"success\"}");
            } else {//修改失败
                out.println("<script>alert(\"修改失败！点击确定重新修改主题\");location.href = \"newspages/topic_modify.jsp?tname="+tname+"&tid="+tid+"\";</script >");
//                out.print("{\"code\":\"error\"}");
            }
        } else if ("del".equals(action)) {//删除主题
            int tid = Integer.parseInt(request.getParameter("tid"));
            //调用业务层实现删除逻辑
            int result = topicService.deleleTopicByTid(tid);
            if (result > 0) {//没有新闻
                //更新初始化的主题列表
//                List<Topic> topicList = topicService.getAllTopics();
//                request.getServletContext().setAttribute("topicList",topicList);
                out.println("<script>alert(\"删除成功！点击确定回到主题列表\");location.href = \"newspages/topic_list.jsp\";</script>");
//                out.print("{code:success}");
            } else if (result == 0) {
                out.println("<script>alert(\"删除失败！点击确定回到主题列表\");location.href = \"topic?action=list\";</script>");
//                out.print("{\"code\":\"error\"}");
            } else {//有新闻
                out.println("<script>alert(\"删除失败！该主题下有新闻，点击确定回到主题列表\");location.href = \"topic?action=list\";</script>");
//                out.print("{\"code\":\"cannot\"}");
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
