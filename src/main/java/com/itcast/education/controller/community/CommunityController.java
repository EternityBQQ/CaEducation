package com.itcast.education.controller.community;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.config.GeneralConstant;
import com.itcast.education.dto.CommentDto;
import com.itcast.education.dto.CommunityPageDto;
import com.itcast.education.dto.PostDto;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        CommunityPageDto pageDto;
        try {
            pageDto = communityService.getCommunityPageData(pageDataSize);
            LOG.info("社区数据获取成功", pageDto);
        } catch (JSONException e) {
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.PROGRESS_SERVLET);
        }
        return ResponseModel.ok(pageDto);
    }

    @ApiOperation(value = "发表帖子", notes = "帖子信息", authorizations = {@Authorization(value = "token")})
    @PutMapping(value = "/sendArticle")
    public ResponseModel sendArticle(@RequestBody PostDto postDto, HttpServletRequest request) {
        try {
            String token = request.getHeader(GeneralConstant.USER_TOKEN);
            boolean flag = communityService.sendOrUpdateArticle(postDto, token);
            if (flag) {
                return ResponseModel.ok();
            } else {
                return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.SEND_FAILED);
            }
        } catch (Exception e) {
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.PROGRESS_SERVLET);
        }
    }

    @ApiOperation(value = "发表评论", notes = "评论信息", authorizations = {@Authorization(value = "token")})
    @PutMapping(value = "/sendComment")
    public ResponseModel sendComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        try {
            String userToken = request.getHeader(GeneralConstant.USER_TOKEN);
            boolean flag = communityService.sendComment(commentDto, userToken);
            if (flag) {
                return ResponseModel.ok();
            } else {
                return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.SEND_FAILED);
            }
        } catch (Exception e) {
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.PROGRESS_SERVLET);
        }
    }
}
