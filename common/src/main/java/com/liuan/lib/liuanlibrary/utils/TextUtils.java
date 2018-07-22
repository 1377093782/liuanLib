package com.liuan.lib.liuanlibrary.utils;


/**
 * Created by liuan on 2018-07-05.
 */

public class TextUtils {
    public static String getIdByText(int id){
       return   LiuAnUtils.getContext().getResources().getString(id);
    }
}
