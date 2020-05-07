package com.itcast.education.controller.user;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.config.GeneralConstant;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.service.UserService;
import io.swagger.annotations.*;
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

    @PostMapping("/loginOut")
    public ResponseModel loginOut(HttpServletRequest request) {
        String token = request.getHeader(GeneralConstant.USER_TOKEN);
        ResponseModel responseModel = userService.loginOut(token);
        return responseModel;
    }
}
