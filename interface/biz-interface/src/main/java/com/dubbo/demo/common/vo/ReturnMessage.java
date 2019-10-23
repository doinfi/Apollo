package com.dubbo.demo.common.vo;

/**
 * Created by dinghui on 17/3/7.
 */
public class ReturnMessage {

    //****通用*******

    //成功/正确
    public final static ResponseVO RETURN_OK = new ResponseVO("0000", "ok");
    //错误消息
    public final static ResponseVO SYSTEM_ERROR = new ResponseVO("1001", "system error");


    //****同步相关*******

    //找不到文件
    public static final ResponseVO FILE_NOT_FOUND = new ResponseVO("1058", "file not found");
    //标签原始数据为空
    public static final ResponseVO SOURCE_DATA_NULL = new ResponseVO("1001", " w4ParseResult is null");


    //****天气相关*******

    //找不到该城市
    public static final ResponseVO WEATHER_CITY_NOT_FOUND = new ResponseVO("1061", "weather city not found");
    //找不到该城市的天气
    public static final ResponseVO WEATHER_NOT_FOUND = new ResponseVO("1062", "weather not found");

    //没有新的24小时天气
    public static final ResponseVO WEATHER_NOT_NEW = new ResponseVO("1063", "not exists new weather");


    //****第三方接口相关*******
    public static final ResponseVO CALLHARASS_ERROR = new ResponseVO("1071", "call harass build error");


}
