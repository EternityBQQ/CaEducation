package com.itcast.education.service.impl;

import com.itcast.education.mapper.TagMapper;
import com.itcast.education.model.pojo.course.Tag;
import com.itcast.education.service.TagService;
import com.itcast.education.utils.ValidateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> find(Tag tag) {
        return tagMapper.find(tag);
    }

    @Override
    public Tag findById(Integer tagId) {
        List<Tag> tags = find(new Tag(tagId));
        if (!ValidateUtil.listIsEmpty(tags)) {
            return tags.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<String> getTagNames(List<Integer> tagIds) {
        List<String> tags = new ArrayList<>();
        if (!ValidateUtil.listIsEmpty(tagIds)) {
            for (Integer id : tagIds) {
                // 通过Id获取标签对象
                Tag tag = findById(id);
                if (tag != null) {
                    String tagName = tag.getTagName();
                    tags.add(tagName);
                }
            }
        }
        return tags;
    }
}
