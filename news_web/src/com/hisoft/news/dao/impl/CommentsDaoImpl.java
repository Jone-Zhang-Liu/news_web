package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.BaseDao;
import com.hisoft.news.dao.CommentsDao;
import com.hisoft.news.entity.Comments;
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
 * @create: 2021-02-18 15:16:10
 **/
public class CommentsDaoImpl extends BaseDao implements CommentsDao {
    public CommentsDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public List<Comments> getAllCommentsByNid(int nid) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Comments> commentsList = new ArrayList<>();
        Comments comments = null;
        try {
            pstmt = conn.prepareStatement("select * from comments where cnid = ? order by cdate desc");
            pstmt.setInt(1, nid);
            rs = pstmt.executeQuery();
            //处理结果集
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String ccontent = rs.getString("ccontent");
                String cip = rs.getString("cip");
                String cauthor = rs.getString("cauthor");
                Date cdate = rs.getTimestamp("cdate");
                comments = new Comments(cid, nid, ccontent, cdate, cip, cauthor);
                commentsList.add(comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(null, pstmt, rs);
        }
        return commentsList;
    }

    @Override
    public int insert(Comments comments) {
        return update("insert into comments(cnid,ccontent,cdate,cip,cauthor) values(?,?,?,?,?)",
                comments.getCnid(),
                comments.getCcontent(),
                comments.getCdate(),
                comments.getCip(),
                comments.getCauthor());
    }

    @Override
    public int deleteByNid(int nid) {
        return update("delete from comments where cnid = ?",nid);
    }
}
