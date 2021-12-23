package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    //获取所有广告位的方法
    List<PromotionSpace> findAllPromotionSpace();

    //添加广告位
    void savePromotionSpace(PromotionSpace promotionSpace);

    //根据id查询广告位的所有信息
    PromotionSpace findPromotionSpaceById(Integer id);

    //更新广告位信息
    void updatePromotionSpace(PromotionSpace promotionSpace);

}
