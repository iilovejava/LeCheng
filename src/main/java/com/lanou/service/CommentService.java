package com.lanou.service;

import com.lanou.model.Comment;

import java.util.List;

/**
 * Created by lanou on 2018/8/3.
 */
public interface CommentService {

    List<Comment> findComment(Integer productId);

    boolean addComment(Comment comment);
}
