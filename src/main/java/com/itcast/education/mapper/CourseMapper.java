package com.itcast.education.mapper;

import com.itcast.education.model.course.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    /**
     * 获取课程列表
     * @param courseLimitNumber
     * @return
     */
    List<Course> queryCourses(Integer courseLimitNumber);

    /**
     * 新增课程信息
     * @param course
     * @return
     */
    boolean addCourse(Course course);
}
