package com.lanou.service.impl;

import com.lanou.dao.CommentMapper;
import com.lanou.dao.DingMapper;
import com.lanou.model.Ding;
import com.lanou.service.DingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2018/8/8.
 */
@Service("dingService")
public class DingServiceImpl implements DingService{

    @Autowired
    private DingMapper dingMapper;

    public List<Ding> findByUserid(Integer userid){
        List<Ding> dings = dingMapper.findByUserid(userid);
        return dings;
    }
}
