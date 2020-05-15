package com.itcast.education.model.community;

import com.itcast.education.model.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zheng.zhang
 * @description 评论实体
 * @date 2020/5/15 11:10
 */
@Data
@NoArgsConstructor
public class Comment extends BaseModel<Comment> {
    private Integer commentId;
    private String commentContent;
    private String commentUserId;
    private String replyUserIds;
    private String mediaIds;
    private String postId;
    private String likes;

    public Comment(String postId) {
        this.postId = postId;
    }
}
