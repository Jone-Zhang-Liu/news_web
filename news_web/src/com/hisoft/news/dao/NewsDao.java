package com.hisoft.news.dao;

import com.hisoft.news.entity.News;

import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-03 14:20:38
 **/
public interface NewsDao {

    //查询所有新闻
    List<News> getAllNews();

    //查询某主题下的新闻
    List<News> getNewsByTid(int tid);

    //根据主题名称查询新闻
    List<News> getNewsByTname(String tname);

    //查询某条新闻具体信息
    News findNewsById(int nid);

    //删除新闻
    int delete(int nid);

    //分页查询
    List<News> getPageNews(int tid,int pageNo,int pageSize);

    //计算新闻总条数
    int getNewsCount(int tid);

    /**
     * 添加新闻
     * @param news
     * @return
     */
    int add(News news);
}
