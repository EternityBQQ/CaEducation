package com.itcast.education.mapper;

import com.itcast.education.model.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 用户信息查询
     * @param user
     * @return
     */
    User findUser(User user);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    Integer saveUser(User user);
}
