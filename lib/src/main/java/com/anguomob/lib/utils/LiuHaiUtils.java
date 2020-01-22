package com.anguomob.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;


import com.anguomob.lib.init.LiuAnUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LiuHaiUtils {
    /**
     * 判断是否是刘海屏
     * @return
     */
    public static boolean hasNotchScreen(Activity activity){
        if (getInt("ro.miui.notch",activity) == 1 || hasNotchAtHuawei() || hasNotchAtOPPO()
                || hasNotchAtVivo() || isAndroidP(activity) != null){ //TODO 各种品牌
            return true;
        }

        return false;
    }

    /**
     * Android P 刘海屏判断
     * @param activity
     * @return
     */
    public static DisplayCutout isAndroidP(Activity activity){
        View decorView = activity.getWindow().getDecorView();
        if (decorView != null && Build.VERSION.SDK_INT >= 28){
            WindowInsets windowInsets = decorView.getRootWindowInsets();
            if (windowInsets != null)
                return windowInsets.getDisplayCutout();
        }
        return null;
    }

    /**
     * 小米刘海屏判断.
     * @return 0 if it is not notch ; return 1 means notch
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static int getInt(String key,Activity activity) {
        int result = 0;
        if (isXiaomi()){
            try {
                ClassLoader classLoader = activity.getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
                //参数类型
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[2];
                paramTypes[0] = String.class;
                paramTypes[1] = int.class;
                Method getInt = SystemProperties.getMethod("getInt", paramTypes);
                //参数
                Object[] params = new Object[2];
                params[0] = new String(key);
                params[1] = new Integer(0);
                result = (Integer) getInt.invoke(SystemProperties, params);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    // 是否是小米手机
    public static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    public static Context getContext(){
        return LiuAnUtils.getContext();
    };
    /**
     * 华为刘海屏判断
     * @return
     */
    public static boolean hasNotchAtHuawei() {
        boolean ret = false;
        try {
            ClassLoader classLoader = getContext().getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            LogUtils.e("hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e("hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e( "hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * VIVO刘海屏判断
     * @return
     */
    public static boolean hasNotchAtVivo() {
        boolean ret = false;
        try {
            ClassLoader classLoader = getContext().getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            LogUtils.e( "hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            LogUtils.e(  "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            LogUtils.e(  "hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }
    /**
     * OPPO刘海屏判断
     * @return
     */
    public static boolean hasNotchAtOPPO() {
        return  getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

}
