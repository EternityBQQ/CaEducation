package com.itcast.education.controller;

import com.itcast.education.controller.course.CourseController;
import com.itcast.education.model.dto.HomePageDto;
import com.itcast.education.model.pojo.base.ResponseModel;
import com.itcast.education.service.MediaOutputService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础controller
 * @author zheng.zhang
 */
@Api(tags = "基础接口")
@RestController
@RequestMapping("/education")
public class BaseController {
    private Logger LOG = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private MediaOutputService mediaOutputService;

    /**
     * 获取媒体查询数据
     * @param carouseSize 轮播图轮播数量
     * @param categorySize 分类标签展示数量
     * @return
     */
    @ApiOperation(value = "获取媒体数据信息", authorizations = {@Authorization(value = "token")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carouseSize", value = "轮播图数量", paramType = "query"),
            @ApiImplicitParam(name = "categorySize", value = "分类页签数量", paramType = "query"),
    })
    @GetMapping("/mediaOutPut")
    public ResponseModel getMediaOutPut(Integer carouseSize, Integer categorySize) {
        // 封装首页DTO对象
        HomePageDto pageData = mediaOutputService.queryHomePageData(carouseSize, categorySize);
        LOG.info("首页数据获取成功", pageData);
        return ResponseModel.ok(pageData);
    }
}
