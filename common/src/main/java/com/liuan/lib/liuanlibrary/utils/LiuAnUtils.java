package com.liuan.lib.liuanlibrary.utils;

import android.content.Context;


public class LiuAnUtils {
    /**
     * 初始化插件
     * @param context
     */
    private static Context libContext;
    public static void init(Context context){
        if(libContext==null){
            libContext = context;
        }
    }
    public static Context getContext(){
        return libContext;
    }
}
