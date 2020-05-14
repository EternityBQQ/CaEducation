package com.itcast.education.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class HomePageDto implements Serializable {
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
}
