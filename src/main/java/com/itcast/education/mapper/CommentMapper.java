package com.itcast.education.mapper;

import com.itcast.education.model.pojo.community.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论信息模块
 * @author zheng.zhang
 */
@Mapper
public interface CommentMapper {

    List<Comment> find(Comment comment);

    /**
     * 保存
     * @param comment 评论
     * @return 是否成功
     */
    boolean save(Comment comment);
}
