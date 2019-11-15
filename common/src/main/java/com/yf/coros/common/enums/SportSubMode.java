/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.enums;

/**
 * 标签类型:1:室内，2:室外
 *
 * @author Infi
 */
public interface SportSubMode {

    /**
     * 室外
     * 或者是组合运动：铁三
     * 雪场滑雪：双板滑雪
     */
    int OUT_DOOR = 1;

    /**
     * 室内
     * 或者是组合运动
     * 雪场滑雪：单板滑雪
     */
    int IN_DOOR = 2;

    /**
     * 组合运动：登山滑雪
     */
    int COMBINATION_SKI = 3;

    /**
     * 头盔骑行
     */
    int HELMET_CYCLE = 90;
}
