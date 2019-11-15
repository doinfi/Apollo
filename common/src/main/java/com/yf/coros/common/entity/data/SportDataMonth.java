package com.yf.coros.common.entity.data;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 月统计实体
 * by max.hu
 * Created by hudahua on 2018/5/16.
 */
@Data
public class SportDataMonth implements Serializable {

    private static final long serialVersionUID = 8019566905097435158L;
    private Long userId;
    private Integer happenYear;
    private Integer happenMonth;
    private Integer calorieRun;//室内跑步、室外跑步加铁三跑步卡路里（小卡）
    private Integer calorieRiding;//骑行加铁三骑行卡路里（小卡）
    private Integer calorieSwim;//室内游泳、室外游泳加铁三游泳卡路里
    private Integer distanceRun;//室内跑步、室外跑步加铁三跑步距离（米）
    private Integer distanceRiding;//骑行加铁三骑行距离（米）
    private Integer distanceSwim;//室内游泳、室外游泳加铁三游泳距离（米）
    private Long motionDurationRun;//跑步加铁三跑步运动时长（秒）
    private Long motionDurationRiding;//骑行加铁三骑行时长（秒）
    private Long motionDurationSwim;//游泳加铁三游泳时长（秒）
    private Integer stepRun;//室内跑步、室外跑步、铁三跑步步数
    private Date createTime;//createTime#创建时间，服务器utc时间

    private Integer indoorRunCount;//室内跑步运动次数
    private Integer indoorRunCalorie;//室内跑步卡路里(小卡)
    private Float indoorRunDistance;//室内跑步距离(米)
    private Long indoorRunDuration;//室内跑步时长(秒)

    private Integer outdoorRunCount;//室外跑步运动次数
    private Integer outdoorRunCalorie;//室外跑步卡路里(小卡)
    private Float outdoorRunDistance;//室外跑步距离(米)
    private Long outdoorRunDuration;//室外跑步时长(秒)

    private Integer indoorSwimCount;//室内游泳运动次数
    private Integer indoorSwimCalorie;//室内游泳卡路里(小卡)
    private Float indoorSwimDistance;//室内游泳距离(米)
    private Long indoorSwimDuration;//室内游泳时长(秒)

    private Integer outdoorSwimCount;//室外游泳运动次数
    private Integer outdoorSwimCalorie;//室外游泳卡路里(小卡)
    private Float outdoorSwimDistance;//室外游泳距离(米)
    private Long outdoorSwimDuration;//室外游泳时长(秒)

    private Integer indoorCycleCount;//骑行运动次数
    private Integer indoorCycleCalorie;//骑行卡路里(小卡)
    private Float indoorCycleDistance;//骑行距离(米)
    private Long indoorCycleDuration;//骑行时长(秒)

    private Integer outdoorCycleCount;//骑行运动次数
    private Integer outdoorCycleCalorie;//骑行卡路里(小卡)
    private Float outdoorCycleDistance;//骑行距离(米)
    private Long outdoorCycleDuration;//骑行时长(秒)


    private Integer triathlonCount;//铁三运动次数
    private Integer triathlonCalorie;//铁三卡路里(小卡)
    private Long triathlonDuration;//铁三时长(秒)
    private Float triathlonDistance;

}
