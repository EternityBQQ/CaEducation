package com.itcast.education.utils;

import com.itcast.education.model.pojo.user.User;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author zheng.zhang
 * Description 登录工具类
 * @date 2020/5/7 14:09
 */
public class LoginUtil {

    /**
     * 给用户信息加盐加密
     * @param user
     */
    public static void encryptionPassword(User user) {
        String salt = UUID.randomUUID().toString();
        String tempPassword = salt + user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(tempPassword.getBytes());
        user.setSalt(salt);
        user.setPassword(md5Password);
    }

    /**
     * 校验用户密码是否正确
     * @param user
     * @param password
     * @return
     */
    public static boolean validateUser(User user, String password) {
        // 用户盐+前台输入密码
        String tempPassword = user.getSalt() + password;
        // 生成MD5加密密码
        String md5Password = DigestUtils.md5DigestAsHex(tempPassword.getBytes());
        return user.getPassword().equals(md5Password);
    }
}
