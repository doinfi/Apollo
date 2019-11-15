/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.enums;

/**
 * 标签类型:1:用户模块，2:标签,运动数据模块
 * 最大支持127个功能模块
 *
 * @author Infi
 */
public interface ServiceModeType {
    /**
     * 用户服务模块
     */
    int USER = 1;
    /**
     * 标签,运动数据服务模块,日常服务模块
     */
    int LABEL = 2;
    /**
     * 自行车导航团队服务模块
     */
    int BIKE_TEAM = 3;
    /**
     * 海拔任务（原先用于：自行车路书服务模块）
     */
    int ELEVATION_TASK = 4;
    /**
     * 头盔项目sos短信信息
     */
    int SOS_RECORD = 5;
    /**
     * 设备信息信息
     */
    int DEVICE = 6;
    /**
     * 固件信息、APP版本信息主键
     */
    int FIRMWARE_AND_APP_VERSION = 7;
    /**
     * 问题反馈信息
     */
    int FEEDBACK = 8;
    /**
     * 验证码模块
     */
    int CHECK_CODE = 9;
    /**
     * 消息
     */
    int MESSAGE = 10;

    /**
     * 日常
     */
    int DAILY = 11;
    /**
     * 使用频率不高的几个功能，公用一个主键生成模块 ID
     * 后台配置：
     * 1. 服务器维护通知
     * 2. 数据采集开关
     */
    int PROFILE = 12;
    /**
     * GoMore initWorkout信息
     */
    int GOMORE_WORKOUT_INITIAL = 13;
    /**
     * 轨迹功能
     */
    int TRACK = 14;
    /**
     * 告警
     */
    int ALARM = 15;
    /**
     * 越野滑雪
     */
    int SKI = 19;
}
