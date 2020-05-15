package com.itcast.education.model.community;

import com.itcast.education.model.base.BaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zheng.zhang
 * @description 帖子实体
 * @date 2020/5/15 12:58
 */
@Data
@Builder
@NoArgsConstructor
public class Post extends BaseModel<Post> {
    private String postId;
    private String postTitle;
    private String postContent;
    private BigInteger postPageViews;
    private BigInteger postLikes;
    private String userId;
    private String tags;

    private List<Comment> comments;
}
