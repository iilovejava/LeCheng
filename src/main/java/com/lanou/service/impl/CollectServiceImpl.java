package com.lanou.service.impl;

import com.lanou.dao.CollectMapper;
import com.lanou.model.Collect;
import com.lanou.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2018/8/6.
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService{
    @Autowired
    private CollectMapper collectMapper;

    public List<Collect> findCollect(Integer userId){
        List<Collect> collects = collectMapper.findCollect(userId);
        return collects;
    }

    public boolean addCollect(Collect collect){
        boolean b = collectMapper.addComment(collect);
        return b;
    }

    public boolean deleteCollect(Collect collect){
        boolean b = collectMapper.deleteCollect(collect);
        return b;
    }
}
