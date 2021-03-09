package com.hisoft.news.service.impl;

import com.hisoft.news.dao.CommentsDao;
import com.hisoft.news.dao.NewsDao;
import com.hisoft.news.dao.impl.CommentsDaoImpl;
import com.hisoft.news.dao.impl.NewsDaoImpl;
import com.hisoft.news.entity.Comments;
import com.hisoft.news.entity.News;
import com.hisoft.news.entity.Page;
import com.hisoft.news.service.NewsService;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-20 8:42:29
 **/
public class NewsServiceImpl implements NewsService {
    private Connection conn;

    @Override
    public int addNews(News news) {
        conn = JdbcUtil.getConnection();
        int result = 0;
        try {
            conn.setAutoCommit(false);
            news.setNcreateDate(new Date());
            NewsDao newsDao = new NewsDaoImpl(conn);
            result = newsDao.add(news);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                result = 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(conn, null, null);
        }
        return result;
    }

    @Override
    public List<News> getAllNews() {
        conn = JdbcUtil.getConnection();
        List<News> newsList = new ArrayList<>();
        try {
            NewsDao newsDao = new NewsDaoImpl(conn);
            newsList = newsDao.getAllNews();
        } finally {
            JdbcUtil.closeAll(conn, null, null);
        }
        return newsList;
    }

    @Override
    public List<News> getNewsByTname(String tname) {
        conn = JdbcUtil.getConnection();
        List<News> newsList = new ArrayList<>();
        try {
            NewsDao newsDao = new NewsDaoImpl(conn);
            newsList = newsDao.getNewsByTname(tname);
        }  finally {
            JdbcUtil.closeAll(conn, null, null);
        }
        return newsList;
    }

    @Override
    public List<News> getNewsByTid(int tid) {
        conn = JdbcUtil.getConnection();
        List<News> newsList = new ArrayList<>();
        try {
            NewsDao newsDao = new NewsDaoImpl(conn);
            newsList = newsDao.getNewsByTid(tid);
        } finally {
            JdbcUtil.closeAll(conn, null, null);
        }
        return newsList;
    }

    @Override
    public News queryNewsDetail(int nid) {
        conn = JdbcUtil.getConnection();
        News news = null;
        try {
            conn.setAutoCommit(false);
            NewsDao newsDao = new NewsDaoImpl(conn);
            CommentsDao commentsDao = new CommentsDaoImpl(conn);
            news = newsDao.findNewsById(nid);
            List<Comments> commentsList = commentsDao.getAllCommentsByNid(nid);
            news.setCommentsList(commentsList);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, null, null);
        }
        return news;
    }

    @Override
    public int deleteNews(int nid) {
        conn = JdbcUtil.getConnection();
        int result = 0;
        try {
            conn.setAutoCommit(false);
            NewsDao newsDao = new NewsDaoImpl(conn);
            CommentsDao commentsDao = new CommentsDaoImpl(conn);
            commentsDao.deleteByNid(nid);//先删除评论-外键约束
            result = newsDao.delete(nid);//再删除新闻
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                result = 0;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(conn, null, null);
        }
        return result;
    }

    @Override
    public Page queryPageNews(int currPageNo,int tid) {
        conn = JdbcUtil.getConnection();
        Page page = new Page();
        try {
            NewsDao newsDao = new NewsDaoImpl(conn);
            int newsCount = newsDao.getNewsCount(tid);
            page.setTotalCount(newsCount);//设置总记录数和总页数
            page.setCurrPageNo(currPageNo);//更新当前页号
            List<News> pageNews = newsDao.getPageNews(tid,page.getCurrPageNo(), page.getPageSize());
            page.setNewsList(pageNews);
        }finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return page;
    }

    public static void main(String[] args) {
//        Page page = new NewsServiceImpl().queryPageNews(1);
//        System.out.println(page);
    }
}
