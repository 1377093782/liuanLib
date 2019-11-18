package com.liuan.lib.liuanlibrary.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

public class ShareUtils {
    public static void share(Activity activity) {
//        清单文件需要配置
        /*
        *
        *     <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.liuan.videowapllpaper.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">

            <!-- 元数据 -->
            <!-- <meta-data -->
            <!-- android:name="android.support.FILE_PROVIDER_PATHS" -->
            <!-- android:resource="@xml/file_paths" /> -->
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/bdp_update_filepaths" />
        </provider>
        * ======
        *
        *
        * bdp_update_filepaths
        * <?xml version="1.0" encoding="utf-8"?>
<!--
  ~ Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
  -->
<resources>
    <external-files-path name="extfiles" path="autoupdatecache/" />
    <external-cache-path name="extcachs" path="autoupdatecache/" />
    <cache-path name="intcachs" path="autoupdatecache/" />
    <paths>
        <root-path path="" name="camera_photos" />
    </paths>
</resources>
        *
        * */
        File f = new File(activity.getPackageResourcePath());
        //调用android分享窗口
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);


        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
//参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致 参数3 共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(activity, activity.getPackageName()+".fileprovider", f);
//添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(Intent.EXTRA_STREAM, apkUri);
        } else {
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        }
        intent.setType("*/*");

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
