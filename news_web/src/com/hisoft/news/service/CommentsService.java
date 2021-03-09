package com.hisoft.news.service;

import com.hisoft.news.entity.Comments;
/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-20 9:32:45
 **/
public interface CommentsService {
    //添加评论
    int saveComments(Comments comments);
}
