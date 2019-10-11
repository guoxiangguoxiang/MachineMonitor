package com.zju.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式工具
 */
public class DateUtil {


    public static String getFormatTime(SimpleDateFormat format, Date time) {
        if (time == null) {
            return null;
        }
        return format.format(time);
    }

    public static Date parseFormatTime(SimpleDateFormat format, String date) {
        if (date == null || date.equals("")) {
            return null;
        }
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormatTime(String format, Date time) {
        return getFormatTime(new SimpleDateFormat(format), time);
    }

    public static Date parseFormatTime(String format, String date) {
        return parseFormatTime(new SimpleDateFormat(format), date);
    }

    public static Date getNow() {
        return new Date();
    }

    public static Date getDate(long pre) {
        return new Date(getNow().getTime() - pre);
    }

    public static Date getDate(int type, int pre) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getNow()); // 设置为当前时间
        calendar.set(type, calendar.get(type) + pre);
        return calendar.getTime();
    }

}
