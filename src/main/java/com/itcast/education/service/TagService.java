package com.itcast.education.service;

import com.itcast.education.model.course.Tag;

/**
 *
 */
public interface TagService {

    /**
     * 根据ID获取标签
     * @param tag
     * @return
     */
    Tag find(Tag tag);
}
