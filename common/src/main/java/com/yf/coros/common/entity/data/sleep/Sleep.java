package com.yf.coros.common.entity.data.sleep;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 睡眠数据
 *
 * @author Infi
 */
@Data
public class Sleep implements Serializable {
    private static final long serialVersionUID = -5188150739948281903L;
    private Long id;
    private Long userId;
    private Integer happenDay;
    private Integer happenMonth;
    private Integer startTimestamp;
    private Integer endTimestamp;
    private Integer startTimezone;
    private Integer endTimezone;
    private Integer deepTime;
    private Integer lightTime;
    private Integer wakeTime;
    private Integer minHeartRate;
    private Integer maxHeartRate;
    private Integer avgHeartRate;
    private byte[] sleepData;
    private Date createTime;
    private Long localId;
    private Date updateTime;
    private Integer totalSleepTime;
    private Integer avgDeepTime;
    private Integer avgLightTime;
    private Integer avgWakeTime;
    private Integer avgSleepTime;
}
