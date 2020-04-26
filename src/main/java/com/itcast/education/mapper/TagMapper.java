package com.itcast.education.mapper;

import com.itcast.education.model.course.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    /**
     * 查询
     * @param tag
     * @return
     */
    Tag find(Tag tag);
}
