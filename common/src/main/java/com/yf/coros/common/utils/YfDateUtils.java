package com.yf.coros.common.utils;


import com.yf.coros.common.enums.MessageKey;
import com.yf.coros.common.exception.YfException;

import java.math.BigDecimal;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class YfDateUtils {


    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     *
     * @param timezone 时区
     */
    public static Integer getCurrentYYYYMMDD(Integer timezone) throws YfException {
        timezone = checkTimezone(timezone);
        DateTime dateTime = new DateTime(DateTimeZone.forOffsetMillis(900000 * timezone));
        return NumberUtils.toInt(dateTime.toString("yyyyMMdd"));
    }

    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     */
    public static Integer getCurrentYYYYMMDD() {
        return NumberUtils.toInt(new DateTime().toString("yyyyMMdd"));
    }

    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     *
     * @param timezone 时区
     */
    public static DateTime getCurrentDataTime(Integer timezone) {
        return new DateTime(DateTimeZone.forOffsetMillis(900000 * timezone));
    }


    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     */
    public static Integer getCurrentYYYYMMInt() {
        DateTime dateTime = new DateTime();
        return NumberUtils.toInt(dateTime.toString("yyyyMM"));
    }

    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     */
    public static String getCurrentYYYYMMString() {
        DateTime dateTime = new DateTime();
        return dateTime.toString("yyyyMM");
    }

    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     */
    public static String getCurrentYYYYMMddhhmmssString() {
        DateTime dateTime = new DateTime();
        return dateTime.toString("yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 功能描述：时间相减得到秒数
     *
     * @return long
     */
    public static long getSecondOf2Date(Date beginDate, Date endDate) {
        long second = 0;
        try {
            second = (endDate.getTime() - beginDate.getTime()) / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return second;
    }


    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param timestampInSecond 当前发送天,已经包含用户时区信息
     * @param firstDayOfWeek    一个星期的第一天为星期几。1:周日,2:周一,3:周二,4:周三,5:周四,6:周五,7:周六
     * @return 本周第一天的int值
     */
    public static Integer getFirstDayOfWeekInt(Long timestampInSecond, Integer timezoneInt,
                                               int firstDayOfWeek)
            throws YfException {
        timezoneInt = checkTimezone(timezoneInt);
        DateTime dateTime = new DateTime(timestampInSecond * 1000,
                DateTimeZone.forOffsetMillis(900000 * timezoneInt));
        DateTime monday = dateTime.withDayOfWeek(firstDayOfWeek)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0);
        return NumberUtils.toInt(monday.plusDays(-1).toString("yyyyMMdd"));

    }

    /**
     * 获取发送时间
     *
     * @param timestampInSecond 时间戳
     * @param timezoneInt       时区
     * @return 发送时间
     */
    public static DateTime getHappenDate(Long timestampInSecond, Integer timezoneInt)
            throws YfException {
        timezoneInt = checkTimezone(timezoneInt);
        DateTime dateTime = new DateTime(timestampInSecond * 1000,
                DateTimeZone.forOffsetMillis(900000 * timezoneInt));
        return dateTime;
    }

    /**
     * 使用UTC时间转换(0时区) 获得happenDay int类型的数据, 时间戳转换为utc时间,获得用户当地的时间
     *
     * @param timestampInSecond 秒时间戳
     * @param timezoneInt       时区
     * @return happenDay
     * @author infi
     */
    public static int getHappenDay(Long timestampInSecond, Integer timezoneInt) throws YfException {
        timezoneInt = checkTimezone(timezoneInt);
        DateTime dateTime = new DateTime(timestampInSecond * 1000,
                DateTimeZone.forOffsetMillis(900000 * timezoneInt));
        return NumberUtils.toInt(dateTime.toString("yyyyMMdd"));
    }

    /**
     * 使用UTC时间转换(0时区) 获得happenDay int类型的数据, 时间戳转换为utc时间,获得用户当地的时间
     *
     * @param timestampInSecond 秒时间戳
     * @return happenDay
     * @author infi
     */
    public static int getHappenDayUTC(Long timestampInSecond) throws YfException {
        DateTime dateTime = new DateTime(timestampInSecond);
        return NumberUtils.toInt(dateTime.toString("yyyyMMdd"));
    }

    /**
     * 检查时区
     *
     * @param timezone 时区在正负48之间
     */
    public static Integer checkTimezone(Integer timezone) throws YfException {
        if (timezone == null) {
            throw new YfException(MessageKey.TIMEZONE_ERROR);
        }
        if (Math.abs(timezone) >= 96) {
            return 0;
        } else {
            return timezone;
        }
    }


    /**
     * 获得happenMonth int类型的数据, 时间戳转换为utc时间,获得用户当地的时间
     *
     * @param timestampInSecond 秒时间戳
     * @param timezoneInt       时区
     * @return happenDay
     * @author infi
     */
    public static int getHappenMonth(Long timestampInSecond, Integer timezoneInt)
            throws YfException {
        return NumberUtils.toInt(getHappenDate(timestampInSecond, timezoneInt).toString("yyyyMM"));
    }

    /**
     * 获得指定时间在当天的第几个刻度,比如2017-01-01 00:12:00 是当天第一个15分钟刻度 区间算法,前闭后开,如:[0,15),[15,30)...
     *
     * @param timestampInSecond   时间戳
     * @param timezoneIn15Minutes 时区
     * @param segmentInMinutes    统计时间间隔(分钟)
     * @return 当天刻度
     */
    public static int getTimeIndexInDaySegment(long timestampInSecond, int timezoneIn15Minutes, int segmentInMinutes)
            throws YfException {
        if (segmentInMinutes == 0) {
            throw new YfException(MessageKey.PARAMETER_ERROR);
        }
        // 1. 分钟数据当天时间戳
        int minuteOfDay = getHappenDate(timestampInSecond, timezoneIn15Minutes).getMinuteOfDay();
        return new BigDecimal(minuteOfDay)
                .divide(new BigDecimal(segmentInMinutes), 0, BigDecimal.ROUND_DOWN).intValue();
    }


    //计算花费多少毫秒
    public static long spendTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    /**
     * 获得天的dateTime数据
     *
     * @param happenDay 天int数据
     * @return datetime
     */
    public static DateTime getDateTimeByHappenDay(int happenDay) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
        return DateTime.parse(String.valueOf(happenDay), format);
    }

    /**
     * 获得两个int类型的时间戳
     *
     * @param startDay 开始时间天
     * @param endDay   结束时间天
     */
    public static int getDaysBetween(int startDay, int endDay) throws YfException {
        int daysBetween = 0;
        try {
            DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
            DateTime startDatetime = DateTime.parse(String.valueOf(startDay), format);
            DateTime endDatetime = DateTime.parse(String.valueOf(endDay), format);
            daysBetween = Math.abs(Days.daysBetween(startDatetime.withTimeAtStartOfDay(),
                    endDatetime.withTimeAtStartOfDay()).getDays());

        } catch (IllegalFieldValueException ex) {
            log.error("日期错误：" + ex.toString());
            throw new YfException(MessageKey.PARAMETER_ERROR);
        } finally {
            return daysBetween;
        }
    }


    /**
     * 发生天 获取周首日
     *
     * @param happenDate 发生天
     * @return 周首日
     */
    public static Integer getFirstDayOfWeek(int happenDate) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");
        DateTime dateTime = DateTime.parse(String.valueOf(happenDate), format);
        DateTime monday = dateTime.withDayOfWeek(1)
                .withHourOfDay(0)
                .withMinuteOfHour(0)
                .withSecondOfMinute(0);
        return NumberUtils.toInt(monday.plusDays(-1).toString(format));
    }
}
