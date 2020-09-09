package com.itcast.education.model.pojo.course;

import com.itcast.education.model.pojo.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程Model
 * @author zheng.zhang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends BaseModel<Course> {
    private String courseId;
    private String courseName;
    private String tags;
    private Double coursePrice;
    private Double discountPrice;
    private Integer saleCourse;
    private String courseUrl;
}
