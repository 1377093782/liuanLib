package com.liuan.lib.liuanlibrary.init;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;

import com.liuan.lib.liuanlibrary.BuildConfig;
import com.liuan.lib.liuanlibrary.utils.Utils;
import com.tencent.mmkv.MMKV;


public class LiuAnUtils {
    /**
     * 初始化插件
     *
     * @param context
     */
    private static Context libContext;


    /**
     * 初始化插件
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        if (libContext == null) {
            libContext = context;
            //65535方法解决方案
            MultiDex.install(context);
            //为Toast 提供能力
            Utils.init(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            webviewSetPath(context);
        }
        MMKV.initialize(context);


    }

    @RequiresApi(api = 28)
    public static void webviewSetPath(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName(context);

            if (!context.getPackageName().equals(processName)) {//判断不等于默认进程名称
                WebView.setDataDirectorySuffix(processName);
            }
        }
    }

    public static String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }


    public static Context getContext() {

        if (libContext == null) {
            try {
                throw new Exception("Must be called when the program starts------>LiuAnUtils.init(this);");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return libContext;
    }


}
