package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.BaseDao;
import com.hisoft.news.dao.TopicDao;
import com.hisoft.news.entity.Topic;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TopicDaoImpl extends BaseDao implements TopicDao {
    public TopicDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<Topic> getAllTopics() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Topic> topicList = new ArrayList<>();
        Topic topic = null;

        try {
            pstmt = conn.prepareStatement("select * from topic");
            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                topic = new Topic(tid,tname);
                topicList.add(topic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return topicList;
    }

    @Override
    public int insert(String tname) {
        return update("insert into topic(tname) values (?)",tname);
    }

    @Override
    public boolean exists(String tname) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            pstmt = conn.prepareStatement("select * from topic where tname = ?");
            pstmt.setString(1,tname);
            rs = pstmt.executeQuery();
            if(rs.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(null,pstmt,rs);
        }
        return flag;
    }

    @Override
    public int update(Topic topic) {
        return super.update("update topic set tname = ? where tid = ?",topic.getTname(),topic.getTid());
    }

    @Override
    public int delete(int tid) {
        return update("delete from topic where tid = ?",tid);
    }
}
