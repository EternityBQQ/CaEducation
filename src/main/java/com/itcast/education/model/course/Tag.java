package com.itcast.education.model.course;

import com.itcast.education.model.base.BaseModel;

/**
 * 标签对象
 */
public class Tag extends BaseModel<Tag> {
    private Integer tagId;
    private String tagName;
    private String tagDesc;

    public Tag() {}

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }
}
