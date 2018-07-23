package com.liuan.lib.liuanlibrary.utils;

import android.os.Build;
import android.util.Log;

import com.liuan.lib.liuanlibrary.BuildConfig;

public class LogUtils {
    private static final String TAG = "liuan_log";
    public static void e(String msg){
        if(BuildConfig.DEBUG){
            Log.e(TAG, msg );
        }
    }
}
