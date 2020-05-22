package com.itcast.education.service.impl;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.controller.dto.CommunityPageDto;
import com.itcast.education.controller.dto.PostDto;
import com.itcast.education.mapper.CommentMapper;
import com.itcast.education.mapper.PostMapper;
import com.itcast.education.model.community.Comment;
import com.itcast.education.model.community.Post;
import com.itcast.education.model.media.MediaOutput;
import com.itcast.education.model.user.User;
import com.itcast.education.service.CommunityService;
import com.itcast.education.service.MediaOutputService;
import com.itcast.education.service.TagService;
import com.itcast.education.service.UserService;
import com.itcast.education.utils.CommonUtil;
import com.itcast.education.utils.JsonUtil;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

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
    private UserService userService;
    @Autowired
    private MediaOutputService mediaOutputService;
    @Autowired
    private TagService tagService;

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
        pageData = convertPageData(posts);
        return pageData;
    }

    /**
     * 封装社区交流版块页面数据
     * @param posts
     * @return
     */
    private CommunityPageDto convertPageData(List<Post> posts) {
        CommunityPageDto pageDto = new CommunityPageDto();
        //1. 设置热帖=>标题贴暂时设置为最新数据
        Post hotPost = posts.get(posts.size() - 1);
        // 用户信息
        Map<String, Object> hotPostMap = new HashMap<>();
        // 用户实体信息
        User user = userService.findUser(hotPost.getUserId());
        // 根据用户实体查到头像
        String headIcon = userService.findHeadIcon(hotPost.getUserId());
        // 根据媒体数据ID查找媒体数据集合
        List<String> imagesUrl = mediaOutputService.findByIds(hotPost.getMediaIds());
        // 头像
        hotPostMap.put(GeneralConstant.HEAD_ICON, headIcon);
        // 名称
        hotPostMap.put(GeneralConstant.USERNAME, user.getUserRealName());
        // 发帖时间
        hotPostMap.put(GeneralConstant.PUBLIC_TIME, hotPost.getCreateTime().toString());
        // 帖子内容
        hotPostMap.put(GeneralConstant.POST_CONTENT, hotPost.getPostContent());
        // 帖子媒体图片
        hotPostMap.put(GeneralConstant.POST_IMAGES, imagesUrl);
        pageDto.setHotPost(hotPostMap);
        //2. 设置论坛贴=>暂时设置为除开最新帖子之外的帖子
        posts.remove(posts.size() - 1);
        List<Map<String, Object>> postsMap = getOriginalPost(posts);
        pageDto.setPosts(postsMap);
        return pageDto;
    }

    /**
     * 设置其他帖子
     * @param posts 帖子集合实例
     * @return 其他帖子集合对象
     */
    private List<Map<String, Object>> getOriginalPost(List<Post> posts) {
        List<Map<String, Object>> result = new ArrayList<>();
        // ============================
        if (!ValidateUtil.listIsEmpty(posts)) {
            for (Post post: posts) {
                Map<String, Object> postMap = new HashMap<>();
                // 标题
                postMap.put(GeneralConstant.TITLE, post.getPostTitle());
                // 内容
                postMap.put(GeneralConstant.POST_CONTENT, post.getPostContent());
                // 图片
                String mediaIds = post.getMediaIds();
                List<Integer> ids = (List<Integer>) JsonUtil.strToPojo(mediaIds, List.class);
                String mediaUrl = GeneralConstant.EMPTY;
                if (!ValidateUtil.listIsEmpty(ids)) {
                    MediaOutput media = mediaOutputService.findMediaById(ids.get(0));
                    mediaUrl = media.getUrl();
                }
                postMap.put(GeneralConstant.IMG_URL, mediaUrl);
                // 标签
                String tags = post.getTags();
                List<Integer> tagsList = (List<Integer>) JsonUtil.strToPojo(tags, List.class);
                List<String> tagNames = tagService.getTagNames(tagsList);
                postMap.put(GeneralConstant.TAGS, tagNames);
                result.add(postMap);
            }
        }
        // ============================
        return result;
    }

    @CachePut(cacheNames = "communityPageData", key = "#postDto.userId")
    @Override
    public boolean sendOrUpdateArticle(PostDto postDto, String token) {
        boolean result;
        // 通过URL找到ID
        if (postDto == null) {
            return false;
        }
        List<String> urlList = (List<String>) JsonUtil.strToPojo(postDto.getMediaIds(), List.class);
        List<Integer> ids = new ArrayList<>();
        if (!ValidateUtil.listIsEmpty(urlList)) {
            for (String url : urlList) {
                MediaOutput mediaOutput = mediaOutputService.findByUrl(url);
                if (mediaOutput != null) {
                    ids.add(mediaOutput.getMediaId());
                }
            }
        }
        postDto.setMediaIds(ids.toString());
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
