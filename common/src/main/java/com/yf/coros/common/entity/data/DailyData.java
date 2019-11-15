package com.yf.coros.common.entity.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 日常数据实体对象
 *
 * @author Infi
 * @date 17/6/1
 */
@Data
public class DailyData implements Serializable {

    private static final long serialVersionUID = -6787889813447404044L;
    private Long userId;
    private Integer happenDate;
    private Integer totalCalorie = 0;
    private Integer totalDistance = 0;
    private Integer totalMotionTime = 0;
    private Integer totalStep = 0;
    private Integer targetMotionTime = 0;
    private Integer targetCalorie = 0;
    private String calorieLine;
    private String motionTimeLine;
    private String stepLine;
    private Date createTime;
    private String heartRateLine;
    private Integer calorieStandardRate;
    private Integer motionTimeStandardRate;
    private Integer happenYear;
    private Integer happenMonth;
    private Integer happenWeekDay;
    private Integer targetCalorieInWeek;
    private byte[] heartRateData;
    private Map<Integer, HeartRateStatisticsData> heartRateDataMap = new HashMap<>();
    private Map<Integer, Integer> calorieMap = new HashMap<>();
    private Map<Integer, Integer> motionTimeMap = new HashMap<>();
    private Map<Integer, Integer> stepMap = new HashMap<>();
    private Long timestamp;
    private Integer timezone;
    private Integer dataStatus;
    private String heartRateDetail;
    private List<HeartRateData> heartRateDataList = new ArrayList<>();
    private Integer reserveHeartRate;
    private Integer minHeartRate;
    private Integer maxHeartRate;
    private Integer avgHeartRate;
    private Integer floors = 0;
    private Integer restingHeartRate1;
    private Integer restingHeartRate2;
    private Integer restingHeartRate3;

    /**
     * 预留附加字段，对应W4DataType.YFSportDataTypeJniFitnessOtherData
     */
    private byte[] reserveData;

    private List<LabelData> sportDataList;

    /**
     * 备注：数据来源不是日常数据上传<br/>
     * 用户查询最近7天时把除当天之外其他天的训练负荷数据更新到日常表
     */
    private Double trainingLoad;

    /**
     * 备注：数据来源不是日常数据上传<br/>
     * 乳酸阈配速，上传完运动数据的时候更新到日常表
     */
    private Integer lactateThresholdPace;

    /**
     * 备注：数据来源不是日常数据上传<br/>
     * 乳酸阈心率，上传完运动数据的时候更新到日常表
     */
    private Integer lactateThresholdHeartrate;

    /**
     * 备注：数据来源不是日常数据上传<br/>
     * 体力等级，上传完运动数据的时候更新到日常表，额外判断一把该运动数据是当天的，不是当天的就不更新
     */
    private Double staminaLevel;

    /**
     * 备注：数据来源不是日常数据上传<br/>
     * 体力等级变化，上传完运动数据的时候更新到日常表，额外判断一把该运动数据是当天的，不是当天的就不更新
     */
    private Double staminaLevelChange;

    private List<String> sleepDataList;
}
