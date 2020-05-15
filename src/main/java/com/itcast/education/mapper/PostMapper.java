package com.itcast.education.mapper;

import com.itcast.education.model.community.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> find(Post post);
}
