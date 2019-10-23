package com.dubbo.demo.common.vo;

/**
 * 状态结果枚举定义<br>
 * 1. 与丁慧 ReturnMessage 重合, 讨论优化。<br>
 * 2. 确保定义的result 与旧版的保持一致。<br>
 * <p>
 * 返回编码结构定义: x-xxx或者xx-xxx,[模块][错误]。<br>
 * 编码结构说明:<br>
 * 1. 如果是四位编码,第一位表示模块;如果是五位编码,前两位表示模块。<br>
 * 2. 在错误编码中模块可以是业务逻辑上的"模块",比如说公共模块,如果有必要可以分为手环绑定"模块",验证码"模块"<br>
 * 3. 最后三位表示错误编号。<br>
 * <p>
 * 返回编号分段定义:
 * 1. 0000: 表示操作成功
 * 2. 1001 - 1999: 表示系统错误信息
 * 3. 2001 - 2999: 表示账户模块
 * 4. 3001 - 3999: 表示用户模块
 * 5. 4001 - 4999: 表示日志模块
 * 6. 5001 - 5999: 表示数据同步模块
 * 7. 6001 - 6999: 表示用户活动模块
 * 8. 7001 - 7999: 表示第三方接口模块
 * 9. 8001 - 8999: 表示公共模块(短信功能,手环激活功能)
 * 10. 9001 - 9999: 表示表盘固件模块
 * 11. 10001 - 10999: 表示数据统计模块
 * 12. 11001 - 11999: 表示接放层模块
 * 13. 80001 - 81999: 表示服务器后台页面模块
 *
 * @author hudahua
 */
public enum StatusResultEnum {

    /**
     * 成功
     */
    OK("0000", "ok"),
    /**
     * 系统错误
     */
    ERROR("1001", "system error"),

    /**
     * 请求太频繁
     */
    REQUEST_MORE_BY_MOBILE("1002", "the mobile or ip request more"),
    /**
     * 登录失败，用户名或密码有误
     */
    LOGIN_ERROR("1006", "please input correct user name and password"),
    /**
     * 用户不存在
     */
    USER_NO_EXIST("1029", "user no exist"),
    /**
     * 用户名不存在
     */
    USER_NAME_IS_EXIST("1013", "the user name is exist"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR("1031", "input parameter is error"),
    /**
     * 找不到文件
     */
    FILE_NOT_FOUND("1058", "file not found"),
    /**
     * 标签原始数据为空
     */
    SOURCE_DATA_NULL("1001", " w4ParseResult is null"),


    // ****天气相关*******
    /**
     * 找不到该城市
     */
    WEATHER_CITY_NOT_FOUND("1061", "weather city not found"),
    /**
     * 找不到该城市的天气
     */
    WEATHER_NOT_FOUND("1062", "weather not found"),

    /**
     * 没有新的24小时天气
     */
    WEATHER_NOT_NEW("1063", "not exists new weather"),

    // ****第三方接口相关*******
    CALLHARASS_ERROR("1071", "call harass build error"),


    /**
     * 日志模块,日志保存失败
     */
    LOG_SAVE_FAILED(ModuleTypeEnum.LOG, "001", "save failed log"),


    MOBILE_IS_NULL("1005", "the mobile is null"),

    /**
     * 验证码错误
     */
    CHECK_CODE_IS_NULL("1014", "the check code is nulll"),

    /**
     * 验证码无效
     */
    CHECK_CODE_IS_INVALID("1015", "the check code is invalid"),
    /**
     * 请求超过上限
     */
    UP_ACCESS_COUNT("1042", "you up to access times"),


    SERVER_MAINTENANCE("2020", "很抱歉,服务器维护中,请稍后再试。维护时间:14:20—16:00");


    private String result;
    private String message;
    private ModuleTypeEnum moduleType;

    /**
     * 新版定义的返回编码信息<br>
     *
     * @param moduleType 模块类型枚举<br>
     * @param result     返回编码<br>
     * @param message    返回信息<br>
     */
    private StatusResultEnum(ModuleTypeEnum moduleType, String result, String message) {
        this.moduleType = moduleType;
        // 返回编码是模块编码和错误编码组成
        this.result = moduleType.getValue().concat(result);
        this.message = message;
    }


    /**
     * 兼容老版本的系统信息返回<br>
     *
     * @param result  状态码<br>
     * @param message 状态消息<br>
     */
    private StatusResultEnum(String result, String message) {
        this.result = result;
        this.message = message;
    }


    /**
     * 获取返回编码<br>
     *
     * @return 返回编码<br>
     */
    public String getResult() {
        return result;
    }

    /**
     * 获取返回信息<br>
     *
     * @return 返回信息<br>
     */
    public String getMessage() {
        return message;
    }

    /**
     * 获取模块枚举对象<br>
     *
     * @return 返回模块枚举对象<br>
     */
    public ModuleTypeEnum getModuleType() {
        return moduleType;
    }

    /**
     * 直接把枚举转换成ResponseVO对象<br>
     * 用法:StatusResultEnum.OK.toResponseVO()<br>
     *
     * @return responseVO对象
     */
    public ResponseVO toResponseVO() {
        return new ResponseVO(this.getResult(), this.getMessage());
    }
}
