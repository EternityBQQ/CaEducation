package com.itcast.education.service.impl;

import com.itcast.education.mapper.CourseMapper;
import com.itcast.education.model.course.Course;
import com.itcast.education.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> queryCourses(Integer courseLimitNumber) {
        List<Course> courses = courseMapper.queryCourses(courseLimitNumber);
        return courses;
    }

    @Override
    public boolean saveCourse(Course course) {
        return courseMapper.addCourse(course);
    }
}
