package com.dubbo.demo.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DateUtil {


    public static Integer getYearNum(Date date) {
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        return currCal.get(Calendar.YEAR);
    }

    /**
     * description：获取当前时间的时间戳
     *
     * @return
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static String getCourseSummarytime(Date date) {
        return getString(date, YFFormat.YYYY_MM_DD_HH_MM);
    }

    /**
     * description：获取当前时间的时间戳
     *
     * @return
     * @throws Exception
     * @version 1.0 yyyy-MM-dd HH:mm:ss:SSS
     * @author ok
     */
    public static String getShareTimeS(Date date) {
        return getString(date, YFFormat.YYYY_MM_DD_HH_MM_SS_SSS);
    }

    /**
     * description：获取当前时间的时间戳
     *
     * @return
     * @throws Exception
     * @version 1.0 yyyy-MM-dd HH:mm:ss:SSS
     * @author ok
     */
    public static String getDateTimeString(Date date) {
        return getString(date, YFFormat.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * description：获取当前时间的时间戳
     * 日期格式转换, Date -> String
     *
     * @return YFFormat.YYYY_MM_DD, 如: 2016_12_20
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static String getDateString(Date date) {
        return getString(date, YFFormat.YYYY_MM_DD);
    }

    public static Date getDateStart() {
        return getDateStart(new Date());
    }

    public static Date getDateStart(Date date) {
        return parseDate(getDateString(date), YFFormat.YYYY_MM_DD);
    }

    /**
     * description：获取当前时间的时间戳yyyyMMdd
     *
     * @return
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static Integer getDateNumber(Date date) {
        String dataStr = getString(date, YFFormat.YYYYMMDD);
        if (dataStr != null) {
            return Integer.parseInt(dataStr);
        }
        return null;
    }

    /**
     * description：获取当前日期，日期格式是yyyyMMdd
     */
    public static Integer getTodayNumber() {
        return getDateNumber(new Date());
    }

    /**
     * description：获取当前时间的时间戳
     *
     * @return
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static String getMeetingTime(Date date) throws Exception {
        return getString(date, "HH:mm/dd/MM");
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    /**
     * 根据日期,获取到当前日期，本周一共有多少天
     *
     * @param date
     * @return
     */
    public static int getCountDaysTodayOfWeeks(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return intWeek;
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekCodeOfDate(Date date) {
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 将指定的日期字符串转化为日期对象
     *
     * @return java.util.Date
     */
    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YFFormat.YYYYMMDDHHMMSS);
        return df.format(date);
    }

    /**
     * description：获取当前时间的时间戳
     *
     * @return
     * @throws Exception
     * @version 1.0
     * @author ok
     */
    public static String getNowStamp() throws Exception {
        String a = null;
        Date d1 = new Date();
        Date d2 = parseDate("20120330", YFFormat.YYYYMMDD);
        a = String.valueOf((d1.getTime() - d2.getTime()) / 1000);
        return a;
    }


    /**
     * 根据匹配格式将时间转换为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getString(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat(format);
        String res = f.format(date);
        return res;
    }

    /**
     * 获取几个月后的时间
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getDatenextMonth(int num) {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(nowCalendar.MONTH, nowCalendar.get(nowCalendar.MONTH) + num);
        return nowCalendar.getTime();
    }

    /**
     * 获取num月后的日期
     *
     * @param date
     * @param num
     * @param flag 1-返回开始日期 2-返回结束日期
     * @return
     */
    public static Date getDateNextMonth(Date date, int num, int flag) {
        Calendar nextDate = Calendar.getInstance();
        nextDate.setTime(date);
        nextDate.set(Calendar.DATE, 1);// 设为当前月的1号
        // 返回结束日期
        if (2 == flag) {
            nextDate.add(Calendar.MONTH, num + 1);// 加减num
            nextDate.add(Calendar.DATE, -1);
        } else {
            // 返回开始日期
            nextDate.add(Calendar.MONTH, num);// 加减num
        }
        return nextDate.getTime();
    }

    /**
     * 获取几天后的时间
     *
     * @return
     */
    public static Date getDatenextday(int num) {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.add(Calendar.DATE, num);
        return nowCalendar.getTime();
    }

    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        if (dateStr.length() < format.length()) {
            format = format.substring(0, dateStr.length());
        }
        try {
            java.text.DateFormat df = new SimpleDateFormat(format);
            date = df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("日期解析错误:" + dateStr);
        }
        return date;
    }

    /**
     * 将秒转换为时间
     *
     * @param second
     * @return
     */
    public static Date parseDate4Second(Long second) {
        return new Date(second * 1000);
    }


    /**
     * 将秒转换为时间
     *
     * @param second
     * @param sFormat
     * @return
     */
    public static Date parseDate4Second(Long second, String sFormat) {
        return DateUtil.formatDate(new Date(second * 1000), sFormat);
    }

    /**
     * 将秒转换为时间字符串
     *
     * @param second
     * @param sFormat
     * @return
     */
    public static String getSecondString(Long second, String sFormat) {
        return DateUtil.getString(new Date(second * 1000), sFormat);
    }

    /**
     * 将秒转换为时间戳显示
     *
     * @param second
     * @param sFormat
     * @return
     */
    public static String getDateString4Second(Long second, String sFormat) {
        return DateUtil.getString(parseDate4Second(second), sFormat);
    }

    public static String getDateAfterDay(Date date, int iDay, String sFormat) {
        Date dToday = date;
        iDay = iDay - 3;
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(dToday);
        SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
        todayCalendar.add(Calendar.DAY_OF_MONTH, iDay);

        return dateFormat.format(todayCalendar.getTime());
    }

    /**
     * 获取几个月前的月份
     *
     * @return
     */
    public static String getPreviousMonthFirst(int n, Integer startDate) {
        String str = "";
        try {
            Date sDate = parseDate(startDate.toString(), YFFormat.YYYYMMDD);
            SimpleDateFormat sdf = new SimpleDateFormat(YFFormat.YYYY_MM_DD);
            Date pDate = getDateNextMonth(sDate, -n, 1);
            str = sdf.format(pDate);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return str;
    }

    /**
     * 获取几个月前的月份
     *
     * @return
     */
    public static String getPreviousMonthFirst(int n, String staredate) {
        String str = "";
        try {
            Date sDate = parseDate(staredate, YFFormat.YYYY_MM_DD);
            SimpleDateFormat sdf = new SimpleDateFormat(YFFormat.YYYY_MM_DD);
            Date pDate = getDateNextMonth(sDate, -n, 1);
            str = sdf.format(pDate);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return str;
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (60 * 60 * 1000);
            // System.out.println("相隔的天数="+day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 功能描述：时间相减得到秒数
     *
     * @param beginDate
     * @param endDate
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
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDate
     * @param endDate
     * @return int
     * @author Administrator
     */
    public static int getDaySub(Date beginDate, Date endDate) {
        Long day = 0l;
        try {
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
            // System.out.println("相隔的天数="+day);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day.intValue();
    }

    public static long daysBetween(Date beginDate, Date endDate) {
        long day = 0;
        try {
            beginDate = DateUtil.formatDate(beginDate, YFFormat.YYYY_MM_DD);
            endDate = DateUtil.formatDate(endDate, YFFormat.YYYY_MM_DD);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 得到某一天的该星期的第一日 00:00:00
     *
     * @param date
     * @param firstDayOfWeek 一个星期的第一天为星期几
     * @return
     */
    public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到一个星期的最后一日的时间,默认这个星期的第一天为星期天 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        return getLastDayOfWeek(date, Calendar.MONDAY);
    }

    /**
     * 得到指定日期相关的周的最后一日 00:00:00
     *
     * @param date
     * @param firstDayOfWeek
     * @return
     */
    public static Date getLastDayOfWeek(Date date, int firstDayOfWeek) {
        Calendar cal = Calendar.getInstance();
        if (date != null)
            cal.setTime(date);
        cal.setFirstDayOfWeek(firstDayOfWeek);
        cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据年月和这个月的第几个星期，返回那一周的开始日期，星期的第一天为星期天
     *
     * @author xuming 2014-05-21
     */

    public static Date getFirstDayOfWeek4Month(Date paraDate, int weekNum) {
        Date returnDate = weekCalendar(paraDate, weekNum, 1);
        return returnDate;
    }

    /**
     * 根据年月和这个月的第几个星期，返回那一周的结束日期，星期的第一天为星期天
     *
     * @author xuming 2014-05-21
     */

    public static Date getLastDayOfWeek4Month(Date paraDate, int weekNum) {
        Date returnDate = weekCalendar(paraDate, weekNum, 2);
        return returnDate;
    }

    /**
     * 根据年月，需要获取的第几周，返回第几周的开始日期或者结束日期
     *
     * @param paraDate
     * @param weekNum
     * @param flag     1-希望返回开始日期 2-希望返回结束日期
     * @return 返回 开始日期或者结束日期
     */

    public static Date weekCalendar(Date paraDate, int weekNum, int flag) {
        Date returnDate = null;
        Calendar c_now = new GregorianCalendar();
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        Calendar real_end_date = new GregorianCalendar();
        // DateFormatSymbols dfs = new DateFormatSymbols();
        // String[] weeks = dfs.getWeekdays();

        // 设置参数，年，月
        // String stDate = "2014-5-5";
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
        Date cDate = paraDate;
        c_now.setTime(cDate);
        int year = c_now.get(Calendar.YEAR);
        int month = c_now.get(Calendar.MONTH) + 1;
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0)
            days[2] = 29;// 大年
        c_begin.set(year, month - 1, 1); // 月 0-11 天 0-
        c_end.set(year, month - 1, days[month]);
        real_end_date.set(year, month - 1, days[month]);

        int count = 1;
        c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
        while (c_begin.before(c_end)) {

            if (weekNum == count) {
                if (flag == 1) {// 找的是开始时间
                    returnDate = new java.sql.Date(c_begin.getTime().getTime());
                    break;
                } else {// 找的是结束时间
                    if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c_begin.compareTo(real_end_date) == 0) {
                        returnDate = new java.sql.Date(c_begin.getTime().getTime());
                        break;
                    }
                }

            }
        /*
         * System.out.println("第" + count + "周  日期：" + new
	     * java.sql.Date(c_begin.getTime().getTime()) + ", " +
	     * weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
	     */
            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                count++;
            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        // System.out.println("共计（跨越）：" + (count - 1) + "周");

        return returnDate;
    }

    /**
     * 计算传进去的年月，计算这个月一共有多少周
     *
     * @param paraDate
     * @return
     * @throws Exception
     */
    public static int getWeekCountOfMonth(Date paraDate) {
        int returnWeeks = 0;
        Calendar c_now = new GregorianCalendar();
        Calendar c_begin = new GregorianCalendar();
        Calendar c_end = new GregorianCalendar();
        // DateFormatSymbols dfs = new DateFormatSymbols();

        Date cDate = paraDate;
        c_now.setTime(cDate);
        int year = c_now.get(Calendar.YEAR);
        int month = c_now.get(Calendar.MONTH) + 1;
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0)
            days[2] = 29;// 大年
        c_begin.set(year, month - 1, 1); // 月 0-11 天 0-
        c_end.set(year, month - 1, days[month]);

        int count = 1;
        c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
        while (c_begin.before(c_end)) {
            if (c_begin.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                count++;
            }
            c_begin.add(Calendar.DAY_OF_YEAR, 1);
        }
        // System.out.println("共计（跨越）：" + (count - 1) + "周");
        returnWeeks = count - 1;

        return returnWeeks;
    }

    /**
     * 添加天
     *
     * @param v
     * @param day
     * @return date
     */
    public static Date addDays(Date v, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(v);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取上周的最后一天日期
     *
     * @param firstDayOfWeek
     * @return
     */
    public static Date getLastDayOfLastWeek(int firstDayOfWeek) {
        Date date = getFirstDayOfWeek(new Date(), firstDayOfWeek);
        date = addDays(date, -1);// 本周第一天的上一天就是上周的最后一天
        return date;
    }

    /**
     * 获取上周的第一天的日期
     *
     * @param firstDayOfWeek
     * @return
     */
    public static Date getFirstDayOfLastWeek(int firstDayOfWeek) {
        Date date = getLastDayOfLastWeek(firstDayOfWeek);
        date = getFirstDayOfWeek(date, firstDayOfWeek);
        return date;
    }

    public static Date getMinDate(Date date1, Date date2) {
        Date returnDate = null;
        if (date1.before(date2)) {
            returnDate = date1;
        } else {
            returnDate = date2;
        }
        return returnDate;
    }

    public static Date getMaxDate(Date date1, Date date2) {
        Date returnDate = null;
        if (date1.before(date2)) {
            returnDate = date2;
        } else {
            returnDate = date1;
        }
        return returnDate;
    }

    // 判断输入的日期是否合法
    public static boolean isDateFormat(String str) {
        SimpleDateFormat format = new SimpleDateFormat(YFFormat.YYYY_MM_DD);
        try {
            format.setLenient(false);
            Date date = format.parse(str);
            // System.out.println("Date==="+date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // 获取年份
    public static Date getYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(YFFormat.YYYY);

        String strDate = format.format(date);
        Date year = null;
        try {
            year = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }

    // 格式化日期
    public static Date formatDate(Date date, String sFormat) {
        SimpleDateFormat format = new SimpleDateFormat(sFormat);
        String strDate = format.format(date);
        Date formateDate = null;
        try {
            formateDate = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formateDate;
    }

    /**
     * Long转Date
     *
     * @param dateFormat
     * @param millSec
     * @return
     */
    public static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 获取2个日期段内的日期
     *
     * @param dBegin
     * @param dEnd
     * @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    public static List<String> findDates1(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        String sBegin = getString(dBegin, YFFormat.YYYY_MM_DD);
        lDate.add(sBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(getString(calBegin.getTime(), YFFormat.YYYY_MM_DD));
        }
        return lDate;
    }

    /**
     * 获取几前的日期
     *
     * @param date
     * @param iDay
     * @param sFormat
     * @return
     */
    public static String getDateAfterDay_new(Date date, int iDay, String sFormat) {
        Date dToday = date;
        Calendar todayCalendar = Calendar.getInstance();
        todayCalendar.setTime(dToday);
        SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
        todayCalendar.add(Calendar.DAY_OF_MONTH, -iDay);

        return dateFormat.format(todayCalendar.getTime());
    }

    public static String getChineseDate(Date date) {
        String strDate = "";
        int days = (int) daysBetween(date, new Date());
        switch (days) {
            case 0:
                strDate = "今天" + DateUtil.getString(date, YFFormat.HH_MM);
                break;
            case 1:
                strDate = "昨天" + DateUtil.getString(date, YFFormat.HH_MM);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                // strDate = days + "天前";
                strDate = DateUtil.getString(date, YFFormat.YYYY_MM_DD_HH_MM);
                break;
            default:
                // strDate = "一周前";
                strDate = DateUtil.getString(date, YFFormat.YYYY_MM_DD_HH_MM);
                break;
        }
        // strDate = strDate+DateUtil.getString(date,
        // DateUtil.DATE_FORMAT_HH_MM);
        return strDate;
    }

    public static String getNexMinute(String str, int minute) throws Exception {
        String fmt = "yyyy-MM-dd HH:mm";
        String fmt2 = "HH:mm";
        Calendar calendar = TimeUtil.getCalendar(str, fmt);
        calendar.add(Calendar.MINUTE, minute);
        return TimeUtil.formatDate(calendar, fmt2);
    }

    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    /**
     * 添加小时
     *
     * @param v
     * @param hours
     * @return date
     */
    public static Date addHours(Date v, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(v);
        cal.add(Calendar.HOUR, hours);
        return cal.getTime();
    }

    /**
     * 按照提供的字符串格式化串与日期字符串，判断该字符串是否能转换为日期对象
     *
     * @param dateFormat 格式化串
     * @param str        日期字符串
     * @return true:可转日期对象;false:不可转日期对象.
     */
    public static boolean matchFormat(DateFormatString dateFormat, String str) {
        // 把格式化串转对正则表达式
        String format = dateFormat.toString().toLowerCase();
        format = format.replaceAll("[d]", "\\\\d");
        format = format.replaceAll("[y]", "\\\\d");
        format = format.replaceAll("[m]", "\\\\d");
        format = format.replaceAll("[h]", "\\\\d");
        format = format.replaceAll("[s]", "\\\\d");

        return str.matches("^" + format + "$");
    } // End matchFormat

    /**
     * @param timestampInSecond 秒时间戳
     * @param timezoneId        分钟数据所属时区，以15分钟为单位。
     *                          东八区（utc+8） = 8*60/15 = 32
     *                          西八区（utc-8） = =8*60/15 = -32
     * @return
     */
    public static Date getHappenDate(Long timestampInSecond, String timezoneId) {
        Date happenDate = null;
        Date date = new Date(timestampInSecond * 1000);
        TimeZone timezone = TimeZone.getTimeZone(timezoneId);
        if (null != timezone) {
            SimpleDateFormat inputFormat = new SimpleDateFormat(YFFormat.YYYY_MM_DD_HH_MM_SS);
            inputFormat.setTimeZone(timezone);
            String aString = inputFormat.format(date);
            happenDate = parseDate(aString, YFFormat.YYYY_MM_DD_HH_MM_SS);
            System.out.print(timezone.getID() + "=");
        }
        return happenDate;
    }

    /**
     * @param timestampInSecond 秒时间戳
     * @param timezone          分钟数据所属时区，以15分钟为单位。
     *                          东八区（utc+8） = 8*60/15 = 32
     *                          西八区（utc-8） = =8*60/15 = -32
     * @return
     */
    public static Date getHappenDate(Long timestampInSecond, int timezone) {
        Date happenDate = null;
        int rawOffset = (int) (timezone * 15 / 60.0 * 3600 * 1000);
        String[] ids = TimeZone.getAvailableIDs(rawOffset);
        if (null != ids && ids.length > 0) {
            return getHappenDate(timestampInSecond, ids[0]);
        }
        return happenDate;
    }

    /**
     * 获取所有的时区编号. <br>
     * 排序规则:按照ASCII字符的正序进行排序. <br>
     * 排序时候忽略字符大小写.
     *
     * @return 所有的时区编号(时区编号已经按照字符[忽略大小写]排序).
     */
    public static String[] fecthAllTimeZoneIds() {
        Vector v = new Vector();
        String[] ids = TimeZone.getAvailableIDs();
        for (int i = 0; i < ids.length; i++) {
            v.add(ids[i]);
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        v.copyInto(ids);
        v = null;
        return ids;
    }

    //计算花费多少毫秒
    public static long spendTime(long startTime) {
        return new Date().getTime() - startTime;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(DateUtil.getDateTimeString(getHappenDate(1482148800l, 32)));
        System.out.println(DateUtil.getDateTimeString(getHappenDate(1482148800l, 33)));
        System.out.println(DateUtil.getDateTimeString(getHappenDate(1482148800l, 34)));
        System.out.println(DateUtil.getDateTimeString(getHappenDate(1482148800l, 35)));
        System.out.println(DateUtil.getDateTimeString(getHappenDate(1482148800l, 38)));

        String[] ids = fecthAllTimeZoneIds();
        for (String string : ids) {
            //System.out.println(string);
        }
    /*
     * String strDate = "2014-06"; Date paraDate =
	 * DateStamp.parseDate(strDate, DateStamp.DATE_FORMAT_YYYY_MM_DD); int
	 * flag = 0; int weekNum = 4;
	 *
	 * String str = "开始日期"; if (flag == 0) { str = "结束日期"; }
	 *
	 * System.out.println(strDate + "的第" + weekNum + "周的" + str + "是 " +
	 * weekCalendar(paraDate, weekNum, flag));
	 */
        // System.out.println(strDate + " 这个月一共有多少个周 ==" +
        // weekOfMonth(paraDate));

        // System.out.println(DateStamp.getDaySub(new Date(),
        // DateStamp.getDatenextday(7)));
        // Date firstDayOfWeek = DateStamp.getFirstDayOfWeek(new
        // Date(),Calendar.MONDAY);
        // Date lastDayOfLastWeek = DateStamp.addDays(firstDayOfWeek,-1);
        // System.out.println(lastDayOfLastWeek);

        // System.out.println(DateStamp.getFirstDayOfLastWeek(Calendar.MONDAY));
        // System.out.println(DateStamp.getLastDayOfLastWeek(Calendar.MONDAY));
    /*
     * Date beginDate = null; Date endDate =
	 * DateStamp.parseDate("2000-01-01", DateStamp.DATE_FORMAT_YYYY_MM_DD);
	 * System.out.println(DateStamp.getMinDate(beginDate, endDate));
	 */
        // System.out.println(isDateFormat("2000-01-19"));
        // System.out.println(getDaySub(new Date(),new Date()));
        // System.out.println(getDatenextday(-1));
        // System.out.println(getDaySub(getDatenextday(-1),new Date()));
        // long aa =1422908040000l;
        // System.out.println(transferLongToDate("yyyy-MM-dd HH:mm:ss",aa));
        Date cdate = DateUtil.parseDate("2015-07-14 12:00:01", YFFormat.YYYY_MM_DD_HH_MM_SS);
        // Date sDate
        // =DateUtil.getDate("2015-07-15",DateUtil.DATE_FORMAT_YYYY_MM_DD);
        // System.out.println(sDate.after(cdate));
        // System.out.println(sDate.compareTo(cdate));
        // System.out.println(cdate.compareTo(sDate));
        String cc = "2015-08-11 10:02:10";
        System.out.println(getNexMinute(cc, 1));
    }
}
