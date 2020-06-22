package com.itcast.education.service;

import com.itcast.education.dto.HomePageDto;
import com.itcast.education.model.media.MediaOutput;

import java.util.List;

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

    /**
     * 根据ID集合查找媒体数据
     * @param mediaIds
     * @return
     */
    List<String> findByIds(String mediaIds);

    /**
     * 根据URL查找媒体数据对象
     * @param url
     * @return
     */
    MediaOutput findByUrl(String url);
}
