package com.liuan.lib.liuanlibrary.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;



public class ToastUtil
{


    private static boolean isShow = true;
    /*设置关闭和打开Toast*/
    public  static void setIsShowToast(boolean isShowToast){
        isShow=isShowToast;
    }
    private static Toast toast;
    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message  信息
     */
    public static void showShort(Context context, CharSequence message)
    {
        if (isShow){
            return;
        }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 子线程中显示吐司
     * @param context 上下文
     * @param message  信息
     */
    public static void showThreadShort(final Context context, final CharSequence message)
    {
        if (isShow){
            new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message   信息
     */
    public static void showShort(Context context, int message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 信息
     */
    public static void showLong(Context context, CharSequence message)
    {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message   信息
     */
    public static void showLong(Context context, int message)
    {
        if (!isShow){
            return;
        }
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文
     * @param message 信息
     * @param duration 显示时间
     */
    public static void show(Context context, CharSequence message, int duration)
    {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context 上下文
     * @param stringResId 文本资源id
     * @param duration 显示时间
     */
    public static void show(Context context, int stringResId, int duration)
    {
        if (!isShow){
            return;
        }
            Toast.makeText(context, stringResId, duration).show();
    }

    public static void show( int stringResId, int duration)
    {
        if (!isShow){
            return;
        }
        Toast.makeText(LiuAnUtils.getContext(), stringResId, duration).show();
    }
    /**
     * 强大的吐司，能够连续弹的吐司
     * @param context 上下文
     * @param text 信息
     */
    public static void showToast(Context context,String text){
        if(!isShow){
            return;
        }
        if(toast==null){
            //如果等于null，则创建
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }else {
            //如果不等于空，则直接将text设置给toast
            toast.setText(text);
        }
        toast.show();
    }

    public static void showToast(String text){
        if(!isShow){
            return;
        }
        if(toast==null){
            //如果等于null，则创建
            toast = Toast.makeText(LiuAnUtils.getContext(), text, Toast.LENGTH_SHORT);
        }else {
            //如果不等于空，则直接将text设置给toast
            toast.setText(text);
        }
        toast.show();
    }

    public static void showToast(int textResId){
        if(!isShow){
            return;
        }
        if(toast==null){
            //如果等于null，则创建
            toast = Toast.makeText(LiuAnUtils.getContext(), textResId, Toast.LENGTH_SHORT);
        }else {
            //如果不等于空，则直接将text设置给toast
            toast.setText(textResId);
        }
        toast.show();
    }




}
