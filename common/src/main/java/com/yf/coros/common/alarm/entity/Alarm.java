package com.yf.coros.common.alarm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * coros 告警标准实体（入库模板）
 * @author lihuaijin
 */
@Data
@NoArgsConstructor
public class Alarm implements Serializable {

    private static final long serialVersionUID = 1867913447804044228L;

    /**
     * 基于告警定义构造基本模板
     * @param alarmDefinition 告警定义
     */
    public Alarm(AlarmDefinition alarmDefinition) {
        super();
        this.alarmId = alarmDefinition.getAlarmId();
        this.name = alarmDefinition.getName();
        this.description = alarmDefinition.getDescription();
        this.metric = alarmDefinition.getMetric();
        this.metricDesc = alarmDefinition.getMetricDesc();
        this.recoverType = alarmDefinition.getRecoverType();
    }

    /**
     * 告警流水ID，一条告警生命周期内的唯一标志
     */
    private Long id;

    /**
     * 告警ID，标识不同的告警，例如： dubbo进程告警/数据库告警/博晶授权情况告警等
     */
    private String alarmId;

    /**
     * 告警名称，与告警ID配套，冗余
     */
    private String name;

    /**
     * 告警描述，与告警ID配套，冗余
     */
    private String description;

    /**
     * 告警对象，产生告警的对象，例如：数据库1、服务器1
     */
    private String target;

    /**
     * 告警子对象，产生告警的对象，例如：服务进程a/b/c
     */
    private String subTarget;

    /**
     * 告警指标，触发告警的指标项，例如：a进程的x资源，与告警ID配套，冗余
     */
    private String metric;

    /**
     * 告警指标描述，描述该指标正常/异常区间，以便快速定位分析，与告警ID配套，冗余
     */
    private String metricDesc;

    /**
     * 告警指标值
     */
    private String value;

    /**
     * 告警指标前一次的值，当告警发生变化时，如果值发生变化，本字段会记录前一次的值
     */
    private String lastValue;

    /**
     * 告警类型，0：告警触发，1：告警恢复，2：告警被强制恢复（手工行为时使用）
     */
    private Integer status;

    /**
     * 告警恢复类型，0：告警支持系统自动恢复，1：告警只能人工干预恢复，与告警ID配套，冗余
     */
    private Integer recoverType;

    /**
     * 告警级别，1：提醒，2：警告，3：严重
     */
    private Integer priority;

    /**
     * 告警前一次的级别
     */
    private Integer lastPriority;

    /**
     * 告警发生时间戳
     */
    private Long happenTime;

    /**
     * 告警恢复时间戳
     */
    private Long recoverTime;

    /**
     * 告警更新时间戳
     */
    private Long updateTime;
}
