package com.liuan.lib.liuanlibrary.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.liuan.lib.liuanlibrary.utils.DevicesInfoUtils.externalMemoryAvailable;

public class FileUtils {



    /**
     * 不用加SD地址 就可以
     * @param path
     */
    public  static String setSdCardPath(Context context, String path){
        //
        if (externalMemoryAvailable()) {
            return  Environment.getExternalStorageDirectory().getPath() + "/"+path;
            //获取跟目录
        } else {
            return context.getFilesDir().getPath() + "/"+path;
        }


    }






}
