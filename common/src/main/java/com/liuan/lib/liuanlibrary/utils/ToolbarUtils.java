/*
* ToolBar操作工具类*/

package com.liuan.lib.liuanlibrary.utils;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class ToolbarUtils {
    public ToolbarUtils() {
    }


/*    设置toolbar的标题 带返回键
 */
    public static void setToobar(String title, Toolbar mToolBar, final AppCompatActivity activity) {
        mToolBar.setTitle(title);
        activity.setSupportActionBar(mToolBar);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }
}
