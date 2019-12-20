package com.apollo.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 工具类.
 *
 * @author infi
 * @version V100R100C01
 */
public class YfTools {
    /**
     * 根据key获取信息
     *
     * @param messageKey key值
     * @return 信息
     */
    public static String getMessageByKey(String messageKey) {
        Locale locale = new Locale("en", "US");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);
    }

    /**
     * 根据key获取信息
     *
     * @param messageKey         key值
     * @param languageAndCountry 语言和国家 zh-CN,en-US
     * @return 信息
     */
    public static String getMessageByKey(String messageKey, String languageAndCountry) {
        String language = "en";
        String country = "US";
        if (StringUtils.isNotBlank(languageAndCountry)) {
            String[] languageAndCountryArr = StringUtils.split(languageAndCountry, "-");
            if (ArrayUtils.isNotEmpty(languageAndCountryArr) || languageAndCountryArr.length >= 2) {
                language = StringUtils.lowerCase(languageAndCountryArr[0]);
                country = StringUtils.upperCase(languageAndCountryArr[languageAndCountryArr.length - 1]);
            }
        }
        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);

    }

    /**
     * 根据key获取信息
     *
     * @param messageKey key值
     * @param locale     语言本地化 zh-CN,en-US
     * @return 信息
     */
    public static String getMessageByKey(String messageKey, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);
    }

    /**
     * 根据key获取信息
     *
     * @param messageKey key值
     * @return 信息
     */
    public static String getMessageByKey(String messageKey, String language, String country) {
        Locale locale = new Locale("en", "US");
        if (StringUtils.isNotBlank(language) && StringUtils.isNotBlank(country)) {
            locale = new Locale(language, country);
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", locale);
        return resourceBundle.getString(messageKey);

    }
}
