package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 日常天统计数据
 *
 * @author Infi
 */
@Data
public class DailyDataCountInDay implements Serializable {
    private static final long serialVersionUID = 2535000746274557101L;
    private Integer happenDay;
    private Integer calorie;
    private Integer motionTime;
    private Integer step;
    private HeartRateDataCount heartRateData;
    private SleepCount sleepData;
    private Integer rhr;
    private Float trainingLoad;
    private Integer lactateThresholdHeartrate;
    private Float lactateThresholdPace;
    private Float staminaLevel;
    private Float maxVo;
    private CalendarDataCount calendarData;
    private String calorieLine;
    private String motionTimeLine;
    private String stepLine;
    private String heartRateLine;
    private List<String> sleepList;
}
