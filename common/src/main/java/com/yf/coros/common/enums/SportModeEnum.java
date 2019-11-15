/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.enums;

/**
 * 标签类型:1:摘下，2:睡眠，3:步行，4:跑步，5:低运动量，6:运动，7:快走,8:跑步训练,9:骑行,10:游泳 其中,8:跑步训练,9:骑行。这三种运动类型有动态心率
 *
 * @author Infi
 */
public interface SportModeEnum {
    /**
     * 跑步训练
     */
    int RUNTRAIN = 8;
    /**
     * 骑行
     */
    int CYCLE = 9;
    /**
     * 游泳
     */
    int SWIM = 10;
    /**
     * 铁人三项
     */
    int TRIATHLON = 13;
    /**
     * 登山
     */
    int CLIMB = 14;
    /**
     * 越野跑
     */
    int TRAIL_RUN = 15;
    /**
     * 徒步
     */
    int HIKE = 16;
    /**
     * 有氧运动
     */
    int AEROBICS = 18;
    /**
     * 越野滑雪
     */
    int SKI = 19;
    /**
     * 操场跑圈模式
     */
    int TRACK_RUN = 20;
    /**
     * 雪场滑雪
     * subMode=1表示双板滑雪
     * subMode=2表示单板滑雪
     */
    int ALPINE_SKI = 21;
    /**
     * 战斗机模式
     */
    int FIGHTER = 22;
    /**
     * 力量训练
     */
    int STRENGTH = 23;
}
