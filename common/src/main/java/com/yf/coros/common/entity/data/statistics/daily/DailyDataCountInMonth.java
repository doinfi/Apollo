package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;

/**
 * 日常月统计数据
 *
 * @author Infi
 */
@Data
public class DailyDataCountInMonth implements Serializable {
    private static final long serialVersionUID = 6060353757860552872L;
    private Integer happenMonth;
    private Integer avgCalorie;
    private Integer avgMotionTime;
    private Integer avgStep;
    private HeartRateDataCount heartRateData;
    private SleepCount sleepData;
    private Float maxVo;
    private Integer minRhr;
    private Integer avgLactateThresholdHeartrate;
    private Float avgLactateThresholdPace;
}
