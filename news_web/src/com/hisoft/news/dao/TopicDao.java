package com.hisoft.news.dao;

import com.hisoft.news.entity.Topic;

import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-03 17:09:02
 **/
public interface TopicDao {

    //查询所有主题
    List<Topic> getAllTopics();

    //新增主题
    int insert(String tname);

    //根据主题名称查询主题是否存在
    boolean exists(String tname);

    //更新主题
    int update(Topic topic);

    //删除主题
    int delete(int tid);
}


