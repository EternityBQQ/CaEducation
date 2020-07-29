package com.itcast.education.mapper;

import com.itcast.education.model.pojo.community.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<Post> find(Post post);

    boolean save(Post post);

    boolean update(Post post);
}
