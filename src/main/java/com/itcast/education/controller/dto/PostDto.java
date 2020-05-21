package com.itcast.education.controller.dto;

import java.util.List;

/**
 * @author zheng.zhang
 * @description 发帖DTO
 * @date 2020/5/15 16:19
 */
public class PostDto {
    private String postTitle;
    private String postContent;
    private String userId;
    private List<Integer> mediaIds;
    private List<Integer> tags;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMediaIds() {
        return mediaIds.toString();
    }

    public void setMediaIds(List<Integer> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public String getTags() {
        return tags.toString();
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}
