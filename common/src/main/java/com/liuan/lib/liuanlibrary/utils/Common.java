package com.liuan.lib.liuanlibrary.utils;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AlertDialog;
import android.util.Log;


import com.liuan.lib.liuanlibrary.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by EdwinWu on 2016/11/7.
 */
public class Common {


    public static void feedBack(Context context,String appName) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:mp624183768@gmail.com"));
        data.putExtra(Intent.EXTRA_SUBJECT, appName+":");
        data.putExtra(Intent.EXTRA_TEXT, "");
        try {
            startActivity(context, data);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void version(Context context,String versionStr) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String version = packageInfo.versionName;

            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            String label = applicationInfo.loadLabel(packageManager).toString();

            new AlertDialog.Builder(context, R.style.MainActivityAlertDialog)
                    .setTitle(versionStr)
                    .setMessage(label + " (" + version + ")")
                    .show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean startGooglePlayByMarketUrl(Context context, String packageName, String zui) {
        if ((packageName == null) || (packageName.length() == 0)) {
            return false;
        }
        if (isInstalled(context, "com.android.vending")) {
        } else {
            return false;
        }
        String market = "market://details?id=" + packageName + "&referrer=utm_source%3D"+zui;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setPackage("com.android.vending");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(market));
        return startActivity(context, intent);
    }

    public static void startGooglePlayByHttpUrl(Context context, String packageName, String zui) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + packageName + "&referrer=utm_source%3D"+zui));
        context.startActivity(intent);
    }

    public static boolean isInstalled(Context context, String packageName) {
        boolean bInstalled = false;
        if (packageName == null)
            return false;
        PackageInfo packageInfo = null;

        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();

        }
        if (packageInfo == null) {
            bInstalled = false;
        } else {
            bInstalled = true;
        }
        return bInstalled;
    }

    public static boolean startActivity(Context context, Intent intent) {
        if ((context == null) || (intent == null))
            return false;
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            activityNotFoundException.printStackTrace();
            return false;
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
            return false;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }


    // 获取可用内存空间大小
    public static long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(outInfo);
        return Math.abs(outInfo.availMem);
    }

    // 获取总运存大小
    public static long getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小
            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i("tatolram:" + str2, num + "\t");
            }
            initial_memory = Long.valueOf(arrayOfString[1]).longValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (Exception e) {
        }
        return Math.abs(initial_memory);
    }

    public static void startAPP(Context context , String appPackageName){
        try{
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        }catch(Exception e){
            //Toast.makeText(context, "没有安装", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * SDCARD是否存
     */
    public static boolean externalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SDCARD剩余存储空间
     *
     * @return
     */
    public static long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = stat.getBlockSizeLong();
            }else{
                blockSize = stat.getBlockSize();
            }
            long availableBlocks = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableBlocks = stat.getAvailableBlocksLong();
            }else{
                availableBlocks = stat.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        } else {
            return 0;
        }
    }

    /**
     * 获取SDCARD总的存储空间
     *
     * @return
     */
    public static long getTotalExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = stat.getBlockSizeLong();
            }else{
                blockSize =stat.getBlockSize();
            }
            long totalBlocks = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                totalBlocks = stat.getBlockCountLong();
            }else{
                totalBlocks = stat.getBlockCount();
            }
            return totalBlocks * blockSize;
        } else {
            return 0;
        }
    }



    /**
     * 获取手机内部剩余存储空间
     *
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
        }else{
            blockSize = stat.getBlockSize();
        }
        long availableBlocks = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocks = stat.getAvailableBlocksLong();
        }else{
            availableBlocks = stat.getAvailableBlocks();
        }
        return availableBlocks * blockSize;
    }

    /**
     * 获取手机内部总的存储空间
     *
     * @return
     */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
        }else{
            blockSize = stat.getBlockSize();
        }
        long totalBlocks = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            totalBlocks = stat.getBlockCountLong();
        }else{
            totalBlocks = stat.getBlockCount();
        }
        return totalBlocks * blockSize;
    }


    /**
     * 获取ConnectivityManager
     */
    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 判断网络连接是否有效（此时可传输数据）。
     *
     * @return boolean 不管wifi，还是mobile net，只有当前在连接状态（可有效传输数据）才返回true,反之false。
     */
    public static boolean isConnected(Context context) {
        NetworkInfo net = getConnectivityManager(context).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            try {
                ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (mWiFiNetworkInfo != null) {
                    return mWiFiNetworkInfo.isAvailable();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
