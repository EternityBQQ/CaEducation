package com.itcast.education.model.course;

import com.itcast.education.model.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程Model
 * @author zheng.zhang
 */
@Data
@NoArgsConstructor
public class Course extends BaseModel<Course> {
    private String courseId;
    private String courseName;
    private String tags;
    private Double coursePrice;
    private Double discountPrice;
    private Integer saleCourse;
    private String courseUrl;
}
