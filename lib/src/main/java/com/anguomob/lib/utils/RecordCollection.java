package com.anguomob.lib.utils;

import android.content.Context;

import com.anguomob.lib.init.LiuAnUtils;


public class RecordCollection {
    private static final String TAG = "RecordCollection";


    public static void record(Context context, String name, String event) {

            Firebase.getInstance(context).logEvent(name, event);



    }


    public static void record(String name, String event) {

        record(LiuAnUtils.getContext(), name, event);
    }


    public static void record(String event) {

        record(LiuAnUtils.getContext(), LiuAnUtils.getContext().getClass().getSimpleName(), event);
    }

}
