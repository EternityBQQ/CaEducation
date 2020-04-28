package com.itcast.education.controller.course;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.controller.dto.CourseDto;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.model.course.Course;
import com.itcast.education.service.CourseService;
import com.itcast.education.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zheng.zhang
 */
@RestController
@Api(tags = "课程接口")
@RequestMapping("/education")
public class CourseController {
    private Logger LOG = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "添加课程", notes = "传入课程相关信息", authorizations = {@Authorization(value = "token")})
    @PutMapping("/addCourse")
    public ResponseModel addCourse(CourseDto courseDto, @RequestParam String loginId) {
        // DTO转化为实体对象
        Course course = (Course) CommonUtil.convertDto2Entity(courseDto, Course.class);
        boolean result = courseService.saveCourse(course, loginId);
        if (result) {
            LOG.info("保存课程成功", course);
            return ResponseModel.ok();
        } else {
            LOG.error("保存课程失败");
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, ErrorMessage.SAVE_FAILED);
        }
    }
}
