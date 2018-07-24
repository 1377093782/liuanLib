package com.liuan.lib.liuanlibrary.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtils {
    public static void onKeyHome() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_HOME);
        LiuAnUtils.getContext().startActivity(intent);
    }

    public static void fromA2B(Class cls) {
        Intent intent = new Intent(LiuAnUtils.getContext(), cls);
        LiuAnUtils.getContext().startActivity(intent);
    }

    public static void fromA2BAddtask(Class cls) {
        Intent intent = new Intent(LiuAnUtils.getContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LiuAnUtils.getContext().startActivity(intent);
    }
}
