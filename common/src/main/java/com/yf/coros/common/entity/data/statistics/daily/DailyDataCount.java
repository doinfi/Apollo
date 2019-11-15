package com.yf.coros.common.entity.data.statistics.daily;

import com.yf.coros.common.entity.data.sleep.Sleep;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 日常统计数据实体对象
 *
 * @author Infi
 * @date 17/6/1
 */
@Data
public class DailyDataCount implements Serializable {
    private static final long serialVersionUID = -5293854561441529479L;
    private Integer happenDay;
    private Integer calorie;
    private Integer motionTime;
    private Integer step;
    private Integer maxHeartRate;
    private Integer minHeartRate;
    private Integer avgHeartRate;
    private Integer rhr;
    private Integer happenMonth;
    private Integer avgCalorie;
    private Integer avgMotionTime;
    private Integer avgStep;
    private String calorieLine;
    private String motionTimeLine;
    private String stepLine;
    private String heartRateLine;
    private Integer targetCalorie;
    private Integer targetMotionTime;
    private Integer lactateThresholdHeartrate;
    private Float lactateThresholdPace;
    private Float staminaLevel;
    private Float maxVo;
    private Integer minRhr;
    private Integer avgLactateThresholdHeartrate;
    private Float avgLactateThresholdPace;
    private CalendarDataCount calendarData;
}
