package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);
        return new ResponseResult(true,200,"分页查询成功",pageInfo);
    }



    //课程图片上传
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目部署路径
        // F:\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55\webapps\ssm_web1
        String realPath = request.getServletContext().getRealPath("/");
        // F:\apache-tomcat-8.5.55-windows-x64\apache-tomcat-8.5.55\webapps
        String substring = realPath.substring(0, realPath.indexOf("ssm_web1"));

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        //生成的文件名就是时间戳+后缀
        String newFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        //需要重新拼接一个文件上传的路径
        String uploadPath = substring+"upload\\";
        File filePath= new File(uploadPath, newFileName);

        //进行判断，如果目录不存在就进行创建
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录成功"+filePath);
        }

        //图片在这里进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        //filePath:"http://loaclhost:8080/upload/3146893213212.JPG"
        map.put("filePath","http://loaclhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }


    //广告动态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionStatus(Integer id,Integer status){

        promotionAdService.updatePromotionAdStatus(id,status);
        return new ResponseResult(true,200,"修改广告动态上下线成功",null);
    }

}
