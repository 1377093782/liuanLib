package com.liuan.lib.liuanlibrary.utils;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;


import com.liuan.lib.liuanlibrary.R;

import java.io.File;

/**
 *
 */
public class SettingUtils {
    public static void feedBack(Context context, String appName, String email) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:" + email));
        data.putExtra(Intent.EXTRA_SUBJECT, appName + ":");
        data.putExtra(Intent.EXTRA_TEXT, "");
        try {
            startActivity_(context, data);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void version(Context context, String versionStr) {
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


    public static void shareMyApp(Context context) {
        File f = new File(context.getPackageResourcePath());
        LogUtils.e(context.getPackageResourcePath() + "context.getPackageResourcePath()");
        //调用android分享窗口
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    public static void startGoogleMyApp(Context context){
        if (isInstalled(context, "com.android.vending")) {
            //Analytics.getInstance(context)._sendEvent(RecommendManager.CATEGORY, RecommendManager.ACTION_AD, "有Google Play");
            String market = "market://details?id=" + context.getPackageName() + "&referrer=utm_source%3D" + context.getString(R.string.app_name);
            Intent intent = new Intent();
            intent.setPackage("com.android.vending");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(market));
            intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(intent);
        } else {
            //Analytics.getInstance(context)._sendEvent(RecommendManager.CATEGORY, RecommendManager.ACTION_AD, "没有Google Play");

            startGooglePlayByHttpUrl(context, context.getPackageName(), context.getString(R.string.app_name));
        }
    }
    //google 市场五星好评
    public static void startGooglePlayByMarketUrl(Context context, String packageName) {
        //如果安装过google市场
        if (isInstalled(context, packageName)) {
            PackageManager pm = context.getPackageManager();
            Intent intent = pm.getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        } else if (isInstalled(context, "com.android.vending")) {
            //Analytics.getInstance(context)._sendEvent(RecommendManager.CATEGORY, RecommendManager.ACTION_AD, "有Google Play");
            String market = "market://details?id=" + packageName + "&referrer=utm_source%3D" + context.getString(R.string.app_name);
            Intent intent = new Intent();
            intent.setPackage("com.android.vending");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(market));
            intent.setAction(Intent.ACTION_VIEW);
            context.startActivity(intent);
        } else {
            //Analytics.getInstance(context)._sendEvent(RecommendManager.CATEGORY, RecommendManager.ACTION_AD, "没有Google Play");

            startGooglePlayByHttpUrl(context, packageName, context.getString(R.string.app_name));
        }


    }

    public static boolean startActivity_(Context context, Intent intent) {
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

    public static void startGooglePlayByHttpUrl(Context context, String packageName, String zui) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("http://play.google.com/store/apps/details?id=" + packageName + "&referrer=utm_source%3D" + zui));
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


}
