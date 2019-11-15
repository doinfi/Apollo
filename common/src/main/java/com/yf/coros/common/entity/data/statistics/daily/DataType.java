package com.yf.coros.common.entity.data.statistics.daily;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据类型
 *
 * @author Infi
 */
@Data
public class DataType implements Serializable {
    private static final long serialVersionUID = -604788100236478361L;
    private Integer calorie;
    private Integer motionTime;
    private Integer step;
    private Integer heartRate;
    private Integer sleep;
    private Integer rhr;
    private Integer trainingLoad;
    private Integer lactateThresholdHeartrate;
    private Integer lactateThresholdPace;
    private Integer staminaLevel;
    private Integer maxVo;
    private Integer calendar;
    private Integer sleepDetail;
    private Integer calorieDetail;
    private Integer motionTimeDetail;
    private Integer stepDetail;
    private Integer heartRateDetail;
}
