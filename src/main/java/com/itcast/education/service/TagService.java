package com.itcast.education.service;

import com.itcast.education.model.course.Tag;

import java.util.List;

/**
 *
 */
public interface TagService {

    /**
     * 获取标签列表
     * @param tag
     * @return
     */
    List<Tag> find(Tag tag);

    /**
     * 根据ID
     * @param tagId
     * @return
     */
    Tag findById(Integer tagId);

    /**
     * 根据ID查找名称集合
     * @param tagIds
     * @return
     */
    List<String> getTagNames(List<Integer> tagIds);
}
