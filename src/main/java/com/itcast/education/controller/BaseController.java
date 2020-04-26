package com.itcast.education.controller;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.controller.dto.HomePageDto;
import com.itcast.education.model.course.Course;
import com.itcast.education.model.media.MediaOutput;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.service.CourseService;
import com.itcast.education.service.MediaOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基础controller
 * @author zheng.zhang
 */
@RestController
public class BaseController {
    @Autowired
    private MediaOutputService mediaOutputService;

    /**
     * 获取媒体查询数据
     * @param token 用户标识
     * @param carouseSize 轮播图轮播数量
     * @param categorySize 分类标签展示数量
     * @return
     */
    @GetMapping("/education/mediaOutPut")
    public ResponseModel getMediaOutPut(@RequestParam String token, Integer carouseSize, Integer categorySize) {
        // 封装首页DTO对象
        HomePageDto pageData = mediaOutputService.queryHomePageData(carouseSize, categorySize);
        return ResponseModel.ok(pageData);
    }
}
