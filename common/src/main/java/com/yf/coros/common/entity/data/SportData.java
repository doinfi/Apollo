package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 运动数据对象，对应t_sport_data表
 *
 * @author Infi
 * @date 17/5/9
 */
@Data
public class SportData implements Serializable {

    private static final long serialVersionUID = -7261514473748405800L;
    private Long labelId;
    private Long userId;
    private Integer happenDay;
    private Integer happenYear;
    private Integer happenWeekDay;
    private String deviceId;
    private Long startTime;
    private Long endTime;
    private Integer startTimezone;
    private Integer endTimezone;
    private Integer calorie = 0;
    private Double distance = 0.0;
    private byte[] sportData;
    private byte[] sportDataModify;
    private Date createTime;
    private Integer happenMonth;
    private Long duration = 0L;
    private Integer laps = 0;
    private Integer step = 0;
    private Integer mode;
    private Integer subMode;
    private Integer avgCadence = 0;
    private Integer avgSpeed = 0;
    private Long runDuration = 0L;
    private Double runDistance = 0.0;
    private Integer runStep = 0;
    private Integer runCalorie = 0;
    private Long cycleDuration = 0L;
    private Double cycleDistance = 0.0;
    private Integer cycleCalorie = 0;
    private Long swimDuration = 0L;
    private Double swimDistance = 0.0;
    private Integer swimCalorie = 0;
    private Integer maxVo;
    private Integer currentMaxVo;
    private Long maxVoTimestamp;
    private Double kValue;
    private Double bValue;
    private Integer kbValidity;
    private byte[] trainDataBytes;
    private Double kbDistance;
    private Double oldStepDistance;
    private Integer strideLengthHigh;
    private Integer strideLengthLow;
    private Integer strideLengthFreestyle;
    private Integer strideLengthBreaststroket;
    private Integer strideLengthButterfly;
    private Integer strideLengthBackstroke;
    private Integer strideLengthMix;
    private Integer avgPace = 0;
    private Integer extraType;
    private Integer elevationCalibrated;
    private Integer totalElevation = 0;
    private Integer totalDecline = 0;
    private Integer count = 0;
    private Integer index;
    private String fitUrl;
    private Integer parentMode;
    private List<SportData> subSportDataList;
    private Integer avgHeartRate;
    private Date fitCreateTime;
    private String name;
}
