package com.itcast.education.controller.user;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.config.GeneralConstant;
import com.itcast.education.model.dto.UserDto;
import com.itcast.education.model.pojo.base.ResponseModel;
import com.itcast.education.model.pojo.user.User;
import com.itcast.education.service.UserService;
import com.itcast.education.utils.CommonUtil;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zheng.zhang
 * @description 用户信息接口
 * @date 2020/5/7 11:11
 */
@Api(tags = "用户信息接口")
@RestController
@RequestMapping("/education")
public class UserController {
    private Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录", authorizations = {@Authorization(value = "token")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query")
    })
    @GetMapping("/login")
    public ResponseModel login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 登录逻辑校验
            ResponseModel responseModel = userService.userLogin(username, password, request, response);
            return responseModel;
        } catch (Exception e) {
            return ResponseModel.build(ErrorMessage.LOGIN_FAILED, GeneralConstant.LOGIN_FAILED);
        }
    }

    @ApiOperation(value = "退出登录", authorizations = {@Authorization(value = "token")})
    @DeleteMapping("/loginOut")
    public ResponseModel loginOut(HttpServletRequest request) {
        String token = request.getHeader(GeneralConstant.USER_TOKEN);
        try {
            LOG.info("登出token: " + token);
            ResponseModel responseModel = userService.loginOut(token);
            return responseModel;
        } catch (Exception e) {
            LOG.info("登出失败: " + token);
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.PROGRESS_SERVLET);
        }
    }

    @ApiOperation(value = "注册", authorizations = {@Authorization(value = "token")})
    @PutMapping("/register")
    public ResponseModel registerUser(UserDto userDto) {
        User user = (User) CommonUtil.convertDto2Entity(userDto, User.class);
        boolean result = userService.saveOrUpdateUser(user);
        if (result) {
            LOG.info("注册成功: " + user);
            return ResponseModel.ok();
        } else {
            LOG.error("注册失败");
            return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, ErrorMessage.SAVE_FAILED);
        }
    }
}
