package com.itcast.education.service.impl;

import com.itcast.education.controller.dto.CommunityPageDto;
import com.itcast.education.mapper.CommentMapper;
import com.itcast.education.mapper.PostMapper;
import com.itcast.education.model.community.Comment;
import com.itcast.education.model.community.Post;
import com.itcast.education.service.CommunityService;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
