package com.lagou.dao;

import com.lagou.domain.Test;

import java.util.List;

public interface TestDao {

    //查询所有
    public List<Test> findAll();
}
