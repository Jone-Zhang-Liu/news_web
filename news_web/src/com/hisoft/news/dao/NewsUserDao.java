package com.hisoft.news.dao;

import com.hisoft.news.entity.NewsUser;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-02 16:34:07
 **/
public interface NewsUserDao {

    //添加用户
     int insert(NewsUser user);

    //根据用户名和密码查询用户
    NewsUser findUser(String uname, String upwd);
}
