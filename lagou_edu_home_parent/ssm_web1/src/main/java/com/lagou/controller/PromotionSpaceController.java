package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/PromotionSpace")
@RestController
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService spaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = spaceService.findAllPromotionSpace();

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有广告位成功", allPromotionSpace);

        return responseResult;
    }

    //新增广告位
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult savePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId() == null){
            spaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"添加广告位成功",null);
        }else {
            spaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"修改广告位成功",null);
        }
    }

    //根据id查询广告位信息
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id){

        PromotionSpace promotionSpace = spaceService.findPromotionSpaceById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询具体广告位成功", promotionSpace);
        return responseResult;
    }
}
