package com.itcast.education.service.impl;

import com.itcast.education.config.ErrorMessage;
import com.itcast.education.config.GeneralConstant;
import com.itcast.education.mapper.UserMapper;
import com.itcast.education.model.base.ResponseModel;
import com.itcast.education.model.user.User;
import com.itcast.education.service.UserService;
import com.itcast.education.utils.CookieUtil;
import com.itcast.education.utils.LoginUtil;
import com.itcast.education.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseModel userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 1.判断用户名密码是否正确
        User user = userMapper.findUser(new User(username));
        if (user == null) {
            // 用户不存在
            return ResponseModel.build(ErrorMessage.LOGIN_FAILED, GeneralConstant.NOT_EXIST_USER);
        }
        if (!LoginUtil.validateUser(user, password)) {
            return ResponseModel.build(ErrorMessage.LOGIN_FAILED, GeneralConstant.ERROR_PASSWORD);
        }
        // 2.生成TOKEN
        String token = UUID.randomUUID().toString();
        // 3.把用户信息写入Redis->持久化user对象,设置session的过期时间
        redisUtil.set(token, user);
        // 4.清除账号和盐密码,避免泄露
        user.setPassword(null);
        user.setSalt(null);
        // 5.添加cookie
        CookieUtil.setCookie(request, response, GeneralConstant.USER_TOKEN, token);
        // 6.返回token
        return ResponseModel.ok(token);
    }

    @Override
    public ResponseModel loginOut(String token) {
        // 清除Token
        redisUtil.deleteCache(token);
        return ResponseModel.ok();
    }
}
