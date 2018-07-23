package com.liuan.lib.liuanlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wl_pc on 17/5/19.
 */

public class TimeUtils {

    /**
     * 时间显示为yyyy年MM月dd日
     * time 时间戳
     */
    public static String timeToYearMouthDay(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日");
//        @SuppressWarnings("unused")
//        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
//        String times = sdr.format(new Date(i * 1000L));
        String result = sdr.format(new Date(time));
        return result;
    }




    /**
     * 将时间戳转化为毫秒
     *
     * @param time
     * @return
     */
    public static long timeToMillisecond(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long msTime = -1;
        try {
            msTime = simpleDateFormat.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return msTime;
    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
