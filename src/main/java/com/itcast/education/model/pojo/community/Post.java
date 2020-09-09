package com.itcast.education.model.pojo.community;

import com.itcast.education.model.pojo.base.BaseModel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Post extends BaseModel<Post> {
    private String postId;
    private String postTitle;
    private String postContent;
    private BigInteger postPageViews;
    private BigInteger postLikes;
    private String userId;
    private String mediaUrls;
    private String tags;

    private List<Comment> comments;
}
