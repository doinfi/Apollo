package com.dubbo.demo.logs.enums;

/**
 * 模块枚举类<br>
 * 枚举说明 枚举类只读<br>
 * OPERATE表示用户操作日志,<br>
 * ERROR表示系统错误日志,<br>
 * DUBUG表示debug级别的日志,<br>
 * INFOE表示info级别的日志<br>
 * Created by Infi on 17/2/19.
 */
public enum LogLevelEnum {
    OPERATE("Operate", 1), ERROR("Error", 2), DUBUG("dubug", 3), INFO("info", 4);
    // 成员变量
    private String name;
    private int value;

    /**
     * 构造方法<br>
     *
     * @param name  name<br>
     * @param value value<br>
     */
    LogLevelEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 只提供get方法,不提供set方法。(只读功能)

    /**
     * 获取枚举name<br>
     *
     * @return 返回枚举name<br>
     */
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
