package com.yf.coros.common.config;

import lombok.Data;

/**
 * 解析库相关配置
 * @author dinghui
 */
@Data
public class W4Properties {
    /**
     * 解析库配置前缀
     */
    public static final String PREFIX = "coros";

    /**
     * 解析库是否打印日志
     */
    private Boolean w4ParseDebug;
    /**
     * 解析库打印日志的结束时间点
     */
    private String w4ParseEndTime;

}
