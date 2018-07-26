package com.liuan.lib.liuanlibrary.utils;

import android.os.Build;
import android.util.Log;

import com.liuan.lib.liuanlibrary.BuildConfig;

public class LogUtils {
    private static final String TAG = "liuan_log";
    private static boolean isStartLog=BuildConfig.DEBUG;
    public static void e(String msg){
        if(isStartLog){
            Log.e(TAG, msg );
        }
    }


    public static void d(String msg){
        if(isStartLog){
            Log.d(TAG, msg );
        }
    }


    public static void v(String msg){
        if(isStartLog){
            Log.v(TAG, msg );
        }
    }

    public static void i(String msg){
        if(isStartLog){
            Log.i(TAG, msg );
        }
    }

    public static void w(String msg){
        if(isStartLog){
            Log.i(TAG, msg );
        }
    }



    /**
     * 打印information日志
     * @param tag 标签
     * @param msg 日志信息
     */
    public static void i(String tag,String msg){
        if(isStartLog) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印information日志
     * @param tag	标签
     * @param msg	日志信息
     * @param throwable	异常
     */
    public static void i(String tag, String msg, Throwable throwable){
        if(isStartLog) {
            Log.i(tag, msg, throwable);
        }
    }

    /**
     * 打印verbose日志
     * @param tag	标签
     * @param msg	日志信息
     */
    public static void v(String tag, String msg){
        if(isStartLog) {
            Log.v(tag, msg);
        }
    }

    /**
     * 打印verbose日志
     * @param tag	标签
     * @param msg	日志信息
     * @param throwable	异常
     */
    public static void v(String tag, String msg, Throwable throwable){
        if(isStartLog){
            Log.v(tag, msg, throwable);
        }
    }

    /**
     * 打印debug信息
     * @param tag	标签信息
     * @param msg	日志信息
     */
    public static void d(String tag, String msg){
        if(isStartLog) {
            Log.d(tag, msg);
        }
    }

    /**
     * 打印debug日志
     * @param tag	标签信息
     * @param msg	日志信息
     * @param throwable	异常
     */
    public static void d(String tag, String msg, Throwable throwable){
        if(isStartLog) {
            Log.d(tag, msg, throwable);
        }
    }

    /**
     * 打印warn日志
     * @param tag	标签信息
     * @param msg	日志信息
     */
    public static void w(String tag, String msg){
        if(isStartLog) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印warn日志
     * @param tag	标签信息
     * @param msg	日志信息
     * @param throwable	异常
     */
    public static void w(String tag, String msg, Throwable throwable){
        if(isStartLog) {
            Log.w(tag, msg, throwable);
        }
    }

    /**
     * 打印error日志
     * @param tag	标签
     * @param msg	日志信息
     */
    public static void e(String tag, String msg){
        if(isStartLog) {
            Log.e(tag, msg);
        }
    }

    /**
     * 打印error日志
     * @param tag	标签
     * @param msg	日志信息
     * @param throwable	异常
     */
    public static void e(String tag, String msg, Throwable throwable){
        if(isStartLog) {
            Log.e(tag, msg, throwable);
        }    }

}
