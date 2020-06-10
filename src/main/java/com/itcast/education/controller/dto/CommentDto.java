package com.itcast.education.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author zheng.zhang
 * @description 评论DTO
 * @date 2020/6/10 14:46
 */
@ApiModel(value = "评论信息DTO")
public class CommentDto {
    @ApiModelProperty(value = "评论内容")
    private String commentContent;
    @ApiModelProperty(value = "回复人ID")
    private String replyUserIds;
    @ApiModelProperty(value = "评论图片URL集合")
    private List<String> imageUrls;
    @ApiModelProperty(value = "帖子ID")
    private String postId;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getReplyUserIds() {
        return replyUserIds;
    }

    public void setReplyUserIds(String replyUserIds) {
        this.replyUserIds = replyUserIds;
    }

    public String getImageUrls() {
        return imageUrls.toString();
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
