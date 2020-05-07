package com.itcast.education.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zheng.zhang
 * @description Cookie工具类
 * @date 2020/5/7 19:17
 */
public class CookieUtil {
    private static Logger LOG = LoggerFactory.getLogger(CookieUtil.class);

    /**
     * 设置 Cookie的值 不设置生效时间默认浏览器关闭即失效,不编码
     * @param request 请求
     * @param response 响应
     * @param key 键
     * @param value 值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value) {
        setCookie(request, response, key, value, -1);
    }

    /**
     * 设置Cookie的值,并且设置过期时间
     * @param request 请求
     * @param response 响应
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value, int expireTime) {
        try {
            Cookie cookie = new Cookie(key, value);
            if (expireTime > 0) {
                cookie.setMaxAge(expireTime);
            }
            if (null != request) {  // 设置域名的cookie
                String domainName = getDomainName(request);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            LOG.error("设置cookie出错", e);
        }
    }

    /**
     * 获取 Cookie的域名
     */
    @SuppressWarnings("all")
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;
        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {	// www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {	// xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }
        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }
}
