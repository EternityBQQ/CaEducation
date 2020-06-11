package com.itcast.education.controller.dto;

import com.itcast.education.utils.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zheng.zhang
 * @description 发帖DTO
 * @date 2020/5/15 16:19
 */
@ApiModel(value = "帖子DTO信息")
public class PostDto {
    @ApiModelProperty(value = "帖子标题")
    private String postTitle;
    @ApiModelProperty(value = "帖子内容")
    private String postContent;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "媒体数据URL")
    private List<String> mediaUrls;
    @ApiModelProperty(value = "标签ID")
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

    public String getMediaUrls() {
        return JsonUtil.objectToJson(this.mediaUrls);
    }

    public void setMediaUrls(List<String> mediaUrls) {
        this.mediaUrls = mediaUrls;
    }

    public String getTags() {
        return tags.toString();
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}
