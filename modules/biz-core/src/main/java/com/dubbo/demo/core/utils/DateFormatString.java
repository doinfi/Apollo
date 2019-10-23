/**
 * @author administrator
 * @date 2008-9-1
 * @project COM.lingdian.TOOLS
 * @package com.lingdian.tools.date
 * @class DateFormatString.java
 */
package com.dubbo.demo.core.utils;

/**
 * 时间格式化类.
 *
 * @author administrator
 * @version V100R100C01
 * @package com.lingdian.tools.date
 * @class DateFormatString.java
 */
public enum DateFormatString {
    /**
     * 指定日期是yyyyMMddHHmmss
     */
    yyyy_MM_dd_HH_mm_ss("yyyyMMddHHmmss"),

    /**
     * 指定日期是yyyyMMddhhmmss
     */
    yyyy_MM_dd_hh_mm_ss("yyyyMMddhhmmss"),

    /**
     * 指定日期是yyyy-MM-dd HH:mm:ss
     */
    yyyy_MM_dd_HH_mm_ss_H("yyyy-MM-dd HH:mm:ss"),

    /**
     * 指定日期是yyyy-MM-dd hh:mm:ss
     */
    yyyy_MM_dd_hh_mm_ss_H("yyyy-MM-dd hh:mm:ss"),

    /**
     * 指定日期是yyyyMMdd
     */
    yyyy_MM_dd(YFFormat.YYYYMMDD),

    /**
     * 指定日期是yyyy-MM-dd
     */
    yyyy_MM_dd_H(YFFormat.YYYY_MM_DD);

    /**
     * 保存指定的格式化串
     */
    private final String format;

    /**
     * 私有的构造方法
     *
     * @param format 格式化串
     */
    private DateFormatString(String format) {
        this.format = format;
    } // End DateFormatString

    /**
     * 返回格式华日期的字符串
     *
     * @return String 格式化串
     */
    @Override
    public String toString() {
        return format;
    } // End toString
} // End enum DateFormatString
