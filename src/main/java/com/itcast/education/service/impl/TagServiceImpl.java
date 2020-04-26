package com.itcast.education.service.impl;

import com.itcast.education.mapper.TagMapper;
import com.itcast.education.model.course.Tag;
import com.itcast.education.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public Tag find(Tag tag) {
        return tagMapper.find(tag);
    }
}
