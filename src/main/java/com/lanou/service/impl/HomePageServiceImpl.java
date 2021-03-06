package com.lanou.service.impl;

import com.lanou.dao.HomePageMapper;
import com.lanou.model.*;
import com.lanou.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("homePageService")
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    private HomePageMapper homePageMapper;

    // 无限级分类 调用自身
    public List<HomePage> findByParentId(Integer parentId) {
        List<HomePage> list = (List<HomePage>) homePageMapper.findByParentId(parentId);

        for (int i = 0; i<list.size(); i++) {
            HomePage index = list.get(i);
            List<HomePage> thirdList = findByParentId(index.getId());
            index.setChildrenHomePage(thirdList);
        }
        return list;
    }
}
