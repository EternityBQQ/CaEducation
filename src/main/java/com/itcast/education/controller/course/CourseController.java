package com.itcast.education.controller.course;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.model.course.Course;
import com.itcast.education.service.CourseService;
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

    @PutMapping("/education/addCourse")
    public ResponseModel addCourse(@RequestParam Course course) {
        boolean result = courseService.saveCourse(course);
        if (result) {
            return ResponseModel.ok();
        } else {
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, ErrorMessage.SAVE_FAILED);
        }
    }
}
