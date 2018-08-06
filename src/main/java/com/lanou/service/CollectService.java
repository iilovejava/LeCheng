package com.lanou.service;

import com.lanou.model.Collect;

import java.util.List;

/**
 * Created by lanou on 2018/8/6.
 */
public interface CollectService {
    List<Collect> findCollect(Integer userId);

    boolean addCollect(Collect collect);

    boolean deleteCollect(Collect collect);
}
