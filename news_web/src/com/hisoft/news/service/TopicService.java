package com.hisoft.news.service;

import com.hisoft.news.entity.Topic;

import java.util.List;


public interface TopicService {

    //删除主题
    int deleleTopicByTid(int tid);

    //查询所有主题
    List<Topic> getAllTopics();

    //添加主题
    int saveTopic(String tname);

    //更新主题
    int updateTopic(Topic topic);
}
