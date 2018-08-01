package com.lanou.service;

import com.lanou.dao.ValueMapper;
import com.lanou.model.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("valueService")
public class ValueServiceImpl implements ValueService {

    @Autowired
    private ValueMapper mapper;

    public List<Value> selectValuesByAttrId(Integer id) {

        List<Value> values = mapper.selectValuesByAttrId(id);
        return values;
    }

    public List<Value> selectValuesByProId(Integer id) {
        return mapper.selectValuesByProId(id);
    }
}
