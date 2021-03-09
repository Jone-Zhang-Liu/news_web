package com.hisoft.news.service;

import com.hisoft.news.entity.News;
import com.hisoft.news.entity.Page;

import java.util.List;
/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-20 8:42:11
 **/
public interface NewsService {
    //查询所有新闻
    List<News> getAllNews();
    //根据主题名称查询新闻
    List<News> getNewsByTname(String tname);
    //查询某主题下的新闻
    List<News> getNewsByTid(int tid);
    //查询单条新闻详情，包括评论列表
    News queryNewsDetail(int nid);
    //删除新闻，评论也一起删除
    int deleteNews(int nid);
    //分页查询新闻
    Page queryPageNews(int currPageNo,int tid);
    //添加新闻
    int addNews(News news);


}
