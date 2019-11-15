package com.yf.coros.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangyueming
 */
@Slf4j
public class IpUtils {

    public static void main(String[] args) {
    }

    public static long ipToLong(String ipAddress) {
        long result = 0;
        try {
            if (StringUtils.isBlank(ipAddress)) {
                return 0;
            }
            String[] ipAddressInArray = ipAddress.split("\\.");
            for (int i = 3; i >= 0; i--) {
                long ip = Long.parseLong(StringUtils.trim(ipAddressInArray[3 - i]));
                result |= ip << (i * 8);
            }
        } catch (Exception ex) {
            log.error("ip地址转换错误：" + ex.toString());
        } finally {
            return result;
        }
    }

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ipIsBlankOrUnknown(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ipIsBlankOrUnknown(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipIsBlankOrUnknown(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipIsBlankOrUnknown(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipIsBlankOrUnknown(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isNotBlank(ip) && StringUtils.contains(ip, ",")) {
            ip = StringUtils.substringAfterLast(ip, ",");
        }
        return StringUtils.equals(ip, "0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    private static boolean ipIsBlankOrUnknown(String ip) {
        return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
    }


}