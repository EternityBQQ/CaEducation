package com.itcast.education.service;

import com.itcast.education.controller.dto.HomePageDto;
import com.itcast.education.model.media.MediaOutput;

public interface MediaOutputService {
    /**
     * 根据类型获取媒体数据类型
     * @param carouseSize
     * @param categorySize
     * @return
     */
    HomePageDto queryHomePageData(Integer carouseSize, Integer categorySize);

    /**
     * 根据ID获取媒体数据
     * @param iconMediaId
     * @return
     */
    MediaOutput findMediaById(Integer iconMediaId);
}
