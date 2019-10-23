/*
 * Copyright (c) 2014-2018 yftech Co.Ltd. All rights reserved.
 */

package com.dubbo.demo.core.common;

/**
 * EnumFileKey:用户类型
 *
 * @author dinghui 2016-06-28
 */
public enum EnumUserType {
    /**
     * 管理员
     */
    ALL("所有"),
    /**
     * 管理员
     */
    ADMIN("管理员"),
    /**
     * 客服
     */
    CS("客服"),
    /**
     * app／手环用户
     */
    APP("手环用户");

    // 成员变量
    private String name;

    // 构造方法
    private EnumUserType(String name) {
        this.name = name;
    }

    // 覆盖方法
    public String getName() {
        return this.name;
    }
}
