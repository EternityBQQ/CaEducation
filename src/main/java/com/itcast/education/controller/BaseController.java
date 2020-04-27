package com.itcast.education.controller;

import com.itcast.education.controller.dto.HomePageDto;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.service.MediaOutputService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基础controller
 * @author zheng.zhang
 */
@Api(tags = "基础接口")
@RestController
@RequestMapping("/education")
public class BaseController {
    @Autowired
    private MediaOutputService mediaOutputService;

    /**
     * 获取媒体查询数据
     * @param carouseSize 轮播图轮播数量
     * @param categorySize 分类标签展示数量
     * @return
     */
    @ApiOperation(value = "获取媒体数据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carouseSize", value = "轮播图数量", paramType = "query"),
            @ApiImplicitParam(name = "categorySize", value = "分类页签数量", paramType = "query"),
    })
    @GetMapping("/mediaOutPut")
    public ResponseModel getMediaOutPut(Integer carouseSize, Integer categorySize) {
        // 封装首页DTO对象
        HomePageDto pageData = mediaOutputService.queryHomePageData(carouseSize, categorySize);
        return ResponseModel.ok(pageData);
    }
}
