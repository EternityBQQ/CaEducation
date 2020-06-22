package com.itcast.education.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zheng.zhang
 * @description 评论DTO
 * @date 2020/6/10 14:46
 */
@Data
@ApiModel(value = "评论信息DTO")
public class CommentDto implements Serializable {
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    @ApiModelProperty(value = "回复人ID")
    private Integer replyCommentId;
    @ApiModelProperty(value = "评论图片URL")
    private String imageUrl;
    @ApiModelProperty(value = "帖子ID")
    private String postId;
}
