package com.your.mall.utils;

import android.util.Log;

import com.your.mall.bean.DistanceTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/24
 * 类描述：
 * 修改备注：
 */
public class TimeUtils {
    public final static long yearLevelValue = 365 * 24 * 60 * 60 * 1000L;
    public final static long monthLevelValue = 30 * 24 * 60 * 60 * 1000L;
    public final static long dayLevelValue = 24 * 60 * 60 * 1000L;
    public final static long hourLevelValue = 60 * 60 * 1000L;
    public final static long minuteLevelValue = 60 * 1000L;
    public final static long secondLevelValue = 1000L;


    public static String getDifference(long nowTime, long targetTime) {//目标时间与当前时间差
        long period = targetTime - nowTime;
        Log.e("TimeUtils", period + "");
        return getDifference(period);
    }

    public static long getDistanceMillisecond(long nowTime, long targetTime) {//目标时间与当前时间差
        return targetTime - nowTime;
    }

    /**
     * 格式化时间转换为毫秒数
     *
     * @param time 2016-11-24 09:59:58
     * @return
     */
    public static long getTimestamp(String time) {
        long timestamp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timestamp = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("TimeUtils", timestamp + "");
        return timestamp;
    }

    public static long getTimestamp1(String time) {
        long timestamp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            timestamp = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("TimeUtils", timestamp + "");
        return timestamp;
    }

    private static String getDifference(long period) {//根据毫秒差计算时间差
        String result = null;

        /*******计算出时间差中的年、月、日、天、时、分、秒*******/
        int year = getYear(period);
        int month = getMonth(period - year * yearLevelValue);
        int day = getDay(period - year * yearLevelValue - month * monthLevelValue);
        int hour = getHour(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue);
        int minute = getMinute(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue - hour * hourLevelValue);
        int second = getSecond(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue - hour * hourLevelValue - minute * minuteLevelValue);
        result = year + "年" + month + "月" + day + "天" + hour + "时" + minute + "分" + second + "秒";
        return result;
    }

    public static DistanceTime getDistanceTime(long period) {//根据毫秒差计算时间差
        /*******计算出时间差中的年、月、日、天、时、分、秒*******/
        int year = getYear(period);
        int month = getMonth(period - year * yearLevelValue);
        int day = getDay(period - year * yearLevelValue - month * monthLevelValue);
        int hour = getHour(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue);
        int minute = getMinute(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue - hour * hourLevelValue);
        int second = getSecond(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue - hour * hourLevelValue - minute * minuteLevelValue);
        DistanceTime DistanceTime = new DistanceTime(year, month, day, hour, minute, second);
        return DistanceTime;
    }

    public static int getDistanceYear(long period) {
        return getYear(period);
    }

    public static int getDistanceMonth(long period) {
        return getMonth(period
                - getDistanceYear(period) * yearLevelValue);
    }

    public static int getDistanceDay(long period) {
        return getDay(period
                - getDistanceYear(period) * yearLevelValue
                - getDistanceMonth(period) * monthLevelValue);
    }

    public static int getDistanceHour(long period) {
        return getHour(period
                - getDistanceYear(period) * yearLevelValue
                - getDistanceMonth(period) * monthLevelValue
                - getDistanceDay(period) * dayLevelValue);
    }

    public static int getDistanceMinute(long period) {
        return getMinute(period
                - getDistanceYear(period) * yearLevelValue
                - getDistanceMonth(period) * monthLevelValue
                - getDistanceDay(period) * dayLevelValue
                - getDistanceHour(period) * hourLevelValue);
    }

    public static int getDistanceSecond(long period) {
        return getSecond(period
                - getDistanceYear(period) * yearLevelValue
                - getDistanceMonth(period) * monthLevelValue
                - getDistanceDay(period) * dayLevelValue
                - getDistanceHour(period) * hourLevelValue
                - getDistanceMinute(period) * minuteLevelValue);
    }

    public static int getYear(long period) {
        return (int) (period / yearLevelValue);
    }

    public static int getMonth(long period) {
        return (int) (period / monthLevelValue);
    }

    public static int getDay(long period) {
        return (int) (period / dayLevelValue);
    }

    public static int getHour(long period) {
        return (int) (period / hourLevelValue);
    }

    public static int getMinute(long period) {
        return (int) (period / minuteLevelValue);
    }

    public static int getSecond(long period) {
        return (int) (period / secondLevelValue);
    }


    public static void main(String[] args) {
        /*****测试效果，这里的毫秒数是15天，3小时，50分，18秒，999毫秒的毫秒总数，只精确到秒，所以999毫秒舍去*****/
        System.out.println(TimeUtils.getDifference(1000 * 60 * 60 * 24 * 15 + 1000 * 60 * 60 * 3 + 1000 * 60 * 50 + 1000 * 18 + 999));
    }
}
