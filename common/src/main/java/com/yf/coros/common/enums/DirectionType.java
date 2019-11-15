/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.yf.coros.common.enums;

/**
 * 查询方向：
 * direction：1:向小余当月方向查询，2：向大于当月方向查询。默认向小余当月的方向查询
 *
 * @author Infi
 */
public interface DirectionType {

    /**
     * 1:向小余当月方向查询
     */
    int DOWN = 1;
    /**
     * 2：向大于当月方向查询
     */
    int UP = 2;
}
