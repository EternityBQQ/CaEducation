package com.itcast.education.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zheng.zhang
 * @description 发帖DTO
 * @date 2020/5/15 16:19
 */
@Data
@ApiModel(value = "帖子DTO信息")
public class PostDto {
    @ApiModelProperty(value = "帖子标题")
    private String postTitle;
    @ApiModelProperty(value = "帖子内容")
    private String postContent;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "媒体数据URL")
    private String mediaUrls;
    @ApiModelProperty(value = "标签ID")
    private String tags;
}
