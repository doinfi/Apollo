package com.yf.coros.common.enums;

/**
 * 模块权限，1：固件功能，2：消息功能，4：设备功能，如果一个用户同时拥有固件功能和消息功能，mode_role=1+2=3
 *
 * @author Infi
 */
public interface ModuleRole {
    /**
     * 固件功能
     */
    Integer OTA_ROLE = 1;
    /**
     * 消息功能
     */
    Integer MESSAGE_ROLE = 2;
    /**
     * 设备功能
     */
    Integer DEVICE_ROLE = 4;
    /**
     * 系统维护管理功能
     */
    Integer MAINTENANCE_ROLE = 8;
    /**
     * 数据采集开关功能
     */
    Integer COLLECT_PROFILE_ROLE = 16;
    /**
     * APP版本维护
     */
    Integer APP_VERSION_ROLE = 32;
    /**
     * 推送产品到
     */
    Integer PUSH_PRODUCT = 64;
    /**
     * 客服&售后人员权限，涉及到手表回厂返修后重新销售
     */
    Integer AFTER_SALES = 128;
    /**
     * 数据采集、数据分析人员权限
     */
    Integer DATA_ANALYSIS = 256;
    /**
     * 用户帐号信息管理，只有客服主管才会开通此权限
     * 目前用途
     * 1. 查询用户密码重置链接
     * 2. 删除用户帐号
     */
    Integer ACCOUNT_MANAGEMENT = 512;
}
