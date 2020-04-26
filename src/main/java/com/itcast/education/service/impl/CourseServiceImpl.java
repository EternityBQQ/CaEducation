package com.itcast.education.service.impl;

import com.itcast.education.mapper.CourseMapper;
import com.itcast.education.model.course.Course;
import com.itcast.education.model.user.User;
import com.itcast.education.service.CourseService;
import com.itcast.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Course> queryCourses(Integer courseLimitNumber) {
        List<Course> courses = courseMapper.queryCourses(courseLimitNumber);
        return courses;
    }

    @Override
    public boolean saveCourse(Course course, String loginId) {
        // 封装创建人和创建时间
        User person = userService.find(new User(loginId));
        if (person == null && StringUtils.isEmpty(person.getUserRealName())) {
            return false;
        }
        course.setCreatePerson(person.getUserRealName());
        course.setCreateTime(new Date());
        boolean result = courseMapper.addCourse(course);
        return result;
    }
}
