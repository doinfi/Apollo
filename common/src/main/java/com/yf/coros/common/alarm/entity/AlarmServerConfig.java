package com.yf.coros.common.alarm.entity;

import lombok.Data;

import java.util.Map;

/**
 * 告警服务配置信息实体
 * @author lihuaijin
 */
@Data
public class AlarmServerConfig {

    private String serverName;

    private String url;

    private Integer authType;

    private Map<String, Object> authParam;

    private Boolean active;
}
