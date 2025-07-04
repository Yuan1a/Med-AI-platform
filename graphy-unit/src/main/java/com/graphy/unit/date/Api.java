package com.graphy.unit.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期/时间处理
 */
@SuppressWarnings("all")
public class Api {


    /**
     * 获得当前日期属于今年的第几周
     *
     * @param date
     * @return
     */
    public static int weekOfyear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前日期周数
     *
     * @param date
     * @return
     */
    public static int getWeekOfDay(Date date) {
        //注意参数的样式为yyyy-MM-dd,必须让参数样式与所需样式统一
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 日期加上指定天数
     *
     * @param date
     * @param value
     * @return
     */
    public static Date dateAddDate(Date date, Integer value) {
        if (date == null) return null;
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, value);
        return rightNow.getTime();
    }

    /**
     * 日期加上指定月数
     *
     * @param date
     * @param value
     * @return
     */
    public static Date dateAddMonth(Date date, Integer value) {
        if (date == null) return null;
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, value);
        return rightNow.getTime();
    }

    /**
     * 获得当前日期属于本月的第几周
     *
     * @param date
     * @return
     */
    public static int weekOfmonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取指定日期的周一的日期
     *
     * @param date
     * @return
     */
    public static Date getBeginDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return cal.getTime();
    }


    /**
     * 获取日期区间
     *
     * @param date 参照日期
     * @param type 1=所在年份 2=所在季度 3=所在月份 4=所在周
     * @return
     */
    public static Date[] getDateInterval(Date date, String type) throws Exception {
        Date[] dates = new Date[2];
        if (type.equals("1")) {
            dates = getYearStartAndEndDate(date);
        } else if (type.equals("2")) {
            dates = getCurrentQuarterStartAndEnd(date);
        } else if (type.equals("3")) {
            dates = getMonthStartAndEndDate(date);
        } else if (type.equals("4")) {
            dates = getWeekStartandEndDate(date);
        }
        return dates;
    }


    /**
     * 日期加上指定秒数
     *
     * @param date
     * @param value
     * @return
     */
    public static Date dateAddSecond(Date date, Integer value) {
        if (date == null) return null;
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.SECOND, value);
        return rightNow.getTime();
    }

    /**
     * 时间格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateFormat(Date date, String format) {
        if (date == null) return "";
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 时间转日期
     *
     * @param date
     * @return
     */
    public static Date timeToDate(Date date) throws Exception {
        if (date == null) return null;
        return dateFormat(dateFormat(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    /**
     * 时间格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static Date dateFormat(String date, String format) throws Exception {
        if (date == null || date.equals("")) return null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }


    /**
     * 获取当前日期所在季度的开始日期与截止日期
     *
     * @param today
     * @return
     * @throws ParseException
     */
    public static Date[] getCurrentQuarterStartAndEnd(Date today) throws ParseException {
        Date[] arr = new Date[2];
        arr[0] = getCurrentQuarterStartDate(today);
        arr[1] = getCurrentQuarterEndDate(today);
        return arr;
    }


    /**
     * 当前季度的开始日期
     *
     * @param today
     * @return
     */
    private static Date getCurrentQuarterStartDate(Date today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = sdf.parse(sdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束日期
     *
     * @param today
     * @return
     */
    private static Date getCurrentQuarterEndDate(Date today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = sdf.parse(sdf.format(c.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 获取本周的第一天日期和最后一天日期（按中国周）
     *
     * @param today :"2017-03-15"
     * @return arr[0] 第一天日期 ；arr[1]最后一天日期
     * @throws ParseException
     */
    public static Date[] getWeekStartandEndDate(Date today) throws ParseException {
        Date[] arr = new Date[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        //所在周开始日期
        arr[0] = sdf.parse(sdf.format(cal.getTime()));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        //所在周结束日期
        arr[1] = sdf.parse(sdf.format(cal.getTime()));
        return arr;
    }

    /**
     * 获取指定月的第一天日期和最后一天日期
     *
     * @param today:"2017-03-15"
     * @return arr[0] 第一天日期 ；arr[1]最后一天日期
     * @throws ParseException
     */
    public static Date[] getMonthStartAndEndDate(Date today) throws ParseException {
        Date[] arr = new Date[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.set(Calendar.DAY_OF_MONTH, 1);
        arr[0] = sdf.parse(sdf.format(c.getTime()));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        arr[1] = sdf.parse(sdf.format(c.getTime()));
        return arr;
    }

    /**
     * 获取指定年的第一天日期和最后一天日期
     *
     * @param today :"2017-03-15"
     * @return arr[0] 第一天日期 ；arr[1]最后一天日期
     * @throws ParseException
     */
    public static Date[] getYearStartAndEndDate(Date today) throws ParseException {
        Date[] arr = new Date[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.set(Calendar.DAY_OF_YEAR, 1);
        arr[0] = sdf.parse(sdf.format(c.getTime()));
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        arr[1] = sdf.parse(sdf.format(c.getTime()));
        return arr;

    }

    /**
     * 根据指定周获取开始，结束日期
     *
     * @param week “2017-24”
     * @return
     */
    public static Date[] getStartEndByWeek(String week) throws Exception {
        Date[] weekArr = new Date[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] arr = week.split("-");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(arr[0]));
        c.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(arr[1]));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 2;
        c.add(Calendar.DATE, -dayOfWeek); // 得到本周的第一天
        weekArr[0] = sdf.parse(sdf.format(c.getTime()));
        c.add(Calendar.DATE, 6); // 得到本周的最后一天
        weekArr[1] = sdf.parse(sdf.format(c.getTime()));
        return weekArr;
    }


    /**
     * 两个日期相关天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDateDiff(Date date1, Date date2) {
        long diff = 0;
        try {
            diff = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1.getTime() - date2.getTime())
                    / (24 * 60 * 60 * 1000)
                    : (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
        }
        return diff;
    }

}
