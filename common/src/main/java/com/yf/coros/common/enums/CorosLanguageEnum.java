package com.yf.coros.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 语言枚举
 *
 * @author infi
 */
public enum CorosLanguageEnum {
    ZH_CN("zh-CN", "中文"),
    EN_US("en-US", "英语"),
    DE_DE("de-DE", "德语"),
    ES_ES("es-ES", "西班牙语"),
    FR_FR("fr-FR", "法语"),
    IT_IT("it-IT", "意大利语"),
    ID_ID("id-ID", "印尼"),
    JP_JP("jp-JP", "日语");

    private final String language;

    private final String desc;

    CorosLanguageEnum(final String language, final String desc) {
        this.language = language;
        this.desc = desc;
    }

    public static String getValue(String value) {
        CorosLanguageEnum[] businessModeEnums = values();
        for (CorosLanguageEnum businessModeEnum : businessModeEnums) {
            if (businessModeEnum.desc().equals(value)) {
                return businessModeEnum.value();
            }
        }
        return null;
    }

    public static String getDesc(String value) {
        CorosLanguageEnum[] businessModeEnums = values();
        for (CorosLanguageEnum businessModeEnum : businessModeEnums) {
            if (StringUtils.equals(businessModeEnum.value(), value)) {
                return businessModeEnum.desc();
            }
        }
        return null;
    }

    public String value() {
        return this.language;
    }

    public String desc() {
        return this.desc;
    }
}
