package com.itcast.education.model.community;

import com.itcast.education.model.base.BaseModel;
import lombok.Data;

import java.util.List;

/**
 * @author zheng.zhang
 * @description 帖子实体
 * @date 2020/5/15 12:58
 */
@Data
public class Post extends BaseModel<Post> {
    private String postId;
    private String post_title;
    private String post_content;
    private String post_pageviews;
    private String post_likes;
    private String user_id;
    private String tags;

    private List<Comment> comments;
}
