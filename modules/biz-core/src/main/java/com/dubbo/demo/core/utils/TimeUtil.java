package com.dubbo.demo.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by sampson on 2/3/15.
 */
final public class TimeUtil {

    /**
     * @return "2015-01-03"
     */
    static public String getToday(String format) {
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    static public Date getDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    static public Calendar getCalendar(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.setTime(sdf.parse(date));
        return calendar;
    }

    static public String formatDate(Calendar calendar, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    public static int getElapsedMinutes(String startTime, String endTime) {
        SimpleDateFormat sb;
        if (startTime.length() == 10) {
            sb = new SimpleDateFormat(YFFormat.YYYY_MM_DD, Locale.CHINA);
        } else if (startTime.length() == 19) {
            sb = new SimpleDateFormat(YFFormat.YYYY_MM_DD_HH_MM_SS, Locale.CHINA);
        } else {
            return 0;
        }

        try {
            Date start = sb.parse(startTime), end = sb.parse(endTime);
            return (int) ((end.getTime() - start.getTime()) / 1000 / 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getEndDate(String beginDate, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat(YFFormat.YYYY_MM_DD);
        Calendar calendar = GregorianCalendar.getInstance();
        try {
            Date date = sdf.parse(beginDate);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days - 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
