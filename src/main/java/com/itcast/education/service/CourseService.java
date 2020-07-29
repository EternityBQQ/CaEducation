package com.itcast.education.service;

import com.itcast.education.model.pojo.course.Course;

import java.util.List;

public interface CourseService {
    /**
     * 获取课程列表
     * @param courseLimitNumber
     * @return
     */
    List<Course> queryCourses(Integer courseLimitNumber);

    /**
     * 保存课程信息
     * @param course
     * @param token
     * @return
     */
    boolean saveCourse(Course course, String token);
}
