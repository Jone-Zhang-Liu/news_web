package com.hisoft.news.service.impl;

import com.hisoft.news.dao.NewsUserDao;
import com.hisoft.news.dao.impl.NewsUserDaoImpl;
import com.hisoft.news.service.NewsUserService;
import com.hisoft.news.util.JdbcUtil;

import java.sql.Connection;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-20 8:36:53
 **/
public class NewsUserServiceImpl implements NewsUserService {
    private Connection conn;
    @Override
    public boolean login(String uname, String upwd) {
        conn = JdbcUtil.getConnection();
        boolean flag = false;
        try {
            NewsUserDao userDao = new NewsUserDaoImpl(conn);
            if (userDao.findUser(uname,upwd)!=null){
                flag = true;
            }
        } finally {
            JdbcUtil.closeAll(conn,null,null);
        }
        return flag;
    }
}
