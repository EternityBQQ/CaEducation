package com.itcast.education.service.impl;

import com.itcast.education.mapper.UserMapper;
import com.itcast.education.model.user.User;
import com.itcast.education.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User find(User user) {
        return userMapper.findUser(user);
    }
}
