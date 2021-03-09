package com.hisoft.news.service.impl;

import com.hisoft.news.dao.NewsDao;
import com.hisoft.news.dao.TopicDao;
import com.hisoft.news.dao.impl.NewsDaoImpl;
import com.hisoft.news.dao.impl.TopicDaoImpl;
import com.hisoft.news.entity.Topic;
import com.hisoft.news.service.TopicService;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-19 16:29:02
 **/
public class TopicServiceImpl implements TopicService {
    private Connection conn;

    @Override
    public int deleleTopicByTid(int tid) {
        int result = 0;
        conn = JdbcUtil.getConnection();
        try {
            conn.setAutoCommit(false);
            NewsDao newsDao = new NewsDaoImpl(conn);
            if (newsDao.getNewsByTid(tid).isEmpty()) {
                //可以删除
                TopicDao topicDao = new TopicDaoImpl(conn);
                result = topicDao.delete(tid);
            } else {
                //有新闻，不能删除
                result = -1;
            }
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
            JdbcUtil.closeAll(conn, null, null);//归还连接
        }
        return result;
    }

    @Override
    public List<Topic> getAllTopics() {
        conn = JdbcUtil.getConnection();
        List<Topic> topicList;
        try {
            TopicDao topicDao = new TopicDaoImpl(conn);
            topicList = topicDao.getAllTopics();
        } finally {
            JdbcUtil.closeAll(conn,null,null);
        }

        return topicList;
    }

    @Override
    public int saveTopic(String tname) {
        conn = JdbcUtil.getConnection();
        int result = 0;
        try {
            conn.setAutoCommit(false);
            TopicDao topicDao = new TopicDaoImpl(conn);
            if(topicDao.exists(tname)){
                result = -1;
            }else{
                result = topicDao.insert(tname);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            result = 0;
        } finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return result;
    }

    @Override
    public int updateTopic(Topic topic) {
        conn = JdbcUtil.getConnection();
        int result = 0;
        try {
            conn.setAutoCommit(false);
            TopicDao topicDao = new TopicDaoImpl(conn);
            if(topicDao.exists(topic.getTname())){
                result = -1;
            }else{
                result = topicDao.update(topic);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            result = 0;
        } finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return result;
    }
}
