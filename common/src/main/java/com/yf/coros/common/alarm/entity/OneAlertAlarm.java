package com.yf.coros.common.alarm.entity;

import lombok.Data;

import java.util.Map;

/**
 * oneAlert 告警模板
 * @author lihuaijin
 */
@Data
public class OneAlertAlarm {
    private String app;
    private String eventType;
    private String eventId;
    private String alarmName;
    private String alarmContent;
    private String entityName;
    private String entityId;
    private Integer priority;
    private String host;
    private String service;
    private Map<String, Object> details;
    private Map<String, Object> contexts;
    private String metric;
    private String value;
}
