package com.itcast.education.mapper;

import com.itcast.education.model.community.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论信息模块
 * @author zheng.zhang
 */
@Mapper
public interface CommentMapper {

    List<Comment> find(Comment comment);
}
