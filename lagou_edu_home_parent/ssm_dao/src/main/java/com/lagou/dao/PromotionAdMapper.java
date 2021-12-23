package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //分页查询广告信息
    List<PromotionAd> findAllPromotionAdByPage();

    //广告动态上下线
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
