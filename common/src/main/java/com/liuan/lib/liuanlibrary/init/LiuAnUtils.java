package com.liuan.lib.liuanlibrary.init;

import android.content.Context;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.liuan.lib.liuanlibrary.utils.Utils;


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
     * @param context  上下文
     *
     */
    public static void init(Context context) {
        if (libContext == null) {
            libContext = context;
            //65535方法解决方案
            MultiDex.install(context);
            //为Toast 提供能力
            Utils.init(context);
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


}
