package com.itcast.education.mapper;

import com.itcast.education.model.media.MediaOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MediaOutputMapper {
    /**
     * 根据类型查找媒体信息
     * @param type
     * @param size
     * @return
     */
    List<MediaOutput> queryMediaByType(String type, Integer size);

    List<MediaOutput> find(MediaOutput findMediaOutput);
}
