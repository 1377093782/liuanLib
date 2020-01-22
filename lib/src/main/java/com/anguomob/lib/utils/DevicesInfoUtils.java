package com.anguomob.lib.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Environment;
import android.os.StatFs;
import androidx.core.app.ActivityCompat;
import android.telephony.TelephonyManager;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DevicesInfoUtils {
    /*获取cpu 核心数*/
    public static String getNumCores() {
        // Private Class to display only CPU devices in the directory listing
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length + "";
        } catch (Exception e) {
            // Default to return 1 core
            return "1";
        }
    }

    /**
     * 获取CPU型号
     *
     * @return
     */
    public static String getCpuName() {

        String str1 = "/proc/cpuinfo";
        String str2 = "";

        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr);
            while ((str2 = localBufferedReader.readLine()) != null) {
                if (str2.contains("Hardware")) {
                    return str2.split(":")[1];
                }
            }
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return null;

    }

    // 获取CPU最大频率（单位KHZ）

    // "/system/bin/cat" 命令行


    // "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" 存储最大频率的文件的路径
//返回GHZ /10^6
    public static String getMaxCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {"/system/bin/cat",
                    "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while (in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();


        } catch (IOException ex) {
            ex.printStackTrace();
            result = "N/A";
        }
        if (result.length() >= 6) {

            double res = 0;
            try {
                res = Float.parseFloat(getGroup(result)) / Math.pow(10, 6);
                return String.format("%.1f", res) + "GHZ";

            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "N/A";
            }
        }
        return result.trim();
    }

    private static String getGroup(String result) {
        // 2188800
        //    ��������������������������������

        Pattern compile = Pattern.compile(".+\\d+");
        Matcher matcher = compile.matcher(result);
        boolean isFind = matcher.find();
        if (isFind) {
            return matcher.group(0);

        } else {
            return "N/A";
        }

    }


    /**
     * 获得SD卡总大小
     *
     * @param context
     * @return
     */
    public static long getSDTotalSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
//        Formatter.formatFileSize(context,)
        return blockSize * totalBlocks;
    }

    /**
     * 获取手机IMEI号
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "N/A";
        }
        String imei = telephonyManager.getDeviceId();

        return imei;
    }

    public static long getTotalRam(Context context){//GB
        String path = "/proc/meminfo";
        String firstLine = null;
        long totalRam = 0 ;
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader,8192);
            firstLine = br.readLine().split("\\s+")[1];
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(firstLine != null){
            totalRam = (int)Math.ceil((new Float(Float.valueOf(firstLine) ).doubleValue()));
        }

        return totalRam *1024;//返回1GB/2GB/3GB/4GB
    }

    public static String getScreenWidth(Context activity) {
//        return activity.getWindowManager().getDefaultDisplay().getWidth() + "";
        return  activity.getResources().getDisplayMetrics().heightPixels + "";
    }

    public static String getScreenHeight(Context activity) {
//        return activity.getWindowManager().getDefaultDisplay().getHeight() + "";
        return  activity.getResources().getDisplayMetrics().widthPixels + "";
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
//    ym 获取渠道号AnalyticsConfig.getChannel(this)


    public static String getSeesionOrPartId() {
        long value = System.currentTimeMillis();
        String base36 = Long.toString(value, 36);
        return 0 + ":" + base36 + ":" +getRandomChar(32);
    }
    public static  String getVolume(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVoluem = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return (currentVolume * 100 / maxVoluem)+"%";
    }
    /**
     * JAVA获得0-9,a-z,A-Z范围的随机数
     *
     * @param length 随机数长度
     * @return String
     */
    public static String getRandomChar(int length) {
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '~', '_', '!'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(62)]);
        }
        return buffer.toString();
    }
}
