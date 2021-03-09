package com.hisoft.news.dao.impl;

import com.hisoft.news.dao.BaseDao;
import com.hisoft.news.dao.NewsUserDao;
import com.hisoft.news.entity.NewsUser;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-02 16:35:21
 **/
public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    public NewsUserDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int insert(NewsUser user) {
        return update("insert into news_users(uname,upwd) values (?,?)",user.getUname(),user.getUpwd());
    }

    @Override
    public NewsUser findUser(String uname, String upwd) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NewsUser user = null;
        try {
            pstmt = conn.prepareStatement("select uid from news_users where uname = ? and upwd = ?");
            pstmt.setString(1,uname);
            pstmt.setString(2,upwd);
            rs = pstmt.executeQuery();
            if(rs.next()){
                user = new NewsUser(rs.getInt("uid"),uname,upwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeAll(null,pstmt,rs);
        }
        return user;
    }
}
