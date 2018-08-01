package com.lanou.service;

import com.lanou.dao.AttrMapper;
import com.lanou.model.Attr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("attrService")
public class AttrServiceImpl implements AttrService {
    @Autowired
    private AttrMapper attrMapper;

    public List<Attr> selectAllAttr() {
        List<Attr> attrs = attrMapper.selectAllAttr();
        return attrs;
    }

    public Attr selectByPrimaryKey(Integer id) {
        Attr attr = attrMapper.selectByPrimaryKey(id);
        return attr;
    }

    public List<Attr> selectByCateId(Integer id) {
        List<Attr> attrs = attrMapper.selectByCateId(id);
        return attrs;
    }
}
