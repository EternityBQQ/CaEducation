package com.itcast.education.controller.course;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.model.dto.CourseDto;
import com.itcast.education.model.pojo.base.ResponseModel;
import com.itcast.education.model.pojo.course.Course;
import com.itcast.education.service.CourseService;
import com.itcast.education.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zheng.zhang
 */
@RestController
@Api(tags = "课程接口")
@Slf4j
@RequestMapping("/education")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "添加课程", notes = "传入课程相关信息", authorizations = {@Authorization(value = "token")})
    @PutMapping("/addCourse")
    public ResponseModel addCourse(@RequestBody CourseDto courseDto, @RequestParam String token) {
        // DTO转化为实体对象
        Course course = (Course) CommonUtil.convertDto2Entity(courseDto, Course.class);
        boolean result = courseService.saveCourse(course, token);
        if (result) {
            log.info("保存课程成功", course);
            return ResponseModel.ok();
        } else {
            log.error("保存课程失败");
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, ErrorMessage.SAVE_FAILED);
        }
    }
}
