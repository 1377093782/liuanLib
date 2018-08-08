package com.liuan.lib.liuanlibrary.init;

import android.content.Context;
import android.text.TextUtils;



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
