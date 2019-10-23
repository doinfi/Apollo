package com.dubbo.demo.core.utils;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 返回码和返回消息定义
 *
 * @author xuming 2014-12-01
 */
public class ReturnMessage {

    public final static String RETURN_OK = "{\"result\":\"0000\",\"message\": \"ok\" }";

    public final static String SYSTEM_ERROR = "{\"result\": \"1001\",\"message\": \"system error\"}";
    public final static String SOURCE_DATA_NULL = "{\"result\": \"1001\",\"message\": \"w4ParseResult is null\"}";
    public final static String SOURCE_EXPLAIN_ERR = "{\"result\": \"1001\",\"message\": \"explain source data error\"}";

    public final static String REQUEST_MORE_BY_MOBILE = "{\"result\": \"1002\",\"message\": \"the mobile or ip request more\"}";
    public final static String USER_NAME_IS_NULL = "{\"result\":\"1003\",\"message\": \"the account is null\" }";
    public final static String PASSWORD_IS_NULL = "{\"result\":\"1004\",\"message\": \"password is null\" }";
    public final static String MOBILE_IS_NULL = "{\"result\":\"1005\",\"message\": \"the mobile is null\" }";

    public final static String LOGIN_ERROR = "{\"result\":\"1006\",\"message\": \"please input correct user name and password\" }";

    public final static String CLIENT_TYPE_IS_NULL = "{\"result\":\"1007\",\"message\": \"the client type is null\" }";
    public final static String USER_TYPE_IS_NULL = "{\"result\":\"1008\",\"message\": \"the user type is null\" }";
    public final static String LONGITUDE_IS_NULL = "{\"result\":\"1009\",\"message\": \"longitude is null\" }";
    public final static String LATITUDE_IS_NULL = "{\"result\":\"1010\",\"message\": \"latitude is null\" }";

    public final static String BAIDU_CHANNEL_ID_IS_NULL = "{\"result\":\"1011\",\"message\": \"BAIDU channel id is null\" }";
    public final static String APPLE_DEVICE_TOKEN_IS_NULL = "{\"result\":\"1012\",\"message\": \"Apple device token is null\" }";
    public final static String USER_NAME_IS_EXIST = "{\"result\":\"1013\",\"message\": \"the user name is exist\" }";
    public final static String CHECK_CODE_IS_NULL = "{\"result\":\"1014\",\"message\": \"the check code is nulll\" }";
    public final static String CHECK_CODE_IS_INVALID = "{\"result\":\"1015\",\"message\": \"the check code is invalid\" }";

    public final static String THIRD_OPEN_ID_IS_NULL = "{\"result\":\"1016\",\"message\": \"the  open id is null\" }";

    public final static String NICKNAME_IS_NULL = "{\"result\":\"1017\",\"message\": \"nickname is null\" }";

    public final static String SEX_IS_NULL = "{\"result\":\"1018\",\"message\": \"sex is null\" }";
    public final static String ACCESS_TOKEN_IS_INVALID = "{\"result\":\"1019\",\"message\": \"access token is invalid\" }";
    public final static String STATURE_IS_NULL = "{\"result\":\"1020\",\"message\": \"stature is null\" }";
    public final static String WEIGHT_IS_NULL = "{\"result\":\"1021\",\"message\": \"weight is null\" }";
    public final static String BIRTHDAY_IS_NULL = "{\"result\":\"1022\",\"message\": \"birthday is null\" }";
    public final static String USER_INFO_NO_EXIST = "{\"result\":\"1023\",\"message\": \"user info no exist\" }";
    public final static String PRIVACY_FLAG_IS_NULL = "{\"result\":\"1024\",\"message\": \"privacy flag is null\" }";
    public final static String OLD_PASSWORD_IS_NULL = "{\"result\":\"1025\",\"message\": \"old password is null\" }";
    public final static String NEW_PASSWORD_IS_NULL = "{\"result\":\"1026\",\"message\": \"new password is null\" }";
    public final static String OLD_PASSWORD_IS_WRONG = "{\"result\":\"1027\",\"message\": \"your old password is wrong\" }";

    public final static String CALORIE_VALUE_IS_NULL = "{\"result\":\"1028\",\"message\": \"calorie value is null\" }";
    public final static String USER_NO_EXIST = "{\"result\":\"1029\",\"message\": \"user no exist\" }";
    public final static String USER_ID_IS_NULL = "{\"result\":\"1030\",\"message\": \"user id is null\" }";

    public final static String PARAMETER_ERROR = "{\"result\": \"1031\",\"message\": \"input parameter is error\"}";
    public final static String ACCESS_TOKEN_IS_NULL = "{\"result\":\"1032\",\"message\": \"access token is null\" }";
    public final static String HEAD_PICTURE_IS_NULL = "{\"result\":\"1033\",\"message\": \"Head picture is null\" }";
    public final static String MID_IS_NULL = "{\"result\":\"1034\",\"message\": \"mid is null\" }";

    public final static String WATCH_NO_IS_NULL = "{\"result\":\"1035\",\"message\": \"watch No is null\" }";
    public final static String RELEASE_TYPE_IS_NULL = "{\"result\":\"1036\",\"message\": \"release type is null\" }";
    public final static String NO_RECORDS = "{\"result\":\"1037\",\"message\": \"no records\" }";
    public final static String PAGE_NUMBER_IS_NULL = "{\"result\":\"1038\",\"message\": \"page number is null\" }";
    public final static String ORDER_FLAG_IS_NULL = "{\"result\":\"1039\",\"message\": \"order flag is null\" }";
    public final static String IDS_IS_NULL = "{\"result\":\"1040\",\"message\": \"ID string is null\" }";
    public final static String WEEK_FLAG_IS_NULL = "{\"result\":\"1041\",\"message\": \"week flag is null\" }";
    public final static String UP_ACCESS_COUNT = "{\"result\":\"1042\",\"message\": \"you up to access times\" }";

    public final static String EMAIL_IS_NULL = "{\"result\":\"1043\",\"message\": \"email is null\" }";
    public final static String EMAIL_IS_INVALID = "{\"result\":\"1043\",\"message\": \"email is invalid\" }";
    public final static String VALID_CODE_IS_INVALID = "{\"result\":\"1044\",\"message\": \"you post valid code is invalid\" }";
    public final static String APPKEY_IS_INVALID = "{\"result\":\"1045\",\"message\": \"App key is invalid\" }";

    public final static String PARAMETER_DISTURBSTATUS_IS_NULL = "{\"result\":\"1046\",\"message\": \"parameter disturb status  is null\" }";
    public final static String BEGINTIME_IS_NULL = "{\"result\":\"1047\",\"message\": \"parameter begintime  is null\" }";
    public final static String ENDTIME_IS_NULL = "{\"result\":\"1048\",\"message\": \"parameter endtime  is null\" }";
    public final static String SWITCH_NAME_IS_NULL = "{\"result\":\"1049\",\"message\": \"parameter switch name is null\" }";
    public final static String MAC_IS_INVALID = "{\"result\":\"1050\",\"message\": \"mac is invalid\" }";
    public final static String SWITCH_STATUS_IS_NULL = "{\"result\":\"1051\",\"message\": \"parameter switch status is null\" }";
    public final static String PARAMETER_APP_LIST_IS_NULL = "{\"result\":\"1052\",\"message\": \"parameter app names is null\" }";
    public final static String PARAMETER_MAC_LIST_IS_NULL = "{\"result\":\"1053\",\"message\": \"parameter mac names is null\" }";
    public final static String DEVICE_ID_LIST_IS_NULL = "{\"result\":\"1054\",\"message\": \"parameter deviceId names is null\" }";
    public final static String PARAMETER_UUID_LIST_IS_NULL = "{\"result\":\"1055\",\"message\": \"parameter uuid names is null\" }";
    public final static String FIRMWARE_TYPE_LIST_IS_NULL = "{\"result\":\"1056\",\"message\": \"parameter firmwareType names is null\" }";
    public final static String WEATHER_NOT_FOUND = "{\"result\":\"1057\",\"message\": \"weather not found\" }";
    public final static String FILE_NOT_FOUND = "{\"result\":\"1058\",\"message\": \"file not found\" }";
    public final static String VERSION_ERROR = "{\"result\": \"1059\",\"message\": \"version error\"}";
    public static final String BUSY_BUSINESS_ERROR = "{\"result\": \"1060\",\"message\": \"business error\"}";
    public final static String WEATHER_CITY_NOT_FOUND = "{\"result\":\"1061\",\"message\": \"weather city not found\" }";

    //该用户已经绑定过
    public final static String USER_IS_BIND = "{\"result\":\"1069\",\"message\": \"user is bind\" }";
    //该用户已存在
    public final static String USER_IS_EXIST = "{\"result\":\"1070\",\"message\": \"user is exist\" }";

    public final static String paralost = "{\"result\": \"1111\",\"message\": \"parameter missing\"}";

    public final static String REGIST_DEVICE_ERROR = "{\"result\": \"2011\",\"message\": \"regist device error\"}";
    public final static String SERVER_MAINTENANCE = "{\"result\":\"2020\",\"message\": \"很抱歉,服务器维护中,请稍后再试。维护时间:14:20—16:00\"}";
    public final static String CONVERT_IS_NOT_DONE = "{\"result\":\"2030\",\"message\": \"convert data is not done !\" }";

    public final static String FIRMWARE_NO_EXIST = "{\"result\": \"2051\",\"message\": \"Not found\"}";


    Map<String, Object> map = new LinkedHashMap<>();

    public static String getReturnMessage(String result, String message) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("result", result);
        map.put("message", message);
        return JSON.toJSONString(map);
    }

    public static String getErrorMessage(String message) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("result", "1001");
        map.put("message", message);
        return JSON.toJSONString(map);
    }

    public ReturnMessage() {
        map.put("result", "0000");
        map.put("message", "ok");
    }

    public ReturnMessage(String result, String message) {
        map.put("result", result);
        map.put("message", message);
    }

    public void put(String key, Object data) {
        map.put(key, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(map);
    }

}
