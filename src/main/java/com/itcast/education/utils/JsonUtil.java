package com.itcast.education.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.education.config.GeneralConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON工具类
 *
 * @author zheng.zhang
 */
public final class JsonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 对象转换为Json字符串
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        String result = GeneralConstant.EMPTY;
        try {
            if (data == null) {
                return result;
            }
            result = MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            LOG.error("对象转json出错：[JsonUtil] [objectToJson]", e);
        }
        return result;
    }

    /**
     * 字符串转化为Json数组
     *
     * @param json
     * @return 对象集合
     */
    public static Object strToPojo(String json, Class<?> clazz) {
        Object result = null;
        try {
            if (clazz == null) {
                return null;
            }
            result = MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("对象转换出错: [JsonUtil] [strToPojo]", e);
        }
        return result;
    }
}
