package com.itcast.education.model.dto;

import com.itcast.education.utils.JsonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zheng.zhang
 * @description 课程DTO
 * @date 2020/4/27 16:41
 */
@ApiModel(value = "课程")
public class CourseDto {
    @ApiModelProperty("课程名称")
    private String courseName;
    @ApiModelProperty("标签")
    private List<Integer> tags;
    @ApiModelProperty("课程售价")
    private Double coursePrice;
    @ApiModelProperty("折扣价格")
    private Double discountPrice;
    @ApiModelProperty("已售数量")
    private Integer saleCourse;
    @ApiModelProperty("课程地址")
    private String courseUrl;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTags() {
        return JsonUtil.objectToJson(this.tags);
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getSaleCourse() {
        return saleCourse;
    }

    public void setSaleCourse(Integer saleCourse) {
        this.saleCourse = saleCourse;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }
}
