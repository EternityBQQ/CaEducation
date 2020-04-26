package com.itcast.education.utils;

import java.util.List;

/**
 * 对象校验工具类
 * @author zheng.zhang
 * @date 2020/04/22
 */
public class ValidateUtil {

    /**
     * 校验对象是否为空
     * @param validateList
     * @return
     */
    public static boolean listIsEmpty(List<?> validateList) {
        return validateList == null || validateList.isEmpty();
    }
}
