package com.itcast.education.service;

import com.itcast.education.model.user.User;

public interface UserService {

    /**
     * 查询用户信息
     * @param user
     * @return
     */
    User find(User user);
}
