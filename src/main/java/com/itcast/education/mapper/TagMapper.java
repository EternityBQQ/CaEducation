package com.itcast.education.mapper;

import com.itcast.education.model.pojo.course.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {

    /**
     * 查询
     * @param tag
     * @return
     */
    List<Tag> find(Tag tag);
}
