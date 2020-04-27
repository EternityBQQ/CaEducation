package com.itcast.education.controller.course;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.model.course.Course;
import com.itcast.education.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zheng.zhang
 */
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "添加课程", notes = "传入课程相关信息")
    @PutMapping("/education/addCourse")
    public ResponseModel addCourse(Course course, @RequestParam String loginId) {
        boolean result = courseService.saveCourse(course, loginId);
        if (result) {
            return ResponseModel.ok();
        } else {
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, ErrorMessage.SAVE_FAILED);
        }
    }
}
