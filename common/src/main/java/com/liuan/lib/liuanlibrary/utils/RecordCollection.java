package com.liuan.lib.liuanlibrary.utils;

import android.content.Context;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

public class RecordCollection {
    private static final String TAG = "RecordCollection";


    public static void record(Context context, String name, String event) {
        if (LiuAnUtils.getUmisStart()) {
            String channel = AnalyticsConfig.getChannel(context);
//        context	当前宿主进程的ApplicationContext上下文。
//        eventId	为当前统计的事件ID。
//        label	事件的标签属性。
//https://developer.umeng.com/docs/66632/detail/66889  文档说明

            // 事件id 必须先注册
            //https://mobile.umeng.com/apps/b7f10069e6e85e761fc7c365/events

            MobclickAgent.onEvent(context, name, event + "_" + channel);
            Firebase.getInstance(context).logEvent(name, event + "_" + channel);
        } else {
            Firebase.getInstance(context).logEvent(name, event);
        }


    }


    public static void record(String name, String event) {

        record(LiuAnUtils.getContext(), name, event);
    }


    public static void record(String event) {

        record(LiuAnUtils.getContext(), LiuAnUtils.getContext().getClass().getSimpleName(), event);
    }

}
