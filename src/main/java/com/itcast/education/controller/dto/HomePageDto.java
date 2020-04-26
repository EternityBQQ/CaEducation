package com.itcast.education.controller.dto;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.model.course.Course;
import com.itcast.education.model.course.Tag;
import com.itcast.education.model.media.MediaOutput;
import com.itcast.education.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageDto {
    /**
     * 轮播图
     */
    private List<Map<String, Object>> carouselList = new ArrayList<>();
    /**
     * 分类标签
     */
    private List<Map<String, Object>> categoryTag = new ArrayList<>();
    /**
     * 课程列表
     */
    private List<Map<String, Object>> courseList = new ArrayList<>();

    public List<Map<String, Object>> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<Map<String, Object>> carouselList) {
        this.carouselList = carouselList;
    }

    public List<Map<String, Object>> getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(List<Map<String, Object>> categoryTag) {
        this.categoryTag = categoryTag;
    }

    public List<Map<String, Object>> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Map<String, Object>> courseList) {
        this.courseList = courseList;
    }
}
