package com.herman.di.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @author hsh
 * @create 2018-08-29 15:30
 **/
public class DateUtils {

    public static final String DEFAULTDATEPATTERN = "yyyy-MM-dd";
    public static final String DEFAULTDATETIMEPATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat DATEFORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATETIMEFORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DateUtils() {
    }

    public static String getDatePattern() {
        return "yyyy-MM-dd";
    }

    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, getDatePattern());
    }

    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);

        try {
            return df.parse(strDate);
        } catch (ParseException var4) {
            throw new IllegalArgumentException("日期格式有误");
        }
    }

    public static String getCurrenDateTime() {
        return DATETIMEFORMATTER.format(new Date());
    }

    public static String getCurrenDate() {
        return DATEFORMATTER.format(new Date());
    }

    public static int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(7);
        return day == 1 ? 7 : day - 1;
    }

    public static List<Date> dateToWeek(Date date) {
        int b = getWeek(date);
        List<Date> list = new ArrayList();
        Long fTime = Long.valueOf(date.getTime() - (long) (b * 24 * 3600000));

        for (int a = 0; a < 8; ++a) {
            Date fdate = new Date();
            fdate.setTime(fTime.longValue() + (long) (a * 24 * 3600000));
            list.add(a, fdate);
        }

        return list;
    }

    public static String getMonday(String date) {
        try {
            return format((Date) dateToWeek(parse(date)).get(1));
        } catch (ParseException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static String getSunDay(String date) {
        try {
            List<Date> dates = dateToWeek(parse(date));
            return format((Date) dates.get(dates.size() - 1));
        } catch (ParseException var3) {
            var3.printStackTrace();
            return "";
        }
    }

    public static String getTomrrow(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, 1);
        return format(calendar.getTime());
    }

    public static String getYesterday(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, -1);
        return format(calendar.getTime());
    }

    public static String getGepDaytime(Date date, int gap) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, gap);
        return format(calendar.getTime());
    }

    public static int compareDate(String date1, String date2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dt1 = df.parse(date1);
        Date dt2 = df.parse(date2);
        return dt1.getTime() > dt2.getTime() ? 1 : (dt1.getTime() < dt2.getTime() ? -1 : 0);
    }

    public static int compareDate(String date1, String date2, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        Date dt1 = df.parse(date1);
        Date dt2 = df.parse(date2);
        return dt1.getTime() > dt2.getTime() ? 1 : (dt1.getTime() < dt2.getTime() ? -1 : 0);
    }

    public static boolean isInTime(String sourceTime, String curTime) {
        if (sourceTime != null && sourceTime.contains("-") && sourceTime.contains(":")) {
            if (curTime != null && curTime.contains(":")) {
                String[] args = sourceTime.split("-");
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                try {
                    long now = sdf.parse(curTime).getTime();
                    long start = sdf.parse(args[0]).getTime();
                    long end = sdf.parse(args[1]).getTime();
                    if (args[1].equals("00:00")) {
                        args[1] = "24:00";
                    }

                    return end < start ? now < end || now >= start : now >= start && now < end;
                } catch (Exception var10) {
                    throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
                }
            } else {
                throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
            }
        } else {
            throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }
    }

    /**
     * 格式日期
     * @param date 日期
     * @return String
     */
    public static String formateMinute(Date date) {
        long minutes = getMinuteByDate(date);
//        System.out.println(minutes);
        if (minutes <= 1) {
            return "1分钟前";
        } else if (minutes <= 3) {
            return "5分钟前";
        } else if (minutes <= 10) {
            return "10分钟前";
        } else if (minutes <= 30) {
            return "半小时前";
        } else if (minutes <= 60) {
            return "1小时前";
        } else if (minutes <= 120) {
            return "2小时前";
        } else if (minutes <= 360) {
            return "半天前";
        } else if (minutes <= 720) {
            return "一天前";
        } else if (minutes <= 2880) {
            return "两天前";
        } else if (minutes <= 4320) {
            return "三天前";
        } else if (minutes <= 5760) {
            return "四天前";
        } else {
            return new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").format(date);
        }
    }

    /**
     * 当前时间距离某个日期相差多少分钟
     *
     * @param date 某个日期
     * @return int 分钟数
     */
    public static long getMinuteByDate(Date date) {
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(new Date());    //设置为当前系统时间
        dateTwo.setTime(date);
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeOne - timeTwo) / (1000 * 60);//转化minute
        return minute;
    }

    /**
     * 两个日期相差多少分钟
     * @param startDate 开始时间
     * @param endDate 结束日期
     * @return long 多少分钟
     */
    public static long getMinuteByDate(Date startDate, Date endDate) {
        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();
        dateOne.setTime(startDate);
        dateTwo.setTime(endDate);
        long timeOne = dateOne.getTimeInMillis();
        long timeTwo = dateTwo.getTimeInMillis();
        long minute = (timeOne - timeTwo) / (1000 * 60);//转化minute
        return minute;
    }


    /**
     * 根据当前日期获取到零点秒数
     */
    public static int getMinuteByZero() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, (day + 1));
        Date parse = DateUtils.parse(DateUtils.format(c.getTime()));
        return new Long(getMinuteByDate(parse, new Date()) * 60).intValue();
    }

    /**
     * 获取指定月份的天数
     *
     * @param year  年
     * @param month 月
     * @return 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取某月所有天数
     *
     * @param month 指定月任意天数
     * @return List<String>
     */
    public static List<String> getMontyDay(Date month) {
        List<String> dayList = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);//month 为指定月份任意日期
        int year = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int dayNumOfMonth = getDaysByYearMonth(year, m + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dayList.add(simpleDateFormat.format(d));
        }
        return dayList;
    }

    /**
     * 获取某日期 年
     *
     * @return year
     */
    public static String getYear(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nsdf = new SimpleDateFormat("yyyy");
        return nsdf.format(sdf.parse(date));
    }

    /**
     * 获取某日期 月
     * @return month
     */
    public static String getMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nsdf = new SimpleDateFormat("MM");
        Date parse = sdf.parse(date);
        return nsdf.format(parse);
    }


    /**
     * 获取某日期 日
     * @param date 时间
     * @return month
     */
    public static String getDay(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nsdf = new SimpleDateFormat("dd");
        return nsdf.format(sdf.parse(date));
    }

    /**
     * 获取某个区间内所有时间
     *
     * @param startDay 开始日期(before)
     * @param endDay   结束日期(after)
     * @return List<String>所有日期集合
     */
    public static List<String> getDayList(String startDay, String endDay) {
        List<String> dayList = new ArrayList<String>();
        Calendar start = Calendar.getInstance();
        try {
            start.set(Integer.parseInt(getYear(startDay)), Integer.parseInt(getMonth(startDay)) - 1, Integer.parseInt(getDay(startDay)));
            Long startTIme = start.getTimeInMillis();
            Calendar end = Calendar.getInstance();
            end.set(Integer.parseInt(getYear(endDay)), Integer.parseInt(getMonth(endDay)) - 1, Integer.parseInt(getDay(endDay)));
            Long endTime = end.getTimeInMillis();
            Long oneDay = 1000 * 60 * 60 * 24l;
            Long time = startTIme;
            while (time <= endTime) {
                Date d = new Date(time);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                dayList.add(df.format(d));
                time += oneDay;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayList;
    }


}
