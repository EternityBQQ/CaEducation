package com.itcast.education.service.impl;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.controller.dto.CommunityPageDto;
import com.itcast.education.controller.dto.PostDto;
import com.itcast.education.mapper.CommentMapper;
import com.itcast.education.mapper.PostMapper;
import com.itcast.education.model.community.Comment;
import com.itcast.education.model.community.Post;
import com.itcast.education.service.CommunityService;
import com.itcast.education.utils.CommonUtil;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zheng.zhang
 * @description 社区交流信息模块服务层
 * @date 2020/5/15 11:06
 */
@Service
public class CommunityServiceImpl implements CommunityService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private PostMapper postMapper;

    @Autowired
    private CommonUtil commonUtil;

    @Cacheable(cacheNames = "communityPageData")
    @Override
    public CommunityPageDto getCommunityPageData(Integer pageDataSize) {
        CommunityPageDto pageData;
        // 帖子
        List<Post> posts = postMapper.find(new Post());
        if (ValidateUtil.listIsEmpty(posts)) {
            return null;
        }
        // 评论
        for (Post post: posts) {
            String postId = post.getPostId();
            // 根据帖子ID拿到它下面的所有评论
            List<Comment> comments = commentMapper.find(new Comment(postId));
            post.setComments(comments);
        }
        // 设置热帖=>标题贴暂时设置为最新数据
        Post hotPost = posts.get(posts.size());
        // 设置论坛贴=>暂时设置为除开最新帖子之外的帖子
        posts.remove(posts.size());
        pageData = new CommunityPageDto(hotPost, posts);
        return pageData;
    }

    @Override
    public boolean sendOrUpdateArticle(PostDto postDto, String token) {
        boolean result;

        Post post = (Post) CommonUtil.convertDto2Entity(postDto, Post.class);
        Post isExistPost = validateIsExist(post);
        String userRealName = commonUtil.getLoginUsernameByToken(token);
        // 设置未封装的字段
        if (isExistPost == null) {
            post.getPostId();
            String postId = UUID.randomUUID().toString().replaceAll("-", "");
            post.setPostId(postId);
            // 浏览量为0
            post.setPostPageViews(BigInteger.ZERO);
            // 点赞量为0
            post.setPostLikes(BigInteger.ZERO);
            // 创建人和更新时间
            post.setCreateTime(new Date());
            post.setCreatePerson(userRealName);
            result = postMapper.save(post);
        } else {
            post.setPostId(isExistPost.getPostId());
            // 浏览量为0
            post.setPostPageViews(isExistPost.getPostPageViews());
            // 点赞量为0
            post.setPostLikes(isExistPost.getPostLikes());
            // 设置更新人和更新时间
            post.setUpdatePerson(userRealName);
            post.setUpdateTime(new Date());
            result = postMapper.update(post);
        }
        return result;
    }

    /**
     * 校验该帖子是否已经发布
     * @param post
     * @return
     */
    private Post validateIsExist(Post post) {
        // 校验是否存在postId
        if (post != null && !StringUtils.isEmpty(post.getPostId())) {
            List<Post> posts = postMapper.find(post);
            if (!ValidateUtil.listIsEmpty(posts)) {
                return posts.get(0);
            }
        }
        return null;
    }
}
