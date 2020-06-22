package com.itcast.education.model.community;

import com.itcast.education.vo.ReplyCommentVo;
import com.itcast.education.model.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zheng.zhang
 * description 评论实体
 * date 2020/5/15 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Comment extends BaseModel<Comment> {
    private Integer commentId;
    private String commentContent;
    private String commentUserId;
    private Integer replyCommentId;
    private String imageUrl;
    private String postId;
    private Integer likes;

    private List<ReplyCommentVo> replyComments = new ArrayList<>();

    public Comment(String postId) {
        this.postId = postId;
    }

    public Comment(Integer commentId) {
        this.commentId = commentId;
    }
}
