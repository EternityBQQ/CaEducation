package com.itcast.education.model.course;

import com.itcast.education.model.base.BaseModel;

import java.util.List;

/**
 * 课程Model
 * @author zheng.zhang
 */
public class Course extends BaseModel<Course> {
    private String courseId;
    private String courseName;
    private String tags;
    private Double coursePrice;
    private Double discountPrice;
    private Integer saleCourse;
    private String courseUrl;


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getSaleCourse() {
        return saleCourse;
    }

    public void setSaleCourse(Integer saleCourse) {
        this.saleCourse = saleCourse;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }
}
