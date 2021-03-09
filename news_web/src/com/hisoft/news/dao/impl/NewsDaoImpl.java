package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.BaseDao;
import com.hisoft.news.dao.NewsDao;
import com.hisoft.news.entity.News;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-03 14:21:35
 **/
public class NewsDaoImpl extends BaseDao implements NewsDao {
    public NewsDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<News> getAllNews() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<News> newsList = new ArrayList<>();
        News news = null;

        try {
            pstmt = conn.prepareStatement("select nid,ntitle,nauthor,ncreateDate from news order by ncreateDate desc");
            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                Date ncreateDate = rs.getTimestamp("ncreateDate");
                news = new News(nid, ntitle, nauthor, ncreateDate);
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return newsList;
    }

    @Override
    public List<News> getNewsByTid(int tid) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<News> newsList = new ArrayList<>();
        News news = null;
        try {
            pstmt = conn.prepareStatement("select nid,ntitle,nauthor,ncreateDate from news where ntid = ?");
            pstmt.setInt(1, tid);
            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                Date ncreateDate = rs.getTimestamp("ncreateDate");
                news = new News(nid, ntitle, nauthor, ncreateDate);
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return newsList;
    }

    @Override
    public List<News> getNewsByTname(String tname) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<News> newsList = new ArrayList<>();
        News news = null;
        try {
            pstmt = conn.prepareStatement("select nid,ntitle,nauthor from news,topic where news.ntid = topic.tid and topic.tname = ? order by news.ncreateDate desc limit 5");
            pstmt.setString(1, tname);
            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                news = new News(nid, ntitle, nauthor);
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return newsList;
    }

    @Override
    public News findNewsById(int nid) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        News news = null;
        try {
            pstmt = conn.prepareStatement("select ntitle,nauthor,ncreateDate,ncontent from news where nid = ?");
            pstmt.setInt(1, nid);
            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                Date ncreateDate = rs.getTimestamp("ncreateDate");
                String ncontent = rs.getString("ncontent");
                news = new News(nid, ntitle, nauthor, ncreateDate, ncontent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return news;
    }

    @Override
    public int delete(int nid) {
        return update("delete from news where nid = ?", nid);
    }

    @Override
    public List<News> getPageNews(int tid, int pageNo, int pageSize) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<News> newsList = new ArrayList<>();
        News news = null;
        try {
            if (tid == 0) {//没有根据tid查询
                pstmt = conn.prepareStatement("select nid,ntitle,nauthor,ncreateDate from news order by ncreateDate desc limit ?,?");
                pstmt.setInt(1, (pageNo - 1) * pageSize);
                pstmt.setInt(2, pageSize);
            } else {
                pstmt = conn.prepareStatement("select nid,ntitle,nauthor,ncreateDate from news where ntid = ? order by ncreateDate desc limit ?,?");
                pstmt.setInt(1, tid);
                pstmt.setInt(2, (pageNo - 1) * pageSize);
                pstmt.setInt(3, pageSize);
            }

            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                int nid = rs.getInt("nid");
                String ntitle = rs.getString("ntitle");
                String nauthor = rs.getString("nauthor");
                Date ncreateDate = rs.getTimestamp("ncreateDate");
                news = new News(nid, ntitle, nauthor, ncreateDate);
                newsList.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return newsList;
    }

    @Override
    public int add(News news) {
        return update("insert into news(ntid,ntitle,nauthor,ncreateDate,npicPath,ncontent,nsummary) values(?,?,?,?,?,?,?)",
                news.getNtid(),
                news.getNtitle(),
                news.getNauthor(),
                news.getNcreateDate(),
                news.getNpicPath(),
                news.getNcontent(),
                news.getNsummary());
    }

    @Override
    public int getNewsCount(int tid) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            if (tid == 0) {
                pstmt = conn.prepareStatement("select count(1) from news");
            } else {
                pstmt = conn.prepareStatement("select count(1) from news where ntid = ?");
                pstmt.setInt(1, tid);
            }
            rs = pstmt.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return count;
    }

    public static void main(String[] args) {
//        List<News> newsList = new NewsDaoImpl(JdbcUtil.getConnection()).getPageNews(1, 5);
//        for (News news : newsList) {
//            System.out.println(news);
//        }

//        int newsCount = new NewsDaoImpl(JdbcUtil.getConnection()).getNewsCount();
//        System.out.println(newsCount);
    }
}
