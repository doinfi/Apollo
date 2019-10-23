package com.dubbo.demo.common.vo;

/**
 * 模块枚举类<br>
 * 枚举说明:<br>
 * SYSTEM("系统模块", 1),
 * ACCOUNT("账户模块", 2),
 * USER("用户模块", 3),
 * LOG("日志模块", 4),
 * DATA_SYNC("数据同步模块", 5),
 * PROMOTION("用户活动模块", 6),
 * EXTERNAL("第三方接口模块", 7),
 * COMMUNITY("公共模块", 8),
 * FIRMWARE_WATCHFACE("表盘和固件模块", 9),
 * MOTION_STAT("统计模块", 10),
 * ACCESS_LAYER("表示接放层模块", 11),
 * ADMIN("表示服务器后台页面模块", 80);
 * Created by Infi on 17/2/19.
 */
public enum ModuleTypeEnum {

    SUCCESSFUL_REQUEST("成功请求", "0"),
    SYSTEM("系统模块", "1"),
    ACCOUNT("账户模块", "2"),
    USER("用户模块", "3"),
    LOG("日志模块", "4"),
    DATA_SYNC("数据同步模块", "5"),
    PROMOTION("用户活动模块", "6"),
    EXTERNAL("第三方接口模块", "7"),
    COMMUNITY("公共模块", "8"),
    FIRMWARE_WATCHFACE("表盘和固件模块", "9"),
    MOTION_STAT("统计模块", "10"),
    ACCESS_LAYER("表示接放层模块", "11"),
    ADMIN("表示服务器后台页面模块", "80");

    private String name;
    private String value;

    /**
     * 构造<br>
     *
     * @param name  模块名称<br>
     * @param value 模块编号<br>
     */
    ModuleTypeEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取模块名称<br>
     *
     * @return 模块名称<br>
     */
    public String getName() {
        return name;
    }

    /**
     * 获取模块编号<br>
     *
     * @return 模块编号<br>
     */
    public String getValue() {
        return value;
    }
}
