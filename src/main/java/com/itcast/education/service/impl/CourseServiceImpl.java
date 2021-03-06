package com.itcast.education.service.impl;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.mapper.CourseMapper;
import com.itcast.education.model.pojo.course.Course;
import com.itcast.education.model.pojo.user.User;
import com.itcast.education.service.CourseService;
import com.itcast.education.utils.CommonUtil;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRES_NEW, rollbackFor = {RuntimeException.class, Error.class})
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public List<Course> queryCourses(Integer courseLimitNumber) {
        return courseMapper.queryCourses(courseLimitNumber, new Course());
    }

    @Override
    public boolean saveCourse(Course course, String token) {
        boolean result = false;
        // 根据课程名校验去重，重复就更新
        String courseName = course.getCourseName();
        Course isExistCourse = validateIsExist(courseName);

        // 根据Token获取登录用户=>从Redis中获取
        User user = commonUtil.getLoginUsernameByToken(token);
        String userRealName = GeneralConstant.COMMON_PERSON;
        if (user != null && StringUtils.isEmpty(user.getUserRealName())) {
            userRealName = user.getUserRealName();
        }
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
     * @param courseName 课程名
     * @return 课程信息
     */
    private Course validateIsExist(String courseName) {
        Course course = Course.builder().build();
        course.setCourseName(courseName);
        List<Course> courses = courseMapper.queryCourses(GeneralConstant.COURSE_LIMIT_NUMBER, course);
        if (!ValidateUtil.listIsEmpty(courses)) {
            return courses.get(0);
        }
        return null;
    }
}
