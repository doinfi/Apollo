package com.dubbo.demo.core.utils;

import java.util.HashMap;
import java.util.Map;

public class YFFormat {

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";

    public static String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDD = "yyyyMMdd";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYYMM = "yyyyMM";

    public static String YYYY = "yyyy";

    public static String MM = "MM";

    public static String HH_MM = "HH:mm";

    public static String H24 = "HH";

    public static String NEW_LINE = System.getProperty("line.separator");

    static Map<Short, String> MAP_LABEL_MODE = null;

    static Map<String, String> MAP_WATCH_CLASS = null;

    public static String formatWatchClass(String watchClass) {
        // 表盘的类别，00-图形表盘,01-数字表盘，02-指针表盘
        if (Tools.isEmpty(MAP_WATCH_CLASS)) {
            MAP_WATCH_CLASS = new HashMap<>();
            MAP_WATCH_CLASS.put("00", "图形表盘");
            MAP_WATCH_CLASS.put("01", "数字表盘");
            MAP_WATCH_CLASS.put("02", "指针表盘");
        }
        if (MAP_WATCH_CLASS.containsKey(watchClass)) {
            return MAP_WATCH_CLASS.get(watchClass);
        }
        return "图形表盘";
    }

    static Map<String, String> MAP_RELEASE_TYPR = null;

    public static String formatReleaseType(String releaseType) {
        // 表盘的类别，00-图形表盘,01-数字表盘，02-指针表盘
        if (Tools.isEmpty(MAP_RELEASE_TYPR)) {
            MAP_RELEASE_TYPR = new HashMap<>();
            MAP_RELEASE_TYPR.put(ApplicationConstants.WATCH_RELEASE_TYPR_ISSUE, "正式发布");
            MAP_RELEASE_TYPR.put(ApplicationConstants.WATCH_RELEASE_TYPR_BETA, "公测");
            MAP_RELEASE_TYPR.put(ApplicationConstants.WATCH_RELEASE_TYPR_TEST, "内测");
        }
        if (MAP_RELEASE_TYPR.containsKey(releaseType)) {
            return MAP_RELEASE_TYPR.get(releaseType);
        }
        return "内测";
    }
}
