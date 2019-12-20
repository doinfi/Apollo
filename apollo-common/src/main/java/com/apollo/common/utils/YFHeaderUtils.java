package com.apollo.common.utils;

import com.alibaba.fastjson.JSON;
import com.apollo.common.entity.YFHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求头部信息
 *
 * @author Infi
 */
@Slf4j
public class YFHeaderUtils {

    /**
     * 获取yfheader
     *
     * @param request request对象
     * @return yfheader
     */
    public static YFHeader getYFHeader(HttpServletRequest request) {
        String yfHeaderJson = request.getHeader("YFHeader");
        if (StringUtils.isBlank(yfHeaderJson)) {
            return new YFHeader();
        }
        return getJson2YFHeader(yfHeaderJson);
    }

    /**
     * 获取yfheader
     *
     * @param yfHeaderJson yfheader json字符串
     * @return yfheader
     */
    public static YFHeader getYFHeader(String yfHeaderJson) {
        if (StringUtils.isBlank(yfHeaderJson)) {
            return new YFHeader();
        }
        return getJson2YFHeader(yfHeaderJson);
    }

    /**
     * 把json转换成yfheader对象
     *
     * @param yfHeaderJson yfheader json字符串
     * @return yfheader对象
     */
    private static YFHeader getJson2YFHeader(String yfHeaderJson) {
        try {
            YFHeader yfHeader = JSON.parseObject(yfHeaderJson, YFHeader.class);
            // 为空就返回new对象，方便后面调试
            if (yfHeader == null) {
                return new YFHeader();
            }
            return yfHeader;
        } catch (Exception e) {
            log.error("yfheader解析出错，yfheaderString" + yfHeaderJson, e);
        }
        return new YFHeader();
    }

    /**
     * 获取yfheader
     *
     * @param request request对象
     * @return 用户所在时区
     */
    public static Integer getYFHeaderTimezone(HttpServletRequest request) {
        YFHeader yfHeader = getYFHeader(request);
        if (yfHeader == null) {
            return null;
        }
        return yfHeader.getTimezone();
    }

    /**
     * 获取yfheader
     *
     * @param request          request对象
     * @param userInfoTimezone 用户信息中的时区
     * @return 用户所在时区
     */
    public static Integer getYFHeaderTimezone(HttpServletRequest request, Integer userInfoTimezone) {
        Integer timezone = getYFHeaderTimezone(request);
        if (timezone != null) {
            return timezone;
        }
        return userInfoTimezone;
    }

    /**
     * 获得请求头部信息的语言数据
     *
     * @param request request
     * @return 语言
     */
    public static String getHeaderLanguage(HttpServletRequest request) {
        YFHeader yfHeader = getYFHeader(request);
        if (yfHeader == null || StringUtils.isBlank(yfHeader.getLanguage())) {
            return StringUtils.EMPTY;
        }
        return yfHeader.getLanguage();
    }

    /**
     * 获得请求头部信息的语言数据
     *
     * @param yfHeaderJson yfheader json 字符串
     * @return 语言
     */
    public static String getHeaderLanguage2String(String yfHeaderJson) {
        YFHeader yfHeader = getYFHeader(yfHeaderJson);
        if (yfHeader == null || StringUtils.isBlank(yfHeader.getLanguage())) {
            return StringUtils.EMPTY;
        }
        return yfHeader.getLanguage();

    }

}
