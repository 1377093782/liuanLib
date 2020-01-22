package com.anguomob.lib.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;

public class SystemUtils {
    /**
     * 隐藏底部导航栏
     * 放到页面的super.onCreate(savedInstanceState);下
     */
    public static void hideBottomUIMenu(Activity activity) {
        //隐藏底部导航栏
        final View decorView = activity.getWindow().getDecorView();
        final int uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOption);

// This code will always hide the navigation bar
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    decorView.setSystemUiVisibility(uiOption);
                }
            }
        });
    }

    /**
     * 用参考布局 la_lib_header.xml
     * View  StatusBarHeightView
     * 放到页面的super.onCreate(savedInstanceState);下
     * @param isTranslucent 是否透明
     * @param activity  当前页面
     * @param colormainId 当前背景颜色
     */
    public static void initStatusBar(boolean isTranslucent,Activity activity,int colormainId) {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(activity, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(activity);
        if (!isTranslucent) {
            StatusBarUtil.setStatusBarColor(activity, activity.getResources().getColor(colormainId));
        }
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        视频播放设置成了白色 这样不至于不显示
        if (!StatusBarUtil.setStatusBarDarkTheme(activity, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(activity, 0x55ffffff);
        }


    }
    public static void setScreenByPortrait(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

}
