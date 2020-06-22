package com.itcast.education.service.impl;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.dto.CommentDto;
import com.itcast.education.dto.CommunityPageDto;
import com.itcast.education.dto.PostDto;
import com.itcast.education.vo.ReplyCommentVo;
import com.itcast.education.mapper.CommentMapper;
import com.itcast.education.mapper.PostMapper;
import com.itcast.education.model.community.Comment;
import com.itcast.education.model.community.Post;
import com.itcast.education.model.user.User;
import com.itcast.education.service.CommunityService;
import com.itcast.education.service.TagService;
import com.itcast.education.service.UserService;
import com.itcast.education.utils.CommonUtil;
import com.itcast.education.utils.JsonUtil;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/**
 * @author zheng.zhang
 * description 社区交流信息模块服务层
 * date 2020/5/15 11:06
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
    private TagService tagService;

    @Resource
    private CommonUtil commonUtil;

    @Override
    public CommunityPageDto getCommunityPageData(Integer pageDataSize) throws JSONException {
        CommunityPageDto pageData;
        // 帖子
        List<Post> posts = postMapper.find(new Post());
        if (ValidateUtil.listIsEmpty(posts)) {
            return null;
        }
        // 评论
        for (Post post : posts) {
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
     *
     * @param posts 帖子
     * @return 社区信息
     */
    @SuppressWarnings("all")
    private CommunityPageDto convertPageData(List<Post> posts) throws JSONException {
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
        List<String> imagesUrl = (List<String>) JsonUtil.strToPojo(hotPost.getMediaUrls(), List.class);
        // 头像
        hotPostMap.put(GeneralConstant.HEAD_ICON, headIcon);
        // 名称
        hotPostMap.put(GeneralConstant.USERNAME, user.getUserRealName());
        // 发帖时间
        hotPostMap.put(GeneralConstant.PUBLIC_TIME, commonUtil.formatDateTime(hotPost.getCreateTime(), GeneralConstant.NORMAL_DATE_STYLE));
        // 帖子内容
        hotPostMap.put(GeneralConstant.POST_CONTENT, hotPost.getPostContent());
        // 帖子媒体图片
        hotPostMap.put(GeneralConstant.POST_IMAGES, imagesUrl);
        // 阅读量
        hotPostMap.put(GeneralConstant.POST_PAGE_VIEWS, hotPost.getPostPageViews());
        // 点赞量
        hotPostMap.put(GeneralConstant.POST_LIKES, hotPost.getPostLikes());
        // 评论数
        int commentNum = GeneralConstant.ZERO;
        if (hotPost.getComments() != null) {
            commentNum = hotPost.getComments().size();
        }
        hotPostMap.put(GeneralConstant.POST_COMMENT_NUMBER, commentNum);
        // 将评论信息树形结构封装
        List<Comment> commentTree = getCommentTree(hotPost.getComments());
        // 热帖评论
        hotPostMap.put(GeneralConstant.COMMENTS, convertCommentData(commentTree));
        pageDto.setHotPost(hotPostMap);
        //2. 设置论坛贴=>暂时设置为除开最新帖子之外的帖子
        posts.remove(posts.size() - 1);
        List<Map<String, Object>> postsMap = getOriginalPost(posts);
        pageDto.setPosts(postsMap);
        return pageDto;
    }

    /**
     * 封装评论的树形结构
     *
     * @param comments 评论集合
     * @return 评论树形结构
     */
    private List<Comment> getCommentTree(List<Comment> comments) {
        List<Comment> resultComment = new ArrayList<>();
        if (!ValidateUtil.listIsEmpty(comments)) {
            comments.stream()
                    .filter(node -> GeneralConstant.ZERO.equals(node.getReplyCommentId()))
                    .forEach(parentNode -> {
                        // 设置子评论信息
                        setChildComment(comments, parentNode);
                        resultComment.add(parentNode);
                    });
        }
        return resultComment;
    }

    /**
     * 设置子评论信息
     * @param comments 评论
     * @param parentNode 父节点
     */
    private void setChildComment(List<Comment> comments, Comment parentNode) {
        comments.stream()
                .filter(node -> !GeneralConstant.ZERO.equals(node.getReplyCommentId()))
                .filter(childNode -> isChildComment(parentNode, childNode))
                .forEach(childComment -> {
                    User replyUser = userService.findUser(childComment.getCommentUserId());
                    String replyUsername = GeneralConstant.EMPTY;
                    if (replyUser != null) {
                        replyUsername = replyUser.getUsername();
                    }
                    parentNode.getReplyComments().add(new ReplyCommentVo(replyUsername, childComment.getCommentContent()));
                });
    }

    /**
     * 是否是父节点的子节点
     * @param parentNode 父节点
     * @param childNode 子节点
     * @return 结果
     */
    private boolean isChildComment(Comment parentNode, Comment childNode) {
        boolean flag = false;
        if (childNode.getReplyCommentId() > GeneralConstant.ZERO) {
            if (childNode.getReplyCommentId().equals(parentNode.getCommentId())) {
                flag = true;
            } else {
                Comment tempNode = findCommentById(childNode.getReplyCommentId());
                flag = isChildComment(parentNode, tempNode);
            }
        }
        return flag;
    }

    /**
     * 通过ID查找评论信息
     * @param commentId 评论ID
     * @return 结果
     */
    public Comment findCommentById(Integer commentId) {
        if (commentId < GeneralConstant.ZERO) {
            return null;
        }
        Comment comment = null;
        List<Comment> comments = commentMapper.find(new Comment(commentId));
        if (comments != null && !comments.isEmpty()) {
            comment = comments.get(0);
        }
        return comment;
    }

    /**
     * 封装评论信息
     * @param comments 评论
     * @return 返回结果
     */
    private List<Map<String, Object>> convertCommentData(List<Comment> comments) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (!ValidateUtil.listIsEmpty(comments)) {
            for (Comment comment : comments) {
                Map<String, Object> itemObj = new HashMap<>();
                String commentUserId = comment.getCommentUserId();
                User commentUser = userService.findUser(commentUserId);
                // 登录人头像
                String headIcon = GeneralConstant.EMPTY;
                // 登录人名称
                String commentUsername = GeneralConstant.EMPTY;
                if (commentUser != null) {
                    headIcon = userService.findHeadIcon(commentUser);
                    commentUsername = commentUser.getUsername();
                }
                itemObj.put(GeneralConstant.IMAGE, headIcon);
                itemObj.put(GeneralConstant.USERNAME, commentUsername);
                // 评论内容
                itemObj.put(GeneralConstant.COMMENT_CONTENT, comment.getCommentContent());
                // 回复帖子=>回复人: 回复内容的格式
                itemObj.put(GeneralConstant.REPLY_COMMENTS, comment.getReplyComments());
                // 发布时间
                itemObj.put(GeneralConstant.CREATE_TIME, comment.getCreateTime());
                result.add(itemObj);
            }
        }
        return result;
    }

    /**
     * 设置其他帖子
     * @param posts 帖子集合实例
     * @return 其他帖子集合对象
     */
    @SuppressWarnings("all")
    private List<Map<String, Object>> getOriginalPost(List<Post> posts) {
        List<Map<String, Object>> result = new ArrayList<>();
        // ============================
        if (!ValidateUtil.listIsEmpty(posts)) {
            for (Post post : posts) {
                Map<String, Object> postMap = new HashMap<>();
                // 标题
                postMap.put(GeneralConstant.TITLE, post.getPostTitle());
                // 内容
                postMap.put(GeneralConstant.POST_CONTENT, post.getPostContent());
                // 图片
                String mediaIds = post.getMediaUrls();
                List<String> mediaUrls = (List<String>) JsonUtil.strToPojo(mediaIds, List.class);
                postMap.put(GeneralConstant.IMG_URL, mediaUrls);
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

    @Override
    @SuppressWarnings("all")
    public boolean sendOrUpdateArticle(PostDto postDto, String token) {
        boolean result;
        // 通过URL找到ID
        if (postDto == null) {
            return false;
        }
        // 因为转换会出错
        Post post = (Post) CommonUtil.convertDto2Entity(postDto, Post.class);
        Post isExistPost = validateIsExist(post);
        User user = commonUtil.getLoginUsernameByToken(token);
        String userRealName = GeneralConstant.COMMON_PERSON;
        if (user != null && StringUtils.isEmpty(user.getUserRealName())) {
            userRealName = user.getUserRealName();
        }
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

    @Override
    @SuppressWarnings("all")
    public boolean sendComment(CommentDto commentDto, String userToken) {
        boolean result = false;
        Comment comment = (Comment) commonUtil.convertDto2Entity(commentDto, Comment.class);
        User user = commonUtil.getLoginUsernameByToken(userToken);
        String userId = GeneralConstant.EMPTY;
        String username = GeneralConstant.COMMON_PERSON;
        if (user != null) {
            userId = user.getUserId();
        }
        // 设置评论人ID
        comment.setCommentUserId(userId);
        // 设置点赞量,默认为0
        comment.setLikes(GeneralConstant.ZERO);
        // 创建人
        comment.setCreatePerson(username);
        // 创建时间
        comment.setCreateTime(new Date());
        result = commentMapper.save(comment);
        return result;
    }

    /**
     * 校验该帖子是否已经发布
     * @param post 校验贴
     * @return 存在帖子
     */
    private Post validateIsExist(Post post) {
        // 校验是否存在postId
        Post result = null;
        if (post != null && !StringUtils.isEmpty(post.getPostId())) {
            List<Post> posts = postMapper.find(post);
            if (!ValidateUtil.listIsEmpty(posts)) {
                result = posts.get(0);
            }
        }
        return result;
    }
}
