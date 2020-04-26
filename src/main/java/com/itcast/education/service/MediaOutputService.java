package com.itcast.education.service;

import com.itcast.education.controller.dto.HomePageDto;

public interface MediaOutputService {
    /**
     * 根据类型获取媒体数据类型
     * @param carouseSize
     * @param categorySize
     * @return
     */
    HomePageDto queryHomePageData(Integer carouseSize, Integer categorySize);
}
