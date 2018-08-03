package com.lanou.service.impl;

import com.lanou.dao.CommentMapper;
import com.lanou.model.Comment;
import com.lanou.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2018/8/3.
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> findComment(Integer productId){
        List<Comment> all =commentMapper.findComment(productId);
        return all;
    }

    public boolean addComment(Comment comment){
        boolean b = commentMapper.addComment(comment);
        return b;
    }

}
