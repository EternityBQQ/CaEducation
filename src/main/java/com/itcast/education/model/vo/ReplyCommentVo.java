package com.itcast.education.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 回复评论Vo
 * @author zheng.zhang
 * 2020/06/12 09:33
 */
@Data
@AllArgsConstructor
public class ReplyCommentVo {
    private String replyUsername;
    private String replyContent;
}
