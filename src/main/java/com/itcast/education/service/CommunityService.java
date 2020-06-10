package com.itcast.education.service;

import com.itcast.education.controller.dto.CommentDto;
import com.itcast.education.controller.dto.CommunityPageDto;
import com.itcast.education.controller.dto.PostDto;

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

    /**
     * 发帖
     * @return 是否成功
     * @param postDto 帖子DTO
     */
    boolean sendOrUpdateArticle(PostDto postDto, String token);

    /**
     * 发表评论
     * @param commentDto 评论DTO
     * @param userToken 用户标识
     * @return 是否成功
     */
    boolean sendComment(CommentDto commentDto, String userToken);
}
