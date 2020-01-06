package com.apollo.common.utils;

import com.ibm.icu.util.LocaleMatcher;
import com.ibm.icu.util.ULocale;
import org.apache.commons.lang3.StringUtils;

/**
 * 语言信息
 */
public class ApolloLanguageUtils {
    private static final String NOT_FOUND_LANGUAGE = "not-found";

    /**
     * 获得http请求头部语言信息
     *
     * @param yfHeaderJson http头部信息
     * @return 语言信息
     */
    public static String getLanguage(String yfHeaderJson) {

        String language = "en-US";
//        String languageForHeader = YFHeaderUtils.getHeaderLanguage2String(yfHeaderJson);
        String languageForHeader = "zh-CN";
        if (StringUtils.isNotBlank(languageForHeader)) {
            language = languageForHeader;
        }

        LocaleMatcher localeMatcher = new LocaleMatcher("en-US,zh-CN");
        ULocale uLocale = localeMatcher.getBestMatch(language);
        return uLocale.toLanguageTag();
    }

    /**
     * 获取消息和APP版本信息用户语言匹配
     * 语言条件，查询消息逻辑：
     * * 1. 如果用户没有传递语言信息，返回默认语言的消息
     * * 2. 如果用户语言在语言列表中不存在，就返回默认语言的消息。"not-found,en-US,zh-CN,de-DE,fr-FR,it-IT,es-ES,id-ID,jp-JP"
     *
     * @param userLanguage 用户语言标识
     * @return 匹配到的语言标识
     */
    public static String getMsgAndAppVersionLanguage(String userLanguage) {
        return mateLanguage(userLanguage);
    }

    /**
     * 获得用户语言
     *
     * @param yfHeaderJson header json字符串
     * @return 用户语言
     */
    public static String getLanguageByRequest(String yfHeaderJson) {
        String userLanguage = "";
//        String languageForHeader = YFHeaderUtils.getHeaderLanguage2String(yfHeaderJson);
        String languageForHeader = "zh-CN";
        if (StringUtils.isNotBlank(languageForHeader)) {
            userLanguage = languageForHeader;
        }
        return mateLanguage(userLanguage);
    }

    /**
     * 匹配用户语言
     *
     * @param userLanguage 用户语言
     * @return 匹配后的系统语言
     */
    private static String mateLanguage(String userLanguage) {
        return mateLanguageInAllLanguage(userLanguage, "not-found,en-US,zh-CN,de-DE,fr-FR,it-IT,es-ES,id-ID,jp-JP");
    }

    /**
     * 获取消息和APP版本信息用户语言匹配
     * 语言条件，查询消息逻辑：
     * * 1. 邮件支持的语言：中文、英文、德语、西班牙语
     * * 2. 如果用户没有传递语言信息，返回默认语言的消息
     * * 3. 如果用户语言在语言列表中不存在，就返回默认语言的消息。"not-found,en-US,zh-CN,de-DE,es-ES"
     *
     * @param userLanguage    用户语言标识
     * @param defaultLanguage 默认语言
     * @param allLanguage     邮件规定语言列表
     * @return 匹配到的语言标识
     */
    public static String getEmailLanguage(String userLanguage, String defaultLanguage, String allLanguage) {
        // 1. 配置的语言列表为空，就返回默认语言
        if (StringUtils.isBlank(allLanguage)) {
            return defaultLanguage;
        }
        String language = mateLanguageInAllLanguage(userLanguage, allLanguage);
        // 2. 语言列表中没有找到匹配语言，就返回默认语言
        if (StringUtils.isBlank(language)) {
            return defaultLanguage;
        }
        return language;
    }

    /**
     * 匹配用户语言
     *
     * @param userLanguage 用户语言
     * @return 匹配后的系统语言
     */
    public static String getWatchFaceLanguage(String userLanguage, String allLanguage) {
        return mateLanguageInAllLanguage(userLanguage, allLanguage);
    }

    /**
     * 匹配语言
     *
     * @param userLanguage 用户语言
     * @param allLanguage  语言列表
     * @return 对应语言
     */
    private static String mateLanguageInAllLanguage(String userLanguage, String allLanguage) {
        String language = "";
        if (StringUtils.isNotBlank(userLanguage)) {
            LocaleMatcher localeMatcher = new LocaleMatcher(allLanguage);
            ULocale uLocale = localeMatcher.getBestMatch(userLanguage);
            userLanguage = uLocale.toLanguageTag();
            if (!StringUtils.equals(userLanguage, NOT_FOUND_LANGUAGE)) {
                language = userLanguage;
            }
        }
        return language;
    }
}
