package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 睡眠数据
 *
 * @author Infi
 */
@Data
public class SleepCount implements Serializable {
    private static final long serialVersionUID = 37267337066984675L;
    private Integer deepTime;
    private Integer lightTime;
    private Integer wakeTime;
    private Integer minHeartRate;
    private Integer maxHeartRate;
    private Integer avgHeartRate;
    private Integer totalSleepTime;
    private Integer avgDeepTime;
    private Integer avgLightTime;
    private Integer avgWakeTime;
    private Integer avgSleepTime;
}
