package com.itcast.education.service;

import com.itcast.education.model.pojo.base.ResponseModel;
import com.itcast.education.model.pojo.user.User;

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

    /**
     * 注册
     * @return
     * @param user
     */
    boolean saveOrUpdateUser(User user);

    /**
     * 根据用户ID查找用户信息
     * @param userId
     * @return
     */
    User findUser(String userId);

    /**
     * 查找用户头像
     * @param userId
     * @return
     */
    String findHeadIcon(String userId);

    /**
     * 根据用户获取头像
     * @param user
     * @return
     */
    String findHeadIcon(User user);
}
