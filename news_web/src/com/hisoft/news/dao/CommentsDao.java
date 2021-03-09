package com.hisoft.news.dao;

import com.hisoft.news.entity.Comments;

import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-18 15:15:01
 **/
public interface CommentsDao {

    //查找某条新闻的所有评论
    List<Comments> getAllCommentsByNid(int nid);

    //添加评论
    int insert(Comments comments);

    //删除某条新闻下的所有评论
    int deleteByNid(int nid);
}
