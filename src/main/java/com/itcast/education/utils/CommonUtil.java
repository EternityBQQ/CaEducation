package com.itcast.education.utils;

import com.itcast.education.config.GeneralConstant;
import com.itcast.education.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author zheng.zhang
 * @description 公共工具类
 * @date 2020/4/27 17:01
 */
public class CommonUtil {
    private static Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

    @Autowired
    private static RedisUtil redisUtil;

    /**
     * DTO转实体
     * @param dto DTO对象
     * @param targetClass 目标对象字节码
     * @return
     */
    public static Object convertDto2Entity(Object dto, Class targetClass) {
        if (dto == null || targetClass == null) {
            return null;
        }
        Object result = null;
        // DTO字节码文件
        Class dtoClass = dto.getClass();
        try {
            // 返回对象实例
            result = targetClass.newInstance();
            // DTO方法集合
            Method[] dtoMethods = dtoClass.getMethods();
            // 目标对象方法集合
            Method[] targetMethods = targetClass.getMethods();
            for (Method method : dtoMethods) {
                String dtoMethodName = method.getName();
                // 拿到DTO对象所有的get方法
                if (dtoMethodName.startsWith("get")) {
                    // 执行get方法，获取字段值
                    Object invokeResult = method.invoke(dto);
                    for (Method targetMethod : targetMethods) {
                        String dtoField = dtoMethodName.substring(3);
                        String targetMethodName = targetMethod.getName();
                        String targetFiled = targetMethodName.substring(3);
                        // 匹配DTO和实体类字段对应的字段，通过set设置值
                        if (targetMethodName.startsWith("set") && targetFiled.equals(dtoField)) {
                            targetMethod.invoke(result, invokeResult);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("[CommonUtil] [convertDto2Entity] 出现异常：", e);
        }
        return result;
    }

    /**
     * 根据Token获取登录用户真实名称
     * @param token
     * @return
     */
    public static String getLoginUsernameByToken(String token) {
        // 根据Token获取登录用户=>从Redis中获取
        User userCache = (User) redisUtil.getByKey(token);
        String personRealName = GeneralConstant.COMMON_PERSON;
        if (userCache != null && !StringUtils.isEmpty(userCache.getUserRealName())) {
            personRealName = userCache.getUserRealName();
        }
        return personRealName;
    }
}
