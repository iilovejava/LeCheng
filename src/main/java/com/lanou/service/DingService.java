package com.lanou.service;

import com.lanou.model.Ding;

import java.util.List;

/**
 * Created by lanou on 2018/8/8.
 */
public interface DingService {

    List<Ding> findByUserid(Integer userid);
}
