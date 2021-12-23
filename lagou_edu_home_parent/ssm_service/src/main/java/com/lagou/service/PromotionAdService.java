package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {

    //分页查询广告信息
    PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    //广告动态上下线
    void updatePromotionAdStatus(Integer id,Integer status);
}
