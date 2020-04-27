package com.itcast.education.model.course;

import com.itcast.education.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标签对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseModel<Tag> {
    private Integer tagId;
    private String tagName;
    private String tagDesc;

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }
}
