package com.liuan.lib.liuanlibrary.utils;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.commonsdk.UMConfigure;


public class LiuAnUtils {
    /**
     * 初始化插件
     *
     * @param context
     */
    private static Context libContext;
    private static boolean isUmStart = false;


    /**
     * 初始化插件
     *
     * @param context  上下文
     *
     */
    public static void init(Context context) {
        if (libContext == null) {
            libContext = context;
        }




    }


    /**
     * 初始化插件
     *
     * @param context  上下文
     * @param umSecret 友盟统计密钥 传null 或者""则不开启友盟
     */
    public static void init(Context context, String umSecret) {
        if (libContext == null) {
            libContext = context;
        }

        if (!TextUtils.isEmpty(umSecret)) {
            // 开启友盟的方法
            UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, umSecret);
            isUmStart = true;
        }


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

    public static boolean getUmisStart() {
        return isUmStart;

    }
}
