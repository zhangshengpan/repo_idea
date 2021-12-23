package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){

        //调用service
        List<Course> courseList = courseService.findCourseByCondition(courseVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);

        return responseResult;

    }


    //课程图片上传
    @RequestMapping("/courseUpload")
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



    //新增课程信息及讲师信息
    //新增课程信息及修改课程信息要放在同一个方法里
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);

            ResponseResult responseResult = new ResponseResult(true, 200, "新增课程信息成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改课程信息成功", null);
            return responseResult;
        }



    }

    //回显课程信息    根据id查询课程信息及讲师信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVO);

        return responseResult;
    }


    //课程状态管理
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id,@RequestParam Integer status){

        //调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "状态变更成功", map);

        return responseResult;
    }



}
