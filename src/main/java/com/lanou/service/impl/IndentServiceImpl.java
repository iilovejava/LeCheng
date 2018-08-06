package com.lanou.service.impl;

import com.lanou.dao.IndentMapper;
import com.lanou.model.Indent;
import com.lanou.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanou on 2018/8/5.
 */
@Service("indentService")
public class IndentServiceImpl implements IndentService{

    @Autowired
    private IndentMapper indentMapper;

    public int addIndent(Indent indent){
        int i = indentMapper.addIndent(indent);
        return i;
    }
}
