package com.hisoft.news.service.impl;

import com.hisoft.news.dao.impl.CommentsDaoImpl;
import com.hisoft.news.entity.Comments;
import com.hisoft.news.service.CommentsService;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-20 9:32:52
 **/
public class CommentsServiceImpl implements CommentsService {
    private Connection conn;
    @Override
    public int saveComments(Comments comments) {
        conn = JdbcUtil.getConnection();
        int result = 0;
        try {
            conn.setAutoCommit(false);
            CommentsDaoImpl commentsDao = new CommentsDaoImpl(conn);
            result = commentsDao.insert(comments);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return result;
    }
}
