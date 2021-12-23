package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper contentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {

        return contentMapper.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {

        Course course = contentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection section) {

        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        //调用方法
        contentMapper.saveSection(section);
    }

    @Override
    public void updateSection(CourseSection courseSection) {

        //补全信息
        courseSection.setUpdateTime(new Date());
        contentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setUpdateTime(new Date());
        courseSection.setStatus(status);
        courseSection.setId(id);

        //调用mapper
        contentMapper.updateSectionStatus(courseSection);

    }


}
