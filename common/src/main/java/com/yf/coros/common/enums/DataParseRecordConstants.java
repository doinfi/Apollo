package com.yf.coros.common.enums;

/**
 * 解析库解析记录常量类
 * @author lihuaijin
 */
public class DataParseRecordConstants {

    /**
     * 状态：解析中
     */
    public static final int RUNNING = 0;

    /**
     * 状态：解析完成
     */
    public static final int FINISHED = 1;

    /**
     * 状态：解析疑似触发JVM崩溃，该条记录对应的用户已加入黑名单
     */
    public static final int BANNED = 99;

    /**
     * 类型：运动数据解析
     */
    public static final int SPORT_DATA_PARSE = 0;

    /**
     * 类型：日常数据解析
     */
    public static final int DAILY_DATA_PARSE = 1;

    /**
     * 类型：fit转运动解析
     */
    public static final int FIT_TO_SPORT_PARSE = 2;

    /**
     * 类型：运动转fit解析
     */
    public static final int SPORT_TO_FIT_PARSE = 3;

    /**
     * 类型：设备原始数据转sportData数据
     */
    public static final int DEVICE_TO_SPORT_PARSE = 4;

    /**
     * 类型：其他解析
     */
    public static final int OTHER_PARSE = 99;

}
