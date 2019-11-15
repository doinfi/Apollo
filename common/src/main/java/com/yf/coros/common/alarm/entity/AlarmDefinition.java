package com.yf.coros.common.alarm.entity;

import lombok.Data;


/**
 * 告警定义信息，定义告警类别和监控触发机制
 * @author lihuaijin
 */
@Data
public class AlarmDefinition {

    /**
     * 告警ID，标识不同的告警
     */
    private String alarmId;

    /**
     * 告警名称
     */
    private String name;

    /**
     * 告警描述
     */
    private String description;

    /**
     * 告警指标，触发告警的指标项
     */
    private String metric;

    /**
     * 告警指标描述，描述该指标正常/异常区间，以便快速定位分析
     */
    private String metricDesc;

    /**
     * 告警恢复类型，0：告警支持系统自动恢复，1：告警只能人工干预恢复
     */
    private Integer recoverType;

    /**
     * 告警开关，0：实时监控本告警，1：忽略监控本告警
     */
    private Integer activeSwitch;

    /**
     * 告警执行器，标识该类告警由哪个执行器来执行监控
     */
    private String alarmExecutor;

    /**
     * 告警是否使用通用的触发机制，0：使用，1：不使用，由执行器来决定触发机制
     */
    private Integer triggerType;

    /**
     * 告警规则，默认情况下，恢复规则与之相反，例如规则为0（超过阈值时），只要指标值低于恢复值的指标，则告警恢复：
     * 0、超过阈值时告警触发，1、低于阈值时告警触发
     */
    private Integer triggerRule;

    /**
     * 第一级别触发阈值，useCommonTrigger为true时才有效
     */
    private Integer commonTriggerLevel1;

    /**
     * 第二级别触发阈值，useCommonTrigger为true时才有效
     */
    private Integer commonTriggerLevel2;

    /**
     * 第三级别触发阈值，useCommonTrigger为true时才有效
     */
    private Integer commonTriggerLevel3;

    /**
     * 告警恢复触发阈值，useCommonTrigger为true时才有效
     */
    private Integer commonRecoverLevel;

    /**
     * 第一级别触发的告警级别，告警级别，0：仅记录日志，1：提醒，2：警告，3：严重，useCommonTrigger为true时才有效
     */
    private Integer priorityLevel1;

    /**
     * 第二级别触发的告警级别，告警级别，0：仅记录日志，1：提醒，2：警告，3：严重，useCommonTrigger为true时才有效
     */
    private Integer priorityLevel2;

    /**
     * 第三级别触发的告警级别，告警级别，0：仅记录日志，1：提醒，2：警告，3：严重，useCommonTrigger为true时才有效
     */
    private Integer priorityLevel3;

    /**
     * 自定义触发机制配置，可以写入json，由专用执行器来处理详细逻辑
     */
    private String customTriggerConfig;
}
