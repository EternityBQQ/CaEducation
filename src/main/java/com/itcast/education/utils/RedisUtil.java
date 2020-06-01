package com.itcast.education.utils;

import com.itcast.education.model.base.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zheng.zhang
 * @description Redis工具类
 * @date 2020/5/7 10:42
 */
@Component
@SuppressWarnings("all")
public class RedisUtil {
    private Logger LOG = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定过期时间
     * @param key 键
     * @param time 时间(秒)
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            LOG.error("指定Redis过期时间出错", e);
            return false;
        }
    }

    /**
     * 根据Key获取过期时间
     * @param key 键
     * @return 过期时间
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 根据Key值判断是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            LOG.error("获取Key值出错", e);
            return false;
        }
    }

    /**
     * 根据Key值清除指定缓存
     * @param key
     */
    public void deleteCache(String...key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 根据Key获取值
     * @param key
     * @return
     */
    public Object getByKey(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            LOG.error("设置缓存出错", e);
            return false;
        }
    }

    /**
     * 设置键值并设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            LOG.error("设置缓存出错", e);
            return false;
        }
    }

    /**
     * 根据键值更新缓存
     * @param key 缓存名称
     * @param model 需要添加的实例
     * @return
     */
    public boolean updateByKey(String key, Object model) {
        boolean result = false;
        try {
            Object object = getByKey(key);
            if (object instanceof List) {
                List<Object> list = (List<Object>) object;
                list.add(model);
                result = set(key, list);
            }
        } catch (Exception e) {
            LOG.error("更新缓存出错", e.getLocalizedMessage());
        }
        return result;
    }
}
