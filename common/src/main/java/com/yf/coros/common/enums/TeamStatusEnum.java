/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.enums;

/**
 * 标签类型:1:用户模块，2:标签,运动数据模块
 *
 * @author Infi
 */
public interface TeamStatusEnum {

    /**
     * 团队默认状态,未开始,未结束
     */
    Integer TEAM_DEFAULT = 0;
    /**
     * 团队状态,开始骑行
     */
    Integer TEAM_START = 1;
    /**
     * 团队状态,结束骑行
     */
    Integer TEAM_END = 2;

}
