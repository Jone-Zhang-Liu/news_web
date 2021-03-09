package com.hisoft.news.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hisoft.news.entity.News;
import com.hisoft.news.entity.Page;
import com.hisoft.news.entity.Topic;
import com.hisoft.news.service.NewsService;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.service.impl.NewsServiceImpl;
import com.hisoft.news.service.impl.TopicServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sun.plugin.dom.core.Element;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/nls")
public class NewsListServlet extends HttpServlet {
//    private Object application;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        NewsService newsService = new NewsServiceImpl();
        TopicService topicService = new TopicServiceImpl();
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            //调用业务层查询所有新闻
            List<News> newsList = newsService.getAllNews();
//            request.setAttribute("newsList", newsList);
//            request.getRequestDispatcher("newspages/admin.jsp").forward(request, response);
            //返回json字符串--[{"nid":1,"ntitle":"jjjj","nauthor":"amdin"},{},{},{},]
//            StringBuffer sb = new StringBuffer("[");
//            for (int i = 0; i < newsList.size(); i++) {
//                News news = newsList.get(i);
//                sb.append("{\"nid\":\""+news.getNid()+"\",\"ntitle\":\""+news.getNtitle().replace("\"","&quot;")+"\",\"nauthor\":\""+news.getNauthor()+"\"},");
//            }
//            sb.deleteCharAt(sb.length()-1);
//            sb.append("]");
//            System.out.println(sb.toString());
//            out.print(sb.toString());

            String jsonString = JSON.toJSONString(newsList,
                    SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteDateUseDateFormat);//yyyy-MM-dd HH:mm:ss
            System.out.println(jsonString);
            out.print(jsonString);

            /*StringBuffer sb = new StringBuffer();
            for (int i = 0; i < newsList.size(); i++) {
                News news = newsList.get(i);
                sb.append("<li>"+news.getNtitle()+"<span> 作者："+news.getNauthor()+" &#160;&#160;&#160;&#160;" +
                        "                        <a href='news_modify.jsp'>修改</a>&#160;&#160;&#160;&#160;" +
                        "                        <a href='javascript:;' onclick=\"del("+news.getNid()+");\">删除</a></span></li>");
                if((i+1)%5==0){
                    sb.append("<li class='space'></li>");
                }
            }
            out.println(sb.toString());*/

        } else if ("index".equals(action)) {
            List<News> list1 = newsService.getNewsByTname("国内");
            List<News> list2 = newsService.getNewsByTname("国际");
            List<News> list3 = newsService.getNewsByTname("娱乐");
//            List<Topic> list4 = topicService.getAllTopics();
//        List<News> list5 = newsService.getAllNews();
//        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
            request.getSession().setAttribute("list1", list1);
            request.getSession().setAttribute("list2", list2);
            request.getSession().setAttribute("list3", list3);

//            String pageNoStr = request.getParameter("pageNo");
//            int pageNo = ((pageNoStr == null) || pageNoStr.equals("") || pageNoStr.equals("null")) ? 1 : Integer.parseInt(request.getParameter("pageNo"));
//            Page newsPage = newsService.queryPageNews(pageNo, 0);//0代表不根据主题查询（查询所有）
////            request.getSession().setAttribute("list4", list4);
//            request.getSession().setAttribute("newsPage", newsPage);
            response.sendRedirect(response.encodeURL("index.jsp"));
        } else if ("indexAjax".equals(action)) {
            String tidStr = request.getParameter("tid");
            int tid = ((tidStr == null || tidStr.equals("") || tidStr.equals("null")) ? 0 : Integer.parseInt(request.getParameter("tid")));
            String pageNoStr = request.getParameter("pageNo");
            int pageNo = ((pageNoStr == null) || pageNoStr.equals("") || pageNoStr.equals("null")) ? 1 : Integer.parseInt(request.getParameter("pageNo"));
            Page newsPage = newsService.queryPageNews(pageNo, tid);
            out.print("[{\"tid\":\"" + tid + "\"}," + JSON.toJSONString(newsPage, SerializerFeature.WriteDateUseDateFormat) + "]");
        } else if ("topic_news".equals(action)) {
//        int tid = Integer.parseInt(request.getParameter("tid"));
            String tidStr = request.getParameter("tid");
            int tid = ((tidStr == null || tidStr.equals("") || tidStr.equals("null")) ? 0 : Integer.parseInt(request.getParameter("tid")));

            String pageNoStr = request.getParameter("pageNo");
            int pageNo = ((pageNoStr == null) || pageNoStr.equals("") || pageNoStr.equals("null")) ? 1 : Integer.parseInt(request.getParameter("pageNo"));
            Page newsPage = newsService.queryPageNews(pageNo, tid);
            request.getSession().setAttribute("newsPage", newsPage);
            request.getSession().setAttribute("tid", tid);
//        List<News> list5 = newsService.getNewsByTid(tid);
//        session.setAttribute("list5", list5);
            response.sendRedirect("index.jsp");
        } else if ("view_news".equals(action)) {
            int nid = Integer.parseInt(request.getParameter("nid"));
            //调用业务层查询新闻详细信息，包括所有评论信息
            News news = newsService.queryNewsDetail(nid);
            news.setNcontent(news.getNcontent().replaceAll("\r\n", "<br/>"));//页面内容换行
            request.setAttribute("news", news);
            request.getRequestDispatcher("news_read.jsp").forward(request, response);
        } else if ("del".equals(action)) {
            int nid = Integer.parseInt(request.getParameter("nid"));
            //删除新闻，同时删除该新闻下所有评论
            int result = newsService.deleteNews(nid);
            if (result > 0) {
                out.println("<script>\n" +
                        "           alert(\"删除成功，点击确定回到新闻列表\");\n" +
                        "           location.href = \"nls?action=list\";\n" +
                        "</script>");

            } else {
                out.println("<script>\n" +
                        "          alert(\"删除失败，点击确定回到新闻列表\");\n" +
                        "          location.href = \"nls?action=list\";\n" +
                        "</script>");
            }
        } else if ("select_topic".equals(action)) {
            List<Topic> topicList = topicService.getAllTopics();
            request.getSession().setAttribute("topics", topicList);
            response.sendRedirect("newspages/news_add.jsp");
        } else if ("addnews".equals(action)) {
//添加新闻
            News news = new News();
            String fieldName = "";  //表单字段元素的name属性值
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            //上传文件的存储路径（服务器文件系统上的绝对文件路径）
            String uploadFilePath = request.getServletContext().getRealPath("/upload");
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(1024 * 1024 * 5);
                //解析form表单中所有文件
                try {
                    List<FileItem> items = upload.parseRequest(request);
                    for (FileItem item : items) {   //依次处理每个文件
                        if (item.isFormField()) {  //普通表单字段
                            fieldName = item.getFieldName();  //表单字段的name属性值
                            if (fieldName.equals("ntid")) {   //主题
                                news.setNtid(Integer.parseInt(item.getString()));
                            } else if (fieldName.equals("ntitle")) { //标题
                                news.setNtitle(item.getString("UTF-8"));
                            } else if (fieldName.equals("nauthor")) {//作者
                                news.setNauthor(item.getString("UTF-8"));
                            } else if (fieldName.equals("nsummary")) { //摘要
                                news.setNsummary(item.getString("UTF-8"));
                            } else if (fieldName.equals("ncontent")) { //内容
                                news.setNcontent(item.getString("UTF-8"));
                            }
                        } else {   //文件表单字段
                            String fileName = item.getName();
                            List<String> filType = Arrays.asList("gif", "jpg", "jpeg");
                            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                            if (fileName != null && !fileName.equals("") && !filType.contains(ext)) {  //判断文件类型是否在允许范围内
                                out.println("<script type=\"text/javascript\">\n" +
                                        "           alert(\"上传失败，文件类型只能是gif、jpg、jpeg\");\n" +
                                        "           location.href = \"newspages/news_add.jsp\";\n" +
                                        "</script>");
                            } else if (fileName != null && !fileName.equals("")) {
                                File fullFile = new File(item.getName());
                                File saveFile = new File(uploadFilePath, fullFile.getName());
                                item.write(saveFile);
                                news.setNpicPath(uploadFilePath + "\\" + fullFile.getName());
                            }
                        }
                    }
                } catch (FileUploadBase.SizeLimitExceededException ex) {
                    out.println("<script type=\"text/javascript\">\n" +
                            "            alert(\"上传失败，文件太大，单个文件的最大限制是：5MB\");\n" +
                            "            location.href = \"newspages/news_add.jsp\";\n" +
                            "</script>");
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (newsService.addNews(news) >= 1) {
                out.println("<script type=\"text/javascript\">\n" +
                        "         alert(\"上传成功！\");\n" +
                        "         location.href = \"nls?action=list\";\n" +
                        "</script>");
            } else {
                out.println(
                        "<script type=\"text/javascript\">\n" +
                                "       alert(\"上传失败，请联系管理员！\");\n" +
                                "       location.href = \"newspages/news_add.jsp\";\n" +
                                "</script>");

            }
        }
        }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
