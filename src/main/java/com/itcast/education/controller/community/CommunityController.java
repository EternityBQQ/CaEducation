package com.itcast.education.controller.community;

import com.itcast.education.controller.dto.CommunityPageDto;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zheng.zhang
 * @description 社区交流信息数据
 * @date 2020/5/15 10:51
 */
@RestController
@Api(tags = "社区交流接口")
@RequestMapping("/education")
public class CommunityController {
    private Logger LOG = LoggerFactory.getLogger(CommunityController.class);
    @Autowired
    private CommunityService communityService;

    @ApiOperation(value = "页面数据列表", notes = "交流页面数据json", authorizations = {@Authorization(value = "token")})
    @GetMapping(value = "/communityTab")
    public ResponseModel loadCommunityPageData(Integer pageDataSize) {
        CommunityPageDto pageDto = communityService.getCommunityPageData(pageDataSize);
        LOG.info("社区数据获取成功", pageDto);
        return ResponseModel.ok(pageDto);
    }
}
