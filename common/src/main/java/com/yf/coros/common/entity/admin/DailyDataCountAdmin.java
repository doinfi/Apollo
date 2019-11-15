package com.yf.coros.common.entity.admin;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理后台日常数据
 *
 * @author Infi
 */
@Data
public class DailyDataCountAdmin implements Serializable {
    private static final long serialVersionUID = 174003653004330997L;
    private Long userId;
    private String userIdStr;
    private Integer happenDate;
    private String happenDay;
    private Integer restingHeartRate;
    private Integer maxHeartRate;
    private Integer sleepSumTime;
    private Date sleepStart;
    private Date sleepEnd;
    private Integer deepTime;
    private Integer lightTime;
    private Integer wakeTime;
    private Integer sleepMinHeartRate;
    private Integer sleepMaxHeartRate;
    private Integer sleepAvgHeartRate;
    private Integer maxVo;
    private Date createTime;
    private Date updateTime;
    private String downloadUrl;
}
