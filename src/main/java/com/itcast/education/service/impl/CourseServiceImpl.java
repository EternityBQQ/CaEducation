package com.itcast.education.service.impl;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.mapper.CourseMapper;
import com.itcast.education.model.course.Course;
import com.itcast.education.service.CourseService;
import com.itcast.education.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> queryCourses(Integer courseLimitNumber) {
        List<Course> courses = courseMapper.queryCourses(courseLimitNumber, null);
        return courses;
    }

    @Override
    public boolean saveCourse(Course course, String token) {
        boolean result = false;
        // 根据课程名校验去重，重复就更新
        String courseName = course.getCourseName();
        Course isExistCourse = validateIsExist(courseName);

        // 根据Token获取登录用户=>从Redis中获取
        String userRealName = CommonUtil.getLoginUsernameByToken(token);
        Integer rows = 0;
        if (isExistCourse == null) {
            // 封装ID
            course.setCourseId(UUID.randomUUID().toString().replace("-", ""));
            // 封装创建人和创建时间
            course.setCreatePerson(userRealName);
            course.setCreateTime(new Date());
            // 添加课程信息
            rows = courseMapper.addCourse(course);
        } else {
            course.setCourseId(isExistCourse.getCourseId());
            // 封装操作人和操作时间
            course.setUpdatePerson(userRealName);
            course.setUpdateTime(new Date());
            // 更新课程信息
            rows = courseMapper.updateCourse(course);
        }
        if (rows > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 校验该课程是否已经上传
     * @param courseName
     * @return
     */
    private Course validateIsExist(String courseName) {
        Course course = Course.builder().build();
        course.setCourseName(courseName);
        List<Course> courses = courseMapper.queryCourses(GeneralConstant.COURSE_LIMIT_NUMBER, course);
        if (courses != null && !courses.isEmpty()) {
            return courses.get(0);
        }
        return null;
    }
}
