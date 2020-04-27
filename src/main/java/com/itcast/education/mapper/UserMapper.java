package com.itcast.education.mapper;

import com.itcast.education.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 用户信息查询
     * @param user
     * @return
     */
    User findUser(User user);
}