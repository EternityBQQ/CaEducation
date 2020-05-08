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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
        if (!StringUtils.isEmpty(token)) {
            redisUtil.deleteCache(token);
            return ResponseModel.ok();
        }
        return ResponseModel.build(ErrorMessage.DEFAULT_ERROR_CODE, GeneralConstant.PROGRESS_SERVLET);
    }

    @Override
    public boolean saveOrUpdateUser(User user) {
        boolean flag = false;
        if (user == null) {
            return false;
        }

        String id = user.getUserId();
        if (StringUtils.isEmpty(id)) {
            // 封装前端未封装字段
            String userId = UUID.randomUUID().toString().replaceAll("-", "");
            user.setUserId(userId);
            // 创建人和创建时间
            user.setCreatePerson(user.getUserRealName());
            user.setCreateTime(new Date());
        } else {
            // 修改人和修改时间
            user.setUpdatePerson(user.getUserRealName());
            user.setUpdateTime(new Date());
        }
        // 存储真实密码=>因为接下来会加密密码,因此在加密前先存储真实密码
        user.setRealPassword(user.getPassword());
        // 加密密码和盐
        LoginUtil.encryptionPassword(user);

        Integer rows = userMapper.saveUser(user);
        if (rows > 0) {
            flag = true;
        }
        return flag;
    }
}
