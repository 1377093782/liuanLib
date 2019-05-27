package com.liuan.lib.liuanlibrary.utils;


import com.liuan.lib.liuanlibrary.init.LiuAnUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by liuan on 2018-07-05.
 */

public class TextFileUtils {
    public static String getIdByText(int id){
       return   LiuAnUtils.getContext().getResources().getString(id);
    }


    public static void addTxtToFileBuffered(File file, String content) {
        //在文本文本中追加内容
        BufferedWriter out = null;
        try {
            //FileOutputStream(file, true),第二个参数为true是追加内容，false是覆盖
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(content);
            out.newLine();//换行

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void deleteLineForText(File f, int deleteLine) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f.getAbsolutePath()));

            StringBuffer sb = new StringBuffer();
            String temp = null;
            int line = 0;
            while ((temp = br.readLine()) != null) {
                line++;
                if (line == deleteLine) continue;
                sb.append(temp).append("\r\n");
            }
            br.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsolutePath(),false));
            bw.write(sb.toString());
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLineForText(File f, int deleteLine) {
        String text = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f.getAbsolutePath()));

            String temp = null;
            int line = 0;
            while ((temp = br.readLine()) != null) {
                line++;
                if (line == deleteLine) {
                    text = temp;
                    break;
                }
            }
            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
