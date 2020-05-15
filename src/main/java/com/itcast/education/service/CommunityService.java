package com.itcast.education.service;

import com.itcast.education.controller.dto.CommunityPageDto;

/**
 * 社区交流信息模块
 * @author zheng.zhang
 */
public interface CommunityService {
    /**
     * 页面加载数据
     * @param pageDataSize 帖子数量
     * @return DTO
     */
    CommunityPageDto getCommunityPageData(Integer pageDataSize);
}
