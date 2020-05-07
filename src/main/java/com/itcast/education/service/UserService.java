package com.itcast.education.service;

import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    /**
     * 登录逻辑
     * @param username 用户名
     * @param password 密码
     * @param request 请求
     * @param response 响应
     * @return 统一结果集
     */
    ResponseModel userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 登出
     * @param token
     * @return
     */
    ResponseModel loginOut(String token);
}
