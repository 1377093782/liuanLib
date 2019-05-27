package com.liuan.lib.liuanlibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.regex.Pattern;

public class DeviceUtils {
    /**
     * 使用前请先获取 Manifest.permission.READ_PHONE_STATE权限
     * @param context 上下文
     * @param imei 串号查询不到返回的值 可以传空
     * @param activity 当前页面
     * @return
     */
    public static String getImei(Context context, String imei, Activity activity) {
        String ret = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//                return TODO;
                return "";
            }
            ret = telephonyManager.getDeviceId();
        } catch (Exception e) {
        }
        if (isReadableASCII(ret)){
            return ret;
        } else {
            return imei;
        }
    }
    private static boolean isReadableASCII(CharSequence string){
        if (TextUtils.isEmpty(string)) return false;
        try {
            Pattern p = Pattern.compile("[\\x20-\\x7E]+");
            return p.matcher(string).matches();
        } catch (Throwable e){
            return true;
        }
    }
}
